package ua.edu.lnu.shop_api.dto.user;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * DTO for {@link ua.edu.lnu.shop_api.entity.User}
 */
public record UserCreationUpdateRequest(String firstName, String lastName, String email, String password,
                                        Date birthDate, UUID roleId) implements Serializable {
}