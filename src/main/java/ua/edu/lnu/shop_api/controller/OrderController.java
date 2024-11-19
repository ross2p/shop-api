package ua.edu.lnu.shop_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ua.edu.lnu.shop_api.dto.auth.DefaultUserDetails;
import ua.edu.lnu.shop_api.dto.order.OrderCreationUpdateRequest;
import ua.edu.lnu.shop_api.dto.order.OrderData;
import ua.edu.lnu.shop_api.entity.Status;
import ua.edu.lnu.shop_api.service.OrderService;
import ua.edu.lnu.shop_api.entity.Order;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<Page<OrderData>> findAll(@RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
                                                   @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                   @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
                                                   @AuthenticationPrincipal DefaultUserDetails userDetails) {

        Page<OrderData> orders = orderService.findAll(PageRequest.of(offset, pageSize, Sort.by(sortBy)), userDetails.getId());
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/orders/all")
    public ResponseEntity<Page<OrderData>> findAll(@RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
                                                   @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                   @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy) {

        Page<OrderData> orders = orderService.findAll(PageRequest.of(offset, pageSize, Sort.by(sortBy)));
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<Order> findById(@PathVariable(value = "orderId") UUID orderId) {
        Order order = orderService.findById(orderId);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> create(@AuthenticationPrincipal DefaultUserDetails userDetails) {
        Order createdOrder = orderService.create(userDetails.getId());
        return ResponseEntity.ok(createdOrder);
    }

    @GetMapping("/orders/{orderId}/actual")
    public ResponseEntity<Order> findActualOrder(@AuthenticationPrincipal DefaultUserDetails userDetails) {
        Order order = orderService.findActualOrder(userDetails.getId());
        return ResponseEntity.ok(order);
    }


    @PutMapping("/orders/{orderId}")
    public ResponseEntity<Order> update(@PathVariable(value = "orderId") UUID orderId,
                                        @RequestBody OrderCreationUpdateRequest order) {
        Order updatedOrder = orderService.update(orderId, order);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<Void> delete(@PathVariable(value = "orderId") UUID orderId) {
        orderService.delete(orderId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/orders/status")
    public ResponseEntity<List<Status>> findByStatus() {
        List<Status> status = orderService.getAllStatus();
        return ResponseEntity.ok(status);
    }


}
