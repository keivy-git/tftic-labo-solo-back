package be.keivy.fleamarketapp.common.mappers.role;

import be.keivy.fleamarketapp.common.dtos.role.requests.RoleRequest;
import be.keivy.fleamarketapp.common.dtos.role.responses.RoleResponse;
import be.keivy.fleamarketapp.domain.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Role toEntity(RoleRequest roleRequest);

    RoleResponse fromEntity(Role role);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromRequest(RoleRequest roleRequest, @MappingTarget Role role);
}
