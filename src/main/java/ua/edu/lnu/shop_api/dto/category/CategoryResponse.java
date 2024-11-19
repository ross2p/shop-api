package ua.edu.lnu.shop_api.dto.category;

import ua.edu.lnu.shop_api.dto.product.ProductData;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link ua.edu.lnu.shop_api.entity.Category}
 */
public record CategoryResponse(UUID id, String name, String description, Set<ProductData> products, Date createdAt,
                               Date updatedAt, Date deletedAt) implements Serializable {
}