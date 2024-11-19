package ua.edu.lnu.shop_api.mapper;

import org.mapstruct.*;
import ua.edu.lnu.shop_api.dto.role.RoleCreationUpdateRequest;
import ua.edu.lnu.shop_api.entity.Role;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleMapper {
    Role toEntity(RoleCreationUpdateRequest roleCreationUpdateRequest);

    RoleCreationUpdateRequest toDto(Role role);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Role partialUpdate(RoleCreationUpdateRequest roleCreationUpdateRequest, @MappingTarget Role role);
}