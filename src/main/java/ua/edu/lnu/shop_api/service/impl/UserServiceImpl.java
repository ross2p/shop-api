package ua.edu.lnu.shop_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.edu.lnu.shop_api.dto.order.OrderCreationUpdateRequest;
import ua.edu.lnu.shop_api.dto.user.UserCreationUpdateRequest;
import ua.edu.lnu.shop_api.dto.user.UserResponse;
import ua.edu.lnu.shop_api.entity.User;
import ua.edu.lnu.shop_api.mapper.UserMapper;
import ua.edu.lnu.shop_api.repository.UserRepository;
import ua.edu.lnu.shop_api.service.OrderService;
import ua.edu.lnu.shop_api.service.UserService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final OrderService orderService;

    @Override
    public UserResponse create(UserCreationUpdateRequest userCreationUpdateRequest) {
        User userToCreate = userMapper.toEntity(userCreationUpdateRequest);
        User newUser = userRepository.save(userToCreate);

        orderService.create(newUser.getId());

        return userMapper.toDto(newUser);
    }

    @Override
    public UserResponse update(UUID id, UserCreationUpdateRequest user) {
        User userToUpdate = this.findById(id);
        userToUpdate =  userMapper.partialUpdate(user, userToUpdate);

        User updatedUser = userRepository.save(userToUpdate);

        return userMapper.toDto(updatedUser);
    }

    @Override
    public void delete(UUID id) {
        //todo implement
    }

    @Override
    public UserResponse findDtoById(UUID id) {
        System.out.println("id = " + id);

        User user = this.findById(id);
        System.out.println("user = " );
        return userMapper.toDto(user);
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public Page<User> findAll(PageRequest pageRequest) {
        return userRepository.findAll(pageRequest);
    }
}
