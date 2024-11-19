package ua.edu.lnu.shop_api.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.edu.lnu.shop_api.dto.order.OrderCreationUpdateRequest;
import ua.edu.lnu.shop_api.dto.order.OrderData;
import ua.edu.lnu.shop_api.entity.Order;
import ua.edu.lnu.shop_api.mapper.OrderMapper;
import ua.edu.lnu.shop_api.repository.OrderRepository;
import ua.edu.lnu.shop_api.service.OrderService;
import ua.edu.lnu.shop_api.entity.Status;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderItemMapper;


    @Override
    public Order create(UUID userId) {
        Optional<Order> actual = orderRepository.findByUser_IdAndStatus(userId, Status.PENDING);
        if (actual.isPresent()) {
            return actual.get();
        }
        Order orderToCreate = orderItemMapper.toEntity(userId);
        return orderRepository.save(orderToCreate);
    }

    @Override
    public Order update(UUID id, OrderCreationUpdateRequest order) {
        Order existingOrder = this.findById(id);
        Order orderToUpdate = orderItemMapper.partialUpdate(order, existingOrder);



        Order updatedOrder =  orderRepository.save(orderToUpdate);
        if(!updatedOrder.getStatus().equals(Status.PENDING)){
            this.create(updatedOrder.getUser().getId());
        }
        return updatedOrder;
    }

    @Override
    public void delete(UUID id) {
        //todo implement
    }

    @Override
    public Order findById(UUID id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public Page<OrderData> findAll(PageRequest pageRequest, UUID userId ) {
        return orderRepository.findByUser_Id(userId, pageRequest).map(orderItemMapper::toDto1);
    }

    @Override
    public Page<OrderData> findAll(PageRequest pageRequest) {
        return orderRepository.findAll(pageRequest).map(orderItemMapper::toDto1);
    }

    @Override
    public Order findActualOrder(UUID userId) {
        return orderRepository.findByUser_IdAndStatus(userId, Status.PENDING)
                .orElseThrow(() -> new RuntimeException("Actual order not found"));
    }

    @Override
    public List<Status> getAllStatus() {
        return List.of(Status.values());
    }
}
