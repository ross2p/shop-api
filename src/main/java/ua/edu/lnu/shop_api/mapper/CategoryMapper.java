package ua.edu.lnu.shop_api.mapper;

import org.mapstruct.*;
import ua.edu.lnu.shop_api.dto.category.CategoryCreationUpdateRequest;
import ua.edu.lnu.shop_api.dto.category.CategoryData;
import ua.edu.lnu.shop_api.dto.category.CategoryData;
import ua.edu.lnu.shop_api.dto.category.CategoryResponse;
import ua.edu.lnu.shop_api.entity.Category;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ProductMapper.class})
public interface CategoryMapper {
    Category toEntity(CategoryCreationUpdateRequest categoryCreationUpdateRequest);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Category partialUpdate(CategoryCreationUpdateRequest categoryCreationUpdateRequest, @MappingTarget Category category);

    Category toEntity(CategoryData categoryData);

    CategoryData toDto1(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Category partialUpdate(CategoryData categoryData, @MappingTarget Category category);
}