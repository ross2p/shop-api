package ua.edu.lnu.shop_api.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ua.edu.lnu.shop_api.dto.auth.DefaultUserDetails;
import ua.edu.lnu.shop_api.dto.orderItem.OrderItemCreationUpdateRequest;
import ua.edu.lnu.shop_api.dto.orderItem.OrderItemResponse;
import ua.edu.lnu.shop_api.entity.OrderItem;
import ua.edu.lnu.shop_api.service.OrderItemService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Transactional
public class OrderItemsController {
    private final OrderItemService orderItemService;

    @GetMapping("order/{orderId}/orderItems")
    public ResponseEntity<Page<OrderItemResponse>> findAll(@RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
                                                   @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                   @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
                                                   @PathVariable(value = "orderId") UUID orderId) {

        Page<OrderItemResponse> orderItems = orderItemService.findAll(PageRequest.of(offset, pageSize, Sort.by(sortBy)), orderId);
        return ResponseEntity.ok(orderItems);
    }

    @GetMapping("/orderItems/{orderItemId}")
    public ResponseEntity<OrderItem> findById(@PathVariable(value = "orderItemId") UUID orderItemId) {
        OrderItem orderItem = orderItemService.findById(orderItemId);
        return ResponseEntity.ok(orderItem);
    }

    @PostMapping("/orderItems")
    public ResponseEntity<OrderItem> create(@RequestBody OrderItemCreationUpdateRequest orderItem) {
        OrderItem createdOrderItem = orderItemService.create(orderItem);
        return ResponseEntity.ok(createdOrderItem);
    }

    @PostMapping("/orderItems/addProduct")
    @Transactional
    public ResponseEntity<OrderItemResponse> addProduct(@RequestBody OrderItemCreationUpdateRequest orderItem,
                                                        @AuthenticationPrincipal DefaultUserDetails userDetails) {
        OrderItemResponse createdOrderItem = orderItemService.addProduct(orderItem, userDetails.getId());

        return ResponseEntity.ok(createdOrderItem);
    }

    @PutMapping("/orderItems/{orderItemId}")
    public ResponseEntity<OrderItemResponse> update(@PathVariable(value = "orderItemId") UUID orderItemId,
                                            @RequestBody OrderItemCreationUpdateRequest orderItem) {
        OrderItemResponse updatedOrderItem = orderItemService.update(orderItemId, orderItem);
        return ResponseEntity.ok(updatedOrderItem);
    }


    @DeleteMapping("/orderItems/{orderItemId}")
    public ResponseEntity<Void> delete(@PathVariable(value = "orderItemId") UUID orderItemId) {
        orderItemService.delete(orderItemId);
        return ResponseEntity.ok().build();
    }



}
