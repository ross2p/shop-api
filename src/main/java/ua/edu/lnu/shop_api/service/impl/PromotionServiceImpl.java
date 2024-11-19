package ua.edu.lnu.shop_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.edu.lnu.shop_api.dto.promotion.PromotionCreationUpdateRequest;
import ua.edu.lnu.shop_api.entity.Promotion;
import ua.edu.lnu.shop_api.mapper.PromotionMapper;
import ua.edu.lnu.shop_api.repository.PromotionRepository;
import ua.edu.lnu.shop_api.service.PromotionService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {
    private final PromotionRepository promotionRepository;
    private final PromotionMapper promotionMapper;

    @Override
    public Promotion create(PromotionCreationUpdateRequest promotion) {
        System.out.println(promotion);
        Promotion promotionToCreate = promotionMapper.toEntity(promotion);
        System.out.println(promotionToCreate);
        return promotionRepository.save(promotionToCreate);
    }

    @Override
    public Promotion update(UUID id, PromotionCreationUpdateRequest promotion) {
        Promotion existingPromotion = this.findById(id);
        Promotion promotionToUpdate = promotionMapper.partialUpdate(promotion, existingPromotion);

        return promotionRepository.save(promotionToUpdate);
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public Promotion findById(UUID id) {
        return promotionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Promotion not found"));
    }
    @Override
    public Page<Promotion> findAll(PageRequest pageRequest) {
        return promotionRepository.findAll(pageRequest);
    }
}
