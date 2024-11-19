package ua.edu.lnu.shop_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.lnu.shop_api.entity.Image;

import java.util.List;
import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID> {
    List<Image> findByProduct_Id(UUID id);
}