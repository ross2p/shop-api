package ua.edu.lnu.shop_api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ua.edu.lnu.shop_api.dto.category.CategoryCreationUpdateRequest;
import ua.edu.lnu.shop_api.dto.category.CategoryData;
import ua.edu.lnu.shop_api.dto.category.CategoryResponse;
import ua.edu.lnu.shop_api.entity.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    Category findById(UUID id);
    CategoryResponse findDtoById(UUID id);
    Page<CategoryResponse> findAll(PageRequest pageRequest);
    List<CategoryData> findAll();
    Category create(CategoryCreationUpdateRequest category);

    Category update(UUID id, CategoryCreationUpdateRequest category);

    void delete(UUID id);
}
