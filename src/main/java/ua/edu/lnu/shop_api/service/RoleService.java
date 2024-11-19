package ua.edu.lnu.shop_api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ua.edu.lnu.shop_api.dto.role.RoleCreationUpdateRequest;
import ua.edu.lnu.shop_api.dto.role.RoleResponse;
import ua.edu.lnu.shop_api.entity.Role;

import java.util.UUID;

public interface RoleService {
    Role create(RoleCreationUpdateRequest role);

    Role update(UUID id, RoleCreationUpdateRequest role);

    void delete(UUID id);

    Role findById(UUID id);

    Page<Role> findAll(PageRequest pageRequest);
}
