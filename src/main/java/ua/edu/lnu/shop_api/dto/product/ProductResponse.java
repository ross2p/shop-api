package ua.edu.lnu.shop_api.dto.product;

import ua.edu.lnu.shop_api.dto.category.CategoryData;
import ua.edu.lnu.shop_api.dto.comment.CommentResponse;
import ua.edu.lnu.shop_api.dto.image.ImageResponse;
import ua.edu.lnu.shop_api.dto.user.UserData;
import ua.edu.lnu.shop_api.dto.user.UserRating;
import ua.edu.lnu.shop_api.entity.Image;
import ua.edu.lnu.shop_api.entity.Promotion;
import ua.edu.lnu.shop_api.entity.User;

import java.io.Serializable;
import java.util.*;

/**
 * DTO for {@link ua.edu.lnu.shop_api.entity.Product}
 */
public record ProductResponse(UUID id, String name, String description, String barcode, Double price, Double rating,
                              CategoryData category, Map<String, String>  characteristic, Set<UserRating> userRating,
                              Set<CommentResponse> comments, Date createdAt, Date updatedAt,
                              Date deletedAt, Set<ImageResponse> images, Promotion promotion) implements Serializable {
}