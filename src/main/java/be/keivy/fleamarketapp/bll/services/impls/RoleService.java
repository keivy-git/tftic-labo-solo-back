package be.keivy.fleamarketapp.bll.services.impls;

import be.keivy.fleamarketapp.bll.services.IRoleService;
import be.keivy.fleamarketapp.common.dtos.role.requests.RoleRequest;
import be.keivy.fleamarketapp.common.dtos.role.responses.RoleResponse;
import be.keivy.fleamarketapp.common.exceptions.AlreadyExistsException;
import be.keivy.fleamarketapp.common.exceptions.role.RoleNotFoundException;
import be.keivy.fleamarketapp.common.mappers.role.RoleMapper;
import be.keivy.fleamarketapp.dal.repositories.RoleRepository;
import be.keivy.fleamarketapp.domain.entities.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;


    @Override
    public List<RoleResponse> getAll() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::fromEntity)
                .toList();
    }

    @Override
    public RoleResponse getByName(String name) {
        Role role = roleRepository.findByName(name).orElseThrow(RoleNotFoundException::new);
        return roleMapper.fromEntity(role);
    }

    @Override
    public RoleResponse getById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(RoleNotFoundException::new);
        return roleMapper.fromEntity(role);
    }

    @Override
    public RoleResponse create(RoleRequest roleRequest) {
        if (roleRepository.findByName(roleRequest.name()).isPresent()) {
            throw new AlreadyExistsException("Role existe déjà : " + roleRequest.name());
        }

        Role role = roleMapper.toEntity(roleRequest);

        return roleMapper.fromEntity(roleRepository.save(role));
    }

    @Override
    public RoleResponse update(Long id, RoleRequest roleRequest) {
        Role role = roleRepository.findById(id).orElseThrow(RoleNotFoundException::new);

        roleMapper.updateEntityFromRequest(roleRequest, role);

        return roleMapper.fromEntity(roleRepository.save(role));
    }

    @Override
    public RoleResponse delete(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(RoleNotFoundException::new);

        roleRepository.delete(role);

        return roleMapper.fromEntity(role);
    }
}
