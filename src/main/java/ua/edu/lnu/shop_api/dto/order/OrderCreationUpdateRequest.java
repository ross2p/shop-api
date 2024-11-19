package ua.edu.lnu.shop_api.dto.order;

import ua.edu.lnu.shop_api.entity.Status;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link ua.edu.lnu.shop_api.entity.Order}
 */
public record OrderCreationUpdateRequest(Status status, UUID addressId) implements Serializable {
}