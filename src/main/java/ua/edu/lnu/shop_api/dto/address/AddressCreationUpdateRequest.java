package ua.edu.lnu.shop_api.dto.address;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link ua.edu.lnu.shop_api.entity.Address}
 */
public record AddressCreationUpdateRequest(String country, String city, String street, String building,
                                           String apartment, String postcode, UUID userId) implements Serializable {
}