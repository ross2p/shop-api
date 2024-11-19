package ua.edu.lnu.shop_api.mapper;

import org.mapstruct.*;
import org.springframework.web.multipart.MultipartFile;
import ua.edu.lnu.shop_api.dto.image.ImageCreationUpdateRequest;
import ua.edu.lnu.shop_api.dto.image.ImageResponse;
import ua.edu.lnu.shop_api.entity.Image;

import java.io.IOException;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ImageMapper {
    @Mapping(source = "productId", target = "product.id")
    Image toEntity(ImageCreationUpdateRequest imageCreationUpdateRequest);

    @Mapping(source = "product.id", target = "productId")
    ImageCreationUpdateRequest toDto(Image image);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "productId", target = "product.id")
    Image partialUpdate(ImageCreationUpdateRequest imageCreationUpdateRequest, @MappingTarget Image image);


    @Mapping(source = "imageCreationUpdateRequest.productId", target = "product.id")
    @Mapping(target = "data", expression = "java(multipartFileToByteArray(multipartFile))")
    Image toEntity(ImageCreationUpdateRequest imageCreationUpdateRequest, MultipartFile multipartFile);

    // Helper method to convert MultipartFile to byte[]
    default byte[] multipartFileToByteArray(MultipartFile file) {
        try {
            return file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException("Error converting MultipartFile to byte array", e);
        }
    }

    Image toEntity(ImageResponse imageResponse);

    ImageResponse toDto1(Image image);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Image partialUpdate(ImageResponse imageResponse, @MappingTarget Image image);
}