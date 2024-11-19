package ua.edu.lnu.shop_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.edu.lnu.shop_api.dto.category.CategoryCreationUpdateRequest;
import ua.edu.lnu.shop_api.dto.category.CategoryData;
import ua.edu.lnu.shop_api.dto.category.CategoryResponse;
import ua.edu.lnu.shop_api.entity.Category;
import ua.edu.lnu.shop_api.entity.Product;
import ua.edu.lnu.shop_api.service.CategoryService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/category")
    public ResponseEntity<List<CategoryData>> findAll() {
        List<CategoryData> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable(value = "categoryId") UUID categoryId) {
        CategoryResponse category = categoryService.findDtoById(categoryId);
        return ResponseEntity.ok(category);
    }

    @PostMapping("/category")
    public ResponseEntity<Category> create(@RequestBody CategoryCreationUpdateRequest category) {
        Category createdCategory = categoryService.create(category);
        return ResponseEntity.ok(createdCategory);
    }
    @PutMapping("/category/{categoryId}")
    public ResponseEntity<Category> update(@PathVariable(value = "categoryId") UUID categoryId,
                                          @RequestBody CategoryCreationUpdateRequest category) {
        Category updatedCategory = categoryService.update(categoryId, category);
        return ResponseEntity.ok(updatedCategory);
    }
    @DeleteMapping("/category/{categoryId}")
    public ResponseEntity<Void> delete(@PathVariable(value = "categoryId") UUID categoryId) {
        categoryService.delete(categoryId);
        return ResponseEntity.ok().build();
    }
}
