package ua.edu.lnu.shop_api.mapper;

import org.mapstruct.*;
import ua.edu.lnu.shop_api.dto.address.AddressUserData;
import ua.edu.lnu.shop_api.dto.order.OrderCreationUpdateRequest;
import ua.edu.lnu.shop_api.dto.order.OrderData;
import ua.edu.lnu.shop_api.dto.order.OrderResponse;
import ua.edu.lnu.shop_api.entity.Address;
import ua.edu.lnu.shop_api.entity.Order;

import java.util.UUID;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserMapper.class, OrderItemMapper.class, OrderMapper.class})
public interface OrderMapper {
    @Mapping(source = "userId", target = "user.id")
    Order toEntity(UUID userId);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "addressId", target = "address.id")
    Order partialUpdate(OrderCreationUpdateRequest orderCreationUpdateRequest, @MappingTarget Order order);

    Order toEntity(OrderResponse orderResponse);

    OrderResponse toDto(Order order);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Order partialUpdate(OrderResponse orderResponse, @MappingTarget Order order);

    Address toEntity(AddressUserData addressUserData);

    AddressUserData toDto(Address address);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Address partialUpdate(AddressUserData addressUserData, @MappingTarget Address address);

    Order toEntity(OrderData orderData);

    OrderData toDto1(Order order);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Order partialUpdate(OrderData orderData, @MappingTarget Order order);
}