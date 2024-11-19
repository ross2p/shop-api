package ua.edu.lnu.shop_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import ua.edu.lnu.shop_api.entity.Comment;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {

    Page<Comment> findByProduct_IdAndParentOrderByCreatedAtDesc(UUID productId, @Nullable Comment parent, Pageable pageable);
}