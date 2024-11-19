package ua.edu.lnu.shop_api.service;

import org.springframework.web.multipart.MultipartFile;
import ua.edu.lnu.shop_api.dto.image.ImageCreationUpdateRequest;
import ua.edu.lnu.shop_api.dto.image.ImageResponse;
import ua.edu.lnu.shop_api.entity.Image;
import ua.edu.lnu.shop_api.repository.ImageRepository;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface ImageService {
    Image save(MultipartFile images, ImageCreationUpdateRequest imageCreationUpdateRequest) throws IOException;
    List<Image> findAll();
    List<Image> findAllByProductId(UUID productId);
    Image update(UUID id, ImageCreationUpdateRequest imageCreationUpdateRequest);
    void delete(UUID imageId);
    Image findById(UUID imageId);
    ImageResponse findDtoById(UUID imageId);

}
