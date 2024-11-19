package ua.edu.lnu.shop_api.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ua.edu.lnu.shop_api.dto.auth.DefaultUserDetails;
import ua.edu.lnu.shop_api.dto.comment.CommentCreationUpdateRequest;
import ua.edu.lnu.shop_api.dto.comment.CommentResponse;
import ua.edu.lnu.shop_api.entity.Comment;
import ua.edu.lnu.shop_api.service.CommentService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;



    @GetMapping("/product/{productId}/comment")
    public ResponseEntity<Page<CommentResponse>> findAll(@RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
                                                 @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                 @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
                                                 @PathVariable UUID productId) {

        Page<CommentResponse> comments = commentService.findAll(PageRequest.of(offset, pageSize, Sort.by(sortBy)), productId);
        return ResponseEntity.ok(comments);
    }
    @GetMapping("/comment/{commentId}")
    public ResponseEntity<CommentResponse> findById(@PathVariable() UUID commentId) {
        CommentResponse comment = commentService.findDtoById(commentId);
        return ResponseEntity.ok(comment);
    }

    @PostMapping("/comment")
    public ResponseEntity<Comment> create(@RequestBody CommentCreationUpdateRequest comment,
                                          @AuthenticationPrincipal DefaultUserDetails userDetails) {
        System.out.println(userDetails.getId());

        Comment createdComment = commentService.create(comment, userDetails.getId());
        System.out.println(createdComment);
        return ResponseEntity.ok(createdComment);
    }


    @PutMapping("/comment/{commentId}")
    public ResponseEntity<Comment> update(@PathVariable(value = "commentId") UUID commentId,
                                          @RequestBody CommentCreationUpdateRequest comment) {
        Comment updatedComment = commentService.update(commentId, comment);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<Void> delete(@PathVariable(value = "commentId") UUID commentId) {
        commentService.delete(commentId);
        return ResponseEntity.ok().build();
    }


}
