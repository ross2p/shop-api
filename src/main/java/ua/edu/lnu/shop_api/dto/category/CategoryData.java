package ua.edu.lnu.shop_api.dto.category;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * DTO for {@link ua.edu.lnu.shop_api.entity.Category}
 */
public record CategoryData(UUID id, String name, String description, Date createdAt, Date updatedAt,
                          Date deletedAt) implements Serializable {
}