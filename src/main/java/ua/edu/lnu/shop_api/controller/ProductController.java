package ua.edu.lnu.shop_api.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ua.edu.lnu.shop_api.dto.auth.DefaultUserDetails;
import ua.edu.lnu.shop_api.dto.product.ProductCreationUpdateRequest;
import ua.edu.lnu.shop_api.dto.product.ProductData;
import ua.edu.lnu.shop_api.dto.product.ProductResponse;
import ua.edu.lnu.shop_api.dto.user.UserData;
import ua.edu.lnu.shop_api.entity.Product;
import ua.edu.lnu.shop_api.service.ProductService;
import org.springframework.web.multipart.MultipartFile;


import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;
    @GetMapping("/products")
    @Transactional
    public ResponseEntity<Page<ProductResponse>> findAll(@RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
                                                         @RequestParam(value = "pageSize", required = false, defaultValue = "24") Integer pageSize,
                                                         @RequestParam(value = "sortBy", required = false, defaultValue = "name") String sortBy,
                                                         @RequestParam(value = "search", required = false, defaultValue = "") String search) {

        Page<ProductResponse> products = productService.findAll(PageRequest.of(offset, pageSize, Sort.by(sortBy)), search);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products/{productId}")
    @Transactional
    public ResponseEntity<ProductResponse> findById(@PathVariable(value = "productId") UUID productId) {
        ProductResponse product = productService.findDtoById(productId);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> create(@RequestBody ProductCreationUpdateRequest productRequest) {
        Product createdProduct = productService.create(productRequest);
        return ResponseEntity.ok(createdProduct);
    }



    @PutMapping("/products/{productId}")
    @Transactional
    public ResponseEntity<ProductResponse> update(@PathVariable(value = "productId") UUID productId,
                                          @RequestBody ProductCreationUpdateRequest product,
                                          @AuthenticationPrincipal DefaultUserDetails userDetails) {
        System.out.println();
        ProductResponse updatedProduct = productService.update(productId, product, userDetails.getId());
        return ResponseEntity.ok(updatedProduct);
    }
    

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Void> delete(@PathVariable(value = "productId") UUID productId) {
        productService.delete(productId);
        return ResponseEntity.ok().build();
    }
}
