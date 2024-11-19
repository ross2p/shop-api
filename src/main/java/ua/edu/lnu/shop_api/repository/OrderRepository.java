package ua.edu.lnu.shop_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.lnu.shop_api.entity.Order;
import ua.edu.lnu.shop_api.entity.Status;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    Optional<Order> findByUser_IdAndStatus(UUID id, Status status);
    Page<Order> findByUser_Id(UUID id, Pageable pageable);

}