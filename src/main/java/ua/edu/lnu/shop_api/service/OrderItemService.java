package ua.edu.lnu.shop_api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ua.edu.lnu.shop_api.dto.orderItem.OrderItemCreationUpdateRequest;
import ua.edu.lnu.shop_api.dto.orderItem.OrderItemResponse;
import ua.edu.lnu.shop_api.entity.OrderItem;

import java.util.UUID;

public interface OrderItemService {
    OrderItem create(OrderItemCreationUpdateRequest orderItem);

    OrderItemResponse update(UUID id, OrderItemCreationUpdateRequest orderItem);

    OrderItemResponse addProduct(OrderItemCreationUpdateRequest orderItem, UUID userId);

    void delete(UUID id);

    OrderItem findById(UUID id);

    Page<OrderItemResponse> findAll(PageRequest pageRequest, UUID orderId);
}
