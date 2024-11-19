package ua.edu.lnu.shop_api.dto.user;

import ua.edu.lnu.shop_api.dto.address.AddressResponse;
import ua.edu.lnu.shop_api.dto.order.OrderResponse;
import ua.edu.lnu.shop_api.dto.role.RoleResponse;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link ua.edu.lnu.shop_api.entity.User}
 */
public record UserResponse(UUID id, String firstName, String lastName, String email, Date birthDate, RoleResponse role,
                           Set<AddressResponse> addresses, Date createdAt, Date updatedAt,
                           Date deletedAt, Set<OrderResponse> history) implements Serializable {
}