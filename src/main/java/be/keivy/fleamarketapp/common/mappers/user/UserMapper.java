package be.keivy.fleamarketapp.common.mappers.user;

import be.keivy.fleamarketapp.common.dtos.auth.requests.OrganizerRegisterRequest;
import be.keivy.fleamarketapp.common.dtos.auth.requests.RegisterRequest;
import be.keivy.fleamarketapp.common.dtos.auth.requests.SecondHandDealerRegisterRequest;
import be.keivy.fleamarketapp.common.dtos.user.requests.UserUpdateRequest;
import be.keivy.fleamarketapp.common.dtos.user.responses.*;
import be.keivy.fleamarketapp.common.exceptions.InvalidUserTypeException;
import be.keivy.fleamarketapp.domain.entities.Organizer;
import be.keivy.fleamarketapp.domain.entities.Role;
import be.keivy.fleamarketapp.domain.entities.SecondHandDealer;
import be.keivy.fleamarketapp.domain.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel="spring")
public interface UserMapper {

    /**
     * Mappe une demande d'enregistrement de OrganizerRegister à une entité Organizer.
     * @param request la demande à mapper
     * @param roles les rôles à assigner à l'utilisateur
     * @return l'entité Organizer
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "request.address.street", target = "address.street")
    @Mapping(source = "request.address.zipCity.city", target = "address.zipCity.city")
    @Mapping(source = "request.address.zipCity.zip", target = "address.zipCity.zip")
    Organizer toEntity(OrganizerRegisterRequest request, Set<Role> roles);

    /**
     * Mappe une demande d'enregistrement de SecondHandDealerRegister à une entité SecondHandDealer.
     * @param request la demande à mapper
     * @param roles les rôles à assigner à l'utilisateur
     * @return l'entité SecondHandDealer
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    SecondHandDealer toEntity(SecondHandDealerRegisterRequest request, Set<Role> roles);

    /**
     * Mappe une demande d'enregistrement générique à une entité User.
     * @param request la demande à mapper
     * @param roles les rôles à assigner à l'utilisateur
     * @return l'entité User correspondante
     * @throws InvalidUserTypeException si le type de l'utilisateur est invalide
     */
    default User toEntity(RegisterRequest request, Set<Role> roles) {
        if (request instanceof OrganizerRegisterRequest organizerRegisterRequest) {
            return toEntity(organizerRegisterRequest, roles);
        }
        if (request instanceof SecondHandDealerRegisterRequest secondHandDealerRegisterRequest) {
            return toEntity(secondHandDealerRegisterRequest, roles);
        }
        throw new InvalidUserTypeException();
    }
    /**
     * Mappe un ensemble de rôle.
     * @param roles l'ensemble des rôles à mapper
     * @return l'ensemble des noms de rôles
     */
    default Set<String> map(Set<Role> roles) {
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
    }

    /**
     * Met à jour une entité User à partir d'une demande de mise à jour de l'utilisateur.
     * @param request la demande de mise à jour
     * @param user l'utilisateur à mettre à jour
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromRequest(UserUpdateRequest request, @MappingTarget User user);


    /**
     * Mappe une entité User à une réponse UserResponse.
     * @param user l'utilisateur à mapper
     * @return la réponse UserResponse
     */
    @Mapping(source = "enabled", target = "isEnabled")
    @Mapping(source = "locked", target = "isLocked")
    UserResponse fromUser(User user);

    /**
     * Mappe une entité Organizer à une réponse OrganizerResponse.
     * @param organizer l'organisateur à mapper
     * @return la réponse OrganizerResponse
     */
    @Mapping(source = "enabled", target = "isEnabled")
    @Mapping(source = "locked", target = "isLocked")
    OrganizerResponse fromOrganizer(Organizer organizer);

    /**
     * Mappe une entité SecondHandDealer à une réponse SecondHandDealerResponse.
     * @param secondHandDealer le brocanteur à mapper
     * @return la réponse SecondHandDealerResponse
     */
    @Mapping(source = "enabled", target = "isEnabled")
    @Mapping(source = "locked", target = "isLocked")
    SecondHandDealerResponse fromSecondHandDealer(SecondHandDealer secondHandDealer);

    /**
     * Mappe un profil Organizer à une réponse OrganizerProfileResponse.
     * @param organizer le profil de l'organisateur à mapper
     * @return la réponse OrganizerProfileResponse
     */
    OrganizerProfileResponse fromOrganizerProfile(Organizer organizer);

    /**
     * Mappe un profil SecondHandDealer à une réponse SecondHandDealerProfileResponse.
     * @param secondHandDealer le profil du brocanteur à mapper
     * @return la réponse SecondHandDealerProfileResponse
     */
    SecondHandDealerProfileResponse fromSecondHandDealerProfile(SecondHandDealer secondHandDealer);

    /**
     * Mappe un utilisateur à une réponse de mise à jour du mot de passe utilisateur.
     * @param user l'utilisateur à mapper
     * @param message le message de confirmation de mise à jour
     * @return la réponse UserUpdatePasswordResponse
     */
    UserUpdatePasswordResponse fromUserToUserUpdatePassword(User user, String message);

}
