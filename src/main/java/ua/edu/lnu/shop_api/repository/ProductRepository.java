package ua.edu.lnu.shop_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.lnu.shop_api.entity.Product;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Page<Product> findByNameLikeOrDescriptionLikeOrBarcodeLikeOrCategory_NameLikeOrCategory_DescriptionLikeAllIgnoreCase(String name, String description, String barcode, String name1, String description1, Pageable pageable);

}