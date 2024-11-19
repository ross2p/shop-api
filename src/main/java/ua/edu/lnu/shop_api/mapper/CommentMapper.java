package ua.edu.lnu.shop_api.mapper;

import org.mapstruct.*;
import ua.edu.lnu.shop_api.dto.comment.CommentCreationUpdateRequest;
import ua.edu.lnu.shop_api.dto.comment.CommentResponse;
import ua.edu.lnu.shop_api.entity.Comment;

import java.util.UUID;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserMapper.class})
public interface CommentMapper {

    @Mapping(source = "commentCreationUpdateRequest.productId", target = "product.id")
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "commentCreationUpdateRequest.parentId", target = "parent.id")
    Comment toEntity(CommentCreationUpdateRequest commentCreationUpdateRequest, UUID userId);



    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Comment partialUpdate(CommentCreationUpdateRequest commentCreationUpdateRequest, @MappingTarget Comment comment);

    Comment toEntity(CommentResponse commentResponse);

    CommentResponse toDto(Comment comment);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Comment partialUpdate(CommentResponse commentResponse, @MappingTarget Comment comment);
}