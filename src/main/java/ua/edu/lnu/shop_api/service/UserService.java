package ua.edu.lnu.shop_api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ua.edu.lnu.shop_api.dto.user.UserCreationUpdateRequest;
import ua.edu.lnu.shop_api.dto.user.UserResponse;
import ua.edu.lnu.shop_api.entity.User;

import java.util.UUID;

public interface UserService {
    UserResponse create(UserCreationUpdateRequest user);

    UserResponse update(UUID id, UserCreationUpdateRequest user);

    void delete(UUID id);

    UserResponse findDtoById(UUID id);

    User findById(UUID id);

    Page<User> findAll(PageRequest pageRequest);
}
