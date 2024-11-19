package ua.edu.lnu.shop_api.dto.promotion;

import ua.edu.lnu.shop_api.entity.PromotionType;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link ua.edu.lnu.shop_api.entity.Promotion}
 */
public record PromotionCreationUpdateRequest(String name, String description, PromotionType type, Date startDate, Date endDate,
                                             Double discount, Integer amount,  Integer maxAmount) implements Serializable {
}