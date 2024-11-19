package ua.edu.lnu.shop_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.edu.lnu.shop_api.dto.product.ProductCreationUpdateRequest;
import ua.edu.lnu.shop_api.dto.product.ProductData;
import ua.edu.lnu.shop_api.dto.product.ProductResponse;
import ua.edu.lnu.shop_api.entity.Comment;
import ua.edu.lnu.shop_api.entity.Image;
import ua.edu.lnu.shop_api.entity.Product;
import ua.edu.lnu.shop_api.entity.User;
import ua.edu.lnu.shop_api.mapper.ProductMapper;
import ua.edu.lnu.shop_api.repository.ImageRepository;
import ua.edu.lnu.shop_api.repository.ProductRepository;
import ua.edu.lnu.shop_api.repository.UserRepository;
import ua.edu.lnu.shop_api.service.ProductService;
import ua.edu.lnu.shop_api.service.UserService;

import java.io.IOException;
import java.util.*;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final UserService userService;
    private final ImageRepository imageRepository;

    @Override
    public Product findById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public ProductResponse findDtoById(UUID id) {
        return productMapper.toDto(this.findById(id));
    }


    @Override
    public Page<ProductResponse> findAll(PageRequest pageRequest, String search) {
        return productRepository
                .findByNameLikeOrDescriptionLikeOrBarcodeLikeOrCategory_NameLikeOrCategory_DescriptionLikeAllIgnoreCase(
                        "%" + search + "%", "%" + search + "%", "%" + search + "%", "%" + search + "%", "%" + search + "%",
                        pageRequest)
                .map(productMapper::toDto);
    }



    public Product create(ProductCreationUpdateRequest productDto) {
        Product productToCreate = productMapper.toEntity(productDto);
        Product savedProduct = productRepository.save(productToCreate);

        Set<Image> images = savedProduct.getImages();
        images.forEach(image -> {
            imageRepository.findById(image.getId()).ifPresentOrElse(
                    image1 -> {
                        image1.setProduct(savedProduct);
                        imageRepository.save(image1);
                    },
                    () -> {
                        image.setProduct(savedProduct);
                        imageRepository.save(image);
                    }
            );
        });



        return savedProduct;
    }


    public ProductResponse update(UUID id, ProductCreationUpdateRequest product, UUID userId) {
        Product existingProduct = this.findById(id);
        Product productToUpdate = productMapper.partialUpdate(product, existingProduct);

        // Calculate new rating
        if (product.rating() != null) {
            User existingUser = this.userService.findById(userId);
            Map<User, Double> userRating = existingProduct.getUserRating();

            userRating.put(existingUser, product.rating());
            productToUpdate.setUserRating(userRating);

            userRating.values().stream()
                    .mapToDouble(v -> v)
                    .average()
                    .ifPresent(productToUpdate::setRating);
        }

        Product updatedProduct = productRepository.save(productToUpdate);

        // Null-check before using images()
        if (product.images() != null) {
            product.images().forEach(imageId -> {
                imageRepository.findById(imageId).ifPresentOrElse(
                        image1 -> {
                            image1.setProduct(updatedProduct);
                            imageRepository.save(image1);
                        },
                        () -> {
                            throw new RuntimeException("Image not found");
                        }
                );
            });
        }

        return productMapper.toDto(updatedProduct);
    }

    @Override
    public void delete(UUID id) {
        //todo implement
    }
}
