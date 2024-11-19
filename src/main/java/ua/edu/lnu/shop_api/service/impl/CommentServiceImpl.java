package ua.edu.lnu.shop_api.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.edu.lnu.shop_api.dto.comment.CommentCreationUpdateRequest;
import ua.edu.lnu.shop_api.dto.comment.CommentResponse;
import ua.edu.lnu.shop_api.entity.Comment;
import ua.edu.lnu.shop_api.mapper.CommentMapper;
import ua.edu.lnu.shop_api.repository.CommentRepository;
import ua.edu.lnu.shop_api.service.CommentService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;


    @Override
    public Comment create(CommentCreationUpdateRequest comment, UUID userId) {
        Comment commentToCreate = commentMapper.toEntity(comment, userId);
        if(comment.parentId() == null) {
            commentToCreate.setParent(null);
        }
        return commentRepository.save(commentToCreate);
    }

    @Override
    public Comment update(UUID commentId, CommentCreationUpdateRequest comment) {
        Comment existingComment = this.findById(commentId);
        Comment commentToUpdate = commentMapper.partialUpdate(comment, existingComment);
        return commentRepository.save(commentToUpdate);
    }

    @Override
    public Comment findById(UUID commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
    }
    public CommentResponse findDtoById(UUID commentId) {
        return commentMapper.toDto(this.findById(commentId));
    }

    @Override
    public Page<CommentResponse> findAll(PageRequest pageRequest, UUID productId) {
        return commentRepository.findByProduct_IdAndParentOrderByCreatedAtDesc(productId,null, pageRequest).map(commentMapper::toDto);
    }

    @Override
    public void delete(UUID commentId) {
        //todo implement
    }
}
