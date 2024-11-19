package ua.edu.lnu.shop_api.dto.comment;

import ua.edu.lnu.shop_api.dto.user.UserData;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link ua.edu.lnu.shop_api.entity.Comment}
 */
public record CommentResponse(UUID id, String text, UserData user, Date createdAt, Date updatedAt,
                              Date deletedAt, Set<CommentResponse> children) implements Serializable {
}