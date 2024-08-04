package be.keivy.fleamarketapp.infrastructure.data_init;

import be.keivy.fleamarketapp.common.dtos.role.requests.RoleRequest;
import be.keivy.fleamarketapp.common.mappers.role.RoleMapper;
import be.keivy.fleamarketapp.dal.repositories.RoleRepository;
import be.keivy.fleamarketapp.domain.entities.Role;
import be.keivy.fleamarketapp.infrastructure.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Order(0)
public class RoleInit implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public void run(String... args){
        if (roleRepository.count() == 0) {
            List<Role> roles = List.of(
                    new Role(Constants.ADMIN_ROLE, "Ce rôle gère l'ensemble du site internet, les annonces, les utilisateurs"),
                    new Role(Constants.ORGANIZER_ROLE, "Ce rôle permet à l'utilisation d'organiser une brocante, d'y accepter des participants"),
                    new Role(Constants.SECOND_HAND_DEALER_ROLE, "Ce rôle permet de s'inscrire et de participer à une brocante en cours")
            );

            roleRepository.saveAll(roles);
        }
    }
}
