package ua.edu.lnu.shop_api.dto.address;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * DTO for {@link ua.edu.lnu.shop_api.entity.Address}
 */
public record AddressResponse(UUID id, String country, String city, String street, String building, String apartment,
                              String postcode, Date createdAt, Date updatedAt, Date deletedAt) implements Serializable {
}