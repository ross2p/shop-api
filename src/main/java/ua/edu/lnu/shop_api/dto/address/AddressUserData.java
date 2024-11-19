package ua.edu.lnu.shop_api.dto.address;

import ua.edu.lnu.shop_api.dto.user.UserData;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * DTO for {@link ua.edu.lnu.shop_api.entity.Address}
 */
public record AddressUserData(UUID id, String country, String city, String street, String building, String apartment,
                              String postcode, UserData user, Date createdAt, Date updatedAt,
                              Date deletedAt) implements Serializable {
}