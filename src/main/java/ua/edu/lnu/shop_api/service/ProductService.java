package ua.edu.lnu.shop_api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;
import ua.edu.lnu.shop_api.dto.product.ProductCreationUpdateRequest;
import ua.edu.lnu.shop_api.dto.product.ProductData;
import ua.edu.lnu.shop_api.dto.product.ProductResponse;
import ua.edu.lnu.shop_api.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    Product findById(UUID id);

    Page<ProductResponse> findAll(PageRequest pageRequest, String search);

    Product create(ProductCreationUpdateRequest product);

    ProductResponse update(UUID id, ProductCreationUpdateRequest product, UUID userId);

    void delete(UUID id);

    ProductResponse findDtoById(UUID productId);
}
