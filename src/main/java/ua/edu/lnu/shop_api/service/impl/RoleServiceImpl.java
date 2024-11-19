package ua.edu.lnu.shop_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.edu.lnu.shop_api.dto.role.RoleCreationUpdateRequest;
import ua.edu.lnu.shop_api.dto.role.RoleResponse;
import ua.edu.lnu.shop_api.entity.Role;
import ua.edu.lnu.shop_api.mapper.RoleMapper;
import ua.edu.lnu.shop_api.repository.RoleRepository;
import ua.edu.lnu.shop_api.service.RoleService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    @Override
    public Page<Role> findAll(PageRequest pageRequest) {
        return roleRepository.findAll(pageRequest);
    }

    @Override
    public Role create(RoleCreationUpdateRequest roleCreationUpdateRequest) {
        Role roleToCreate = roleMapper.toEntity(roleCreationUpdateRequest);
        return roleRepository.save(roleToCreate);
    }

    @Override
    public Role update(UUID id, RoleCreationUpdateRequest role) {
        Role existingRole = this.findById(id);
        Role roleToUpdate = roleMapper.partialUpdate(role, existingRole);

        return roleRepository.save(roleToUpdate);
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public Role findById(UUID id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }

}
