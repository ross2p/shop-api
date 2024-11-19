package ua.edu.lnu.shop_api.dto.order;

import ua.edu.lnu.shop_api.entity.Status;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * DTO for {@link ua.edu.lnu.shop_api.entity.Order}
 */
public record OrderResponse(UUID id, Status status, Date orderDate, double totalAmount, Date createdAt, Date updatedAt,
                            Date deletedAt) implements Serializable {
}