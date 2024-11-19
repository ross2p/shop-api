package ua.edu.lnu.shop_api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ua.edu.lnu.shop_api.dto.promotion.PromotionCreationUpdateRequest;
import ua.edu.lnu.shop_api.entity.Promotion;

import java.util.UUID;

public interface PromotionService {
    Promotion create(PromotionCreationUpdateRequest promotion);

    Promotion update(UUID id, PromotionCreationUpdateRequest promotion);

    void delete(UUID id);

    Promotion findById(UUID id);

    Page<Promotion> findAll(PageRequest pageRequest);
}
