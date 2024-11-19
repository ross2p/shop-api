package ua.edu.lnu.shop_api.mapper;

import org.mapstruct.*;
import ua.edu.lnu.shop_api.dto.orderItem.OrderItemCreationUpdateRequest;
import ua.edu.lnu.shop_api.dto.orderItem.OrderItemResponse;
import ua.edu.lnu.shop_api.entity.Image;
import ua.edu.lnu.shop_api.entity.OrderItem;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ProductMapper.class, OrderMapper.class})
public interface OrderItemMapper {
    @Mapping(source = "orderId", target = "order.id")
    @Mapping(source = "productId", target = "product.id")
    OrderItem toEntity(OrderItemCreationUpdateRequest orderItemCreationUpdateRequest);


    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OrderItem partialUpdate(OrderItemCreationUpdateRequest orderItemCreationUpdateRequest, @MappingTarget OrderItem orderItem);


    OrderItemResponse toDto(OrderItem orderItem);

}