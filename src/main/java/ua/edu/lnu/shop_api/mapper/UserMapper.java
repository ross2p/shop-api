package ua.edu.lnu.shop_api.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ua.edu.lnu.shop_api.config.AuthComponent;
import ua.edu.lnu.shop_api.dto.user.UserCreationUpdateRequest;
import ua.edu.lnu.shop_api.dto.user.UserData;
import ua.edu.lnu.shop_api.dto.user.UserRating;
import ua.edu.lnu.shop_api.dto.user.UserResponse;
import ua.edu.lnu.shop_api.entity.User;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {AuthComponent.class})
public interface UserMapper {


    User toEntity(UserResponse userResponse);

    @AfterMapping
    default void linkAddresses(@MappingTarget User user) {
        user.getAddresses().forEach(address -> address.setUser(user));
    }


    UserResponse toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserResponse userResponse, @MappingTarget User user);

    @Mapping(source = "roleId", target = "role.id")
    @Mapping(target = "password", source = "password", qualifiedByName = "encodedPassword")
    User toEntity(UserCreationUpdateRequest userCreationUpdateRequest);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "roleId", target = "role.id")
    @Mapping(target = "password", source = "password", qualifiedByName = "encodedPassword")
    User partialUpdate(UserCreationUpdateRequest userCreationUpdateRequest, @MappingTarget User user);

    User toEntity(UserData userData);

    UserData toDto1(User user);

    User toEntity(UserRating userRating);

    @Mapping(target = "rating", source = "rating")
    UserRating toDto(User user, Double rating);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserData userData, @MappingTarget User user);
}