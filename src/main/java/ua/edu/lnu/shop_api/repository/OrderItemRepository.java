package ua.edu.lnu.shop_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.lnu.shop_api.entity.OrderItem;

import java.util.Set;
import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
    Set<OrderItem> findOrderItemByOrder_Id(UUID id);
    Page<OrderItem> findByOrder_Id(UUID id, Pageable pageable);
}