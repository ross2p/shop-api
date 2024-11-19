package ua.edu.lnu.shop_api.dto.product;

import ua.edu.lnu.shop_api.dto.image.ImageResponse;
import ua.edu.lnu.shop_api.dto.user.UserData;
import ua.edu.lnu.shop_api.dto.user.UserRating;
import ua.edu.lnu.shop_api.entity.Image;
import ua.edu.lnu.shop_api.entity.Promotion;

import java.io.Serializable;
import java.util.*;

/**
 * DTO for {@link ua.edu.lnu.shop_api.entity.Product}
 */
public record  ProductData(UUID id, String name, String description, String barcode, Double price, Double rating,
                           Map<String, String>  characteristic, Set<UserRating> userRating, Date createdAt,
                           Date updatedAt, Date deletedAt, Set<Image> images, Promotion promotion) implements Serializable {
}