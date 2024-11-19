package ua.edu.lnu.shop_api.dto.orderItem;

import ua.edu.lnu.shop_api.dto.order.OrderData;
import ua.edu.lnu.shop_api.dto.product.ProductData;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * DTO for {@link ua.edu.lnu.shop_api.entity.OrderItem}
 */
public record OrderItemResponse(UUID id, ProductData product, int quantity, double price, OrderData order,
                                Date createdAt, Date updatedAt, Date deletedAt) implements Serializable {
}