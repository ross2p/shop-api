package ua.edu.lnu.shop_api.dto.image;

import jakarta.persistence.Lob;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link ua.edu.lnu.shop_api.entity.Image}
 */
public record ImageResponse(UUID id, String name, String contentType, Long size, String originalFilename,
                            @Lob byte[] data) implements Serializable {
}