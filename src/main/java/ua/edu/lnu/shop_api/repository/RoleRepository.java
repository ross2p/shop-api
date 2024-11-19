package ua.edu.lnu.shop_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.lnu.shop_api.entity.Role;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
}