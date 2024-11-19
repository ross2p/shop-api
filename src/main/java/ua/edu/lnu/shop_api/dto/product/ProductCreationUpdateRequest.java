package ua.edu.lnu.shop_api.dto.product;

import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link ua.edu.lnu.shop_api.entity.Product}
 */
public record ProductCreationUpdateRequest(String name, String description, String barcode, Double price,
                                           UUID categoryId, Map<String, String> characteristic, Double rating,
                                           Set<UUID> images, UUID promotionId) implements Serializable {
}