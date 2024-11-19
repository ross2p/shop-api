package ua.edu.lnu.shop_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.lnu.shop_api.entity.Category;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}