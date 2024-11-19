package ua.edu.lnu.shop_api.dto.user;

import ua.edu.lnu.shop_api.entity.Role;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * DTO for {@link ua.edu.lnu.shop_api.entity.User}
 */
public record UserRating(UUID id, String firstName, String lastName, String email, String password, Date birthDate,
                         Role role, Date createdAt, Date updatedAt, Date deletedAt, Double rating) implements Serializable {
}