package ua.edu.lnu.shop_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.edu.lnu.shop_api.dto.promotion.PromotionCreationUpdateRequest;
import ua.edu.lnu.shop_api.entity.Promotion;
import ua.edu.lnu.shop_api.service.PromotionService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PromotionController {
    private final PromotionService promotionService;

    @GetMapping("/promotions")
    public ResponseEntity<Page<Promotion>> findAll(@RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
                                                   @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                   @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy) {

        Page<Promotion> promotions = promotionService.findAll(PageRequest.of(offset, pageSize, Sort.by(sortBy)));
        return ResponseEntity.ok(promotions);
    }

    @GetMapping("/promotions/{promotionId}")
    public ResponseEntity<Promotion> findById(@PathVariable(value = "promotionId") UUID promotionId) {
        Promotion promotion = promotionService.findById(promotionId);
        return ResponseEntity.ok(promotion);
    }

    @PostMapping("/promotions")
    public ResponseEntity<Promotion> create(@RequestBody PromotionCreationUpdateRequest promotion) {
        Promotion createdPromotion = promotionService.create(promotion);
        return ResponseEntity.ok(createdPromotion);
    }

    @PutMapping("/promotions/{promotionId}")
    public ResponseEntity<Promotion> update(@PathVariable(value = "promotionId") UUID promotionId,
                                            @RequestBody PromotionCreationUpdateRequest promotion) {
        Promotion updatedPromotion = promotionService.update(promotionId, promotion);
        return ResponseEntity.ok(updatedPromotion);
    }

    @DeleteMapping("/promotions/{promotionId}")
    public ResponseEntity<Void> delete(@PathVariable(value = "promotionId") UUID promotionId) {
        promotionService.delete(promotionId);
        return ResponseEntity.ok().build();
    }

}
