package ua.edu.lnu.shop_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.edu.lnu.shop_api.dto.role.RoleCreationUpdateRequest;
import ua.edu.lnu.shop_api.dto.role.RoleResponse;
import ua.edu.lnu.shop_api.entity.Product;
import ua.edu.lnu.shop_api.entity.Role;
import ua.edu.lnu.shop_api.service.RoleService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RoleController {
    private final RoleService roleService;

    @GetMapping("/role")
    public ResponseEntity<Page<Role>> findAll(@RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
                                                 @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                 @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy) {

        Page<Role> products = roleService.findAll(PageRequest.of(offset, pageSize, Sort.by(sortBy)));
        return ResponseEntity.ok(products);
    }

    @PostMapping("/role")
    public ResponseEntity<Role> create(@RequestBody RoleCreationUpdateRequest role) {
        Role createdRole = roleService.create(role);
        return ResponseEntity.ok(createdRole);
    }

    @GetMapping("/role/{roleId}")
    public ResponseEntity<Role> findById(@PathVariable(value = "roleId") UUID roleId) {
        Role role = roleService.findById(roleId);
        return ResponseEntity.ok(role);
    }

    @PutMapping("/role/{roleId}")
    public ResponseEntity<Role> update(@PathVariable(value = "roleId") UUID roleId,
                                        @RequestBody RoleCreationUpdateRequest role) {
        Role updatedRole = roleService.update(roleId, role);
        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping("/role/{roleId}")
    public ResponseEntity<Void> delete(@PathVariable(value = "roleId") UUID roleId) {
        roleService.delete(roleId);
        return ResponseEntity.ok().build();
    }



}
