package ua.edu.lnu.shop_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.edu.lnu.shop_api.dto.user.UserCreationUpdateRequest;
import ua.edu.lnu.shop_api.dto.user.UserResponse;
import ua.edu.lnu.shop_api.entity.User;
import ua.edu.lnu.shop_api.repository.UserRepository;
import ua.edu.lnu.shop_api.service.UserService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<Page<User>> findAll(@RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
                                              @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                              @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy) {

        Page<User> users = userService.findAll(PageRequest.of(offset, pageSize, Sort.by(sortBy)));
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponse> findById(@PathVariable(value = "userId") UUID userId) {
        UserResponse user = userService.findDtoById(userId);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<UserResponse> update(@PathVariable(value = "userId") UUID userId,
                                        @RequestBody UserCreationUpdateRequest user) {
        UserResponse updatedUser = userService.update(userId, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> delete(@PathVariable(value = "userId") UUID userId) {
        userService.delete(userId);
        return ResponseEntity.ok().build();
    }
}
