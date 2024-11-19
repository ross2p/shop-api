package ua.edu.lnu.shop_api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ua.edu.lnu.shop_api.dto.order.OrderCreationUpdateRequest;
import ua.edu.lnu.shop_api.dto.order.OrderData;
import ua.edu.lnu.shop_api.entity.Order;
import ua.edu.lnu.shop_api.entity.Status;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    Order create(UUID userId);

    Order update(UUID id, OrderCreationUpdateRequest order);

    void delete(UUID id);

    Order findById(UUID id);

    Page<OrderData> findAll(PageRequest pageRequest, UUID userId);

    Page<OrderData> findAll(PageRequest pageRequest);

    Order findActualOrder(UUID userId);

    List<Status> getAllStatus();
}
