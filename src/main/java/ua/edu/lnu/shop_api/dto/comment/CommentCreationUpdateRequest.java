package ua.edu.lnu.shop_api.dto.comment;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link ua.edu.lnu.shop_api.entity.Comment}
 */
public record CommentCreationUpdateRequest(String text, UUID productId,
                                           UUID parentId) implements Serializable {
}