package ua.edu.lnu.shop_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.edu.lnu.shop_api.dto.image.ImageCreationUpdateRequest;
import ua.edu.lnu.shop_api.dto.image.ImageResponse;
import ua.edu.lnu.shop_api.entity.Image;
import ua.edu.lnu.shop_api.mapper.ImageMapper;
import ua.edu.lnu.shop_api.repository.ImageRepository;
import ua.edu.lnu.shop_api.service.ImageService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;


    @Override
    public Image save(MultipartFile image, ImageCreationUpdateRequest imageCreationUpdateRequest) throws IOException {
        Image imageToSave = imageMapper.toEntity(imageCreationUpdateRequest, image);
        return imageRepository.save(imageToSave);
    }

    @Override
    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public List<Image> findAllByProductId(UUID productId) {
        return imageRepository.findByProduct_Id(productId);
    }

    @Override
    public Image update(UUID imageId, ImageCreationUpdateRequest imageCreationUpdateRequest) {
        Image existingImage = this.findById(imageId);
        Image imageToUpdate = imageMapper.partialUpdate(imageCreationUpdateRequest, existingImage);

        return imageRepository.save(imageToUpdate);
    }

    @Override
    public void delete(UUID imageId) {
        imageRepository.deleteById(imageId);
    }

    @Override
    public Image findById(UUID imageId) {
        return imageRepository.findById(imageId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public ImageResponse findDtoById(UUID imageId) {
        return imageMapper.toDto1(this.findById(imageId));
    }
}
