package ua.edu.lnu.shop_api.dto.role;

import java.io.Serializable;

/**
 * DTO for {@link ua.edu.lnu.shop_api.entity.Role}
 */
public record RoleCreationUpdateRequest(String name, String description) implements Serializable {
}