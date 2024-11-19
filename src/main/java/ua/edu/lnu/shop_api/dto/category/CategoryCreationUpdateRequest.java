package ua.edu.lnu.shop_api.dto.category;

import lombok.Value;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link ua.edu.lnu.shop_api.entity.Category}
 */
public record CategoryCreationUpdateRequest(String name, String description,
                                            Set<UUID> productIds) implements Serializable {
}