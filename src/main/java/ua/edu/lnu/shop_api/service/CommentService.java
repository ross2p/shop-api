package ua.edu.lnu.shop_api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ua.edu.lnu.shop_api.dto.comment.CommentCreationUpdateRequest;
import ua.edu.lnu.shop_api.dto.comment.CommentResponse;
import ua.edu.lnu.shop_api.entity.Comment;
import ua.edu.lnu.shop_api.repository.CommentRepository;

import java.util.UUID;

public interface CommentService {
    Comment create(CommentCreationUpdateRequest comment, UUID userId);

    Comment update(UUID commentId, CommentCreationUpdateRequest comment);

    Comment findById(UUID commentId);

    CommentResponse findDtoById(UUID commentId);

    Page<CommentResponse> findAll(PageRequest pageRequest, UUID productId);

    void delete(UUID commentId);
}
