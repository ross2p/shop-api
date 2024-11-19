package ua.edu.lnu.shop_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.edu.lnu.shop_api.dto.image.ImageCreationUpdateRequest;
import ua.edu.lnu.shop_api.dto.image.ImageResponse;
import ua.edu.lnu.shop_api.entity.Image;
import ua.edu.lnu.shop_api.entity.Order;
import ua.edu.lnu.shop_api.service.ImageService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ImageController {
    private final ImageService imageService;

    @GetMapping("/images")
    public ResponseEntity<List<Image>> findAll() {
        return null;
    }

    @GetMapping("/images/{imageId}")
    public ResponseEntity<ImageResponse> findById(@PathVariable UUID imageId) {
        ImageResponse imageResponse = imageService.findDtoById(imageId);
        return ResponseEntity.ok(imageResponse);
    }

    @GetMapping("/images/{productId}/product")
    public ResponseEntity<List<Image>> findByProductId(@PathVariable UUID productId) {
        return null;
    }

    @PostMapping(value = "/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Image> create(@RequestPart(value = "file") MultipartFile image,
                                        @RequestPart(value = "body", required = false )ImageCreationUpdateRequest imageCreationUpdateRequest) throws IOException {
        System.out.println(imageCreationUpdateRequest);
        System.out.println(image.getOriginalFilename());
        Image createdImage = imageService.save(image, imageCreationUpdateRequest);
        return ResponseEntity.ok(createdImage);
    }

    @DeleteMapping("/images/{imageId}")
    public ResponseEntity<Void> delete(@PathVariable UUID imageId) {
        imageService.delete(imageId);
        return ResponseEntity.ok().build();
    }

}
