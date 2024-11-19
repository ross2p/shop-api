package ua.edu.lnu.shop_api.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ua.edu.lnu.shop_api.dto.category.CategoryResponse;
import ua.edu.lnu.shop_api.dto.product.ProductData;
import ua.edu.lnu.shop_api.dto.product.ProductCreationUpdateRequest;
import ua.edu.lnu.shop_api.dto.product.ProductResponse;
import ua.edu.lnu.shop_api.dto.user.UserRating;
import ua.edu.lnu.shop_api.entity.*;

import java.util.*;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {CategoryMapper.class, CommentMapper.class, UserMapper.class,ImageMapper.class})
public interface ProductMapper {
    UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "category.id", source = "productCreationUpdateRequest.categoryId")
    @Mapping(target = "rating", ignore = true)
    @Mapping(target = "promotion", expression = "java(mapPromotion(productCreationUpdateRequest.promotionId()))") // handle null promotionId
    @Mapping(target = "images", expression = "java(mapImageIdsToImages(productCreationUpdateRequest.images(), product))")
    Product toEntity(ProductCreationUpdateRequest productCreationUpdateRequest);


    default Promotion mapPromotion(UUID promotionId) {
        if (promotionId == null) {
            return null;
        }
        Promotion promotion = new Promotion();
        promotion.setId(promotionId);
        return promotion;
    }


    @Named("mapImageIdsToImages")
    default Set<Image> mapImageIdsToImages(Set<UUID> imageIds, Product product) {
        if (imageIds == null || imageIds.isEmpty()) {
            return Collections.emptySet();
        }
        return imageIds.stream().map(id -> {
            Image image = new Image();
            image.setId(id);
            image.setProduct(product);
            return image;
        }).collect(Collectors.toSet());
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "images", ignore = true)
    Product partialUpdate(ProductCreationUpdateRequest productCreationUpdateRequest, @MappingTarget Product product);

//    Product toEntity(ProductResponse productResponse);

    @AfterMapping
    default void linkComments(@MappingTarget Product product) {
        product.getComments().forEach(comment -> comment.setProduct(product));
    }



    @Mapping(target = "userRating", source = "userRating", qualifiedByName = "mapUserRating")
    ProductResponse toDto(Product product);

    @Named("mapUserRating")
    default HashSet<UserRating> mapUserRating(Map<User, Double> userRating) {
        if (userRating == null) {
            return null;
        }
        Set<UserRating> set = userRating.entrySet().stream()
                .map(entry -> userMapper.toDto(entry.getKey(), entry.getValue())).collect(Collectors.toSet());
        return (HashSet<UserRating>) set;
    }


    //ProductData
    @Mapping(target = "userRating", source = "userRating", qualifiedByName = "mapUserRating")
    ProductData toDto1(Product product);


    //CategoryResponse
    CategoryResponse toDto(Category category);
}