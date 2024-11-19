package ua.edu.lnu.shop_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.edu.lnu.shop_api.dto.category.CategoryCreationUpdateRequest;
import ua.edu.lnu.shop_api.dto.category.CategoryData;
import ua.edu.lnu.shop_api.dto.category.CategoryResponse;
import ua.edu.lnu.shop_api.entity.Category;
import ua.edu.lnu.shop_api.mapper.CategoryMapper;
import ua.edu.lnu.shop_api.mapper.ProductMapper;
import ua.edu.lnu.shop_api.repository.CategoryRepository;
import ua.edu.lnu.shop_api.service.CategoryService;
import ua.edu.lnu.shop_api.service.ProductService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final ProductMapper productMapper;

    @Override
    public Category findById(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
    @Override
    public CategoryResponse findDtoById(UUID id) {
        return productMapper.toDto(this.findById(id));
    }

    @Override
    public Page<CategoryResponse> findAll(PageRequest pageRequest) {
        return categoryRepository.findAll(pageRequest).map(productMapper::toDto);
    }

    @Override
    public List<CategoryData> findAll() {
        return categoryRepository.findAll().stream().map(categoryMapper::toDto1).toList();
    }


    @Override
    public Category create(CategoryCreationUpdateRequest category) {
        Category categoryToCreate = categoryMapper.toEntity(category);
        return categoryRepository.save(categoryToCreate);
    }

    @Override
    public Category update(UUID id, CategoryCreationUpdateRequest category) {
        Category existingCategory = this.findById(id);
        Category categoryToUpdate = categoryMapper.partialUpdate(category, existingCategory);

        return categoryRepository.save(categoryToUpdate);
    }

    @Override
    public void delete(UUID id) {
        //todo implement
    }

}
