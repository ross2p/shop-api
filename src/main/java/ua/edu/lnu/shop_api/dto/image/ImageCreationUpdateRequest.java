package ua.edu.lnu.shop_api.dto.image;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link ua.edu.lnu.shop_api.entity.Image}
 */
public record ImageCreationUpdateRequest(UUID productId) implements Serializable {
}