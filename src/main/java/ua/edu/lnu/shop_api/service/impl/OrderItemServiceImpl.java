package ua.edu.lnu.shop_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.edu.lnu.shop_api.dto.orderItem.OrderItemCreationUpdateRequest;
import ua.edu.lnu.shop_api.dto.orderItem.OrderItemResponse;
import ua.edu.lnu.shop_api.entity.Order;
import ua.edu.lnu.shop_api.entity.OrderItem;
import ua.edu.lnu.shop_api.entity.Product;
import ua.edu.lnu.shop_api.entity.Promotion;
import ua.edu.lnu.shop_api.mapper.OrderItemMapper;
import ua.edu.lnu.shop_api.repository.OrderItemRepository;
import ua.edu.lnu.shop_api.repository.OrderRepository;
import ua.edu.lnu.shop_api.service.OrderItemService;
import ua.edu.lnu.shop_api.service.OrderService;
import ua.edu.lnu.shop_api.service.ProductService;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;
    private final OrderService orderService;
    private final ProductService productService;
    private final OrderRepository orderRepository;

    @Override
    public OrderItem create(OrderItemCreationUpdateRequest orderItem) {
        OrderItem orderItemToCreate = orderItemMapper.toEntity(orderItem);
        return orderItemRepository.save(orderItemToCreate);
    }

    @Override
    public OrderItemResponse update(UUID id, OrderItemCreationUpdateRequest orderItem) {
        System.out.println("update");
        OrderItem existingOrderItem = this.findById(id);
        System.out.println("existingOrderItem");

        OrderItem orderItemToUpdate = orderItemMapper.partialUpdate(orderItem, existingOrderItem);
        System.out.println("orderItemToUpdate" + orderItem);
        orderItemToUpdate = calculateOrderItem(orderItemToUpdate, productService.findById(orderItem.productId()));

        return this.orderItemMapper.toDto(orderItemRepository.save(orderItemToUpdate));
    }

    @Override
    public OrderItemResponse addProduct(OrderItemCreationUpdateRequest orderItem, UUID userId) {
        System.out.println("addProduct");
        Order actualOrder = orderService.findActualOrder(userId);
        Set<OrderItem> orderItems = this.findAll(actualOrder.getId());

        Optional<OrderItem> existingOrderItem = orderItems.stream()
                .filter(item -> item.getProduct().getId().equals(orderItem.productId()))
                .findFirst();

        OrderItem orderItemToCreate;
        if (existingOrderItem.isPresent()) {
            orderItemToCreate = existingOrderItem.get();
            orderItemToCreate.setQuantity(orderItemToCreate.getQuantity() + orderItem.quantity());
        } else {
            orderItemToCreate = orderItemMapper.toEntity(orderItem);
            orderItemToCreate.setOrder(actualOrder);
        }
        orderItemToCreate = calculateOrderItem(orderItemToCreate, productService.findById(orderItem.productId()));

        return this.orderItemMapper.toDto( this.orderItemRepository.save(orderItemToCreate));
    }

    private OrderItem calculateOrderItem(OrderItem orderItem, Product product) {
        if (orderItem.getQuantity() <= 0) {
            this.delete(orderItem.getId());
        }

        Promotion promotion = product.getPromotion();
        if (promotion == null) {
            orderItem.setPrice(product.getPrice() * orderItem.getQuantity());
        }else{
            int count = orderItem.getQuantity()/promotion.getAmount();

            if (count > 0 ) {
                if(count > promotion.getAmount()){
                    count = promotion.getAmount();
                }
                double price = product.getPrice() * orderItem.getQuantity() - (product.getPrice() * promotion.getDiscount()/100) *  count;
                orderItem.setPrice(price);
            }
        }
        OrderItem updatedOrderItem = orderItemRepository.save(orderItem);
        calculateOrder(orderItem.getOrder().getId());
        return updatedOrderItem;
    }
    private Order calculateOrder(UUID orderId) {
        Order order = orderService.findById(orderId);
        Set<OrderItem> orderItems = this.findAll(orderId);
        double price = orderItems.stream().mapToDouble(OrderItem::getPrice).sum();
        order.setTotalAmount(price);
        System.out.println("order" + price);
        return orderRepository.save(order);
    }

    @Override
    public void delete(UUID id) {
        //todo implement

    }

    @Override
    public OrderItem findById(UUID id) {
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderItem not found"));
    }

    @Override
    public Page<OrderItemResponse> findAll(PageRequest pageRequest, UUID orderId) {
        return orderItemRepository.findByOrder_Id(orderId, pageRequest).map(orderItemMapper::toDto);
    }

    public Set<OrderItem> findAll(UUID orderId) {
        return orderItemRepository.findOrderItemByOrder_Id(orderId);
    }

}
