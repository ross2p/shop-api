package ua.edu.lnu.shop_api.dto.role;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * DTO for {@link ua.edu.lnu.shop_api.entity.Role}
 */
public record RoleResponse(UUID id, String name, String description, Date createdAt, Date updatedAt,
                           Date deletedAt) implements Serializable {
}