package ua.edu.lnu.shop_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.lnu.shop_api.entity.Promotion;

import java.util.UUID;

public interface PromotionRepository extends JpaRepository<Promotion, UUID> {
}