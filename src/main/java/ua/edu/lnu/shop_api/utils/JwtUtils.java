package ua.edu.lnu.shop_api.utils;

import ua.edu.lnu.shop_api.entity.Role;

import java.util.UUID;

public interface JwtUtils {
    String generateToken(UUID id, String username, Role role);

    boolean validateToken(String token);

    String getSubject(String token);
}
