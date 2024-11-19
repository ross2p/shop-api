package ua.edu.lnu.shop_api.mapper;

import org.mapstruct.*;
import ua.edu.lnu.shop_api.dto.promotion.PromotionCreationUpdateRequest;
import ua.edu.lnu.shop_api.entity.Promotion;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PromotionMapper {

    Promotion toEntity(PromotionCreationUpdateRequest promotionCreationUpdateRequest);

    PromotionCreationUpdateRequest toDto(Promotion promotion);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Promotion partialUpdate(PromotionCreationUpdateRequest promotionCreationUpdateRequest, @MappingTarget Promotion promotion);
}