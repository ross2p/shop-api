package ua.edu.lnu.shop_api.dto.orderItem;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link ua.edu.lnu.shop_api.entity.OrderItem}
 */
public record OrderItemCreationUpdateRequest(UUID productId, int quantity, UUID orderId) implements Serializable {
}