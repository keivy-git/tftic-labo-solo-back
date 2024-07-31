package be.keivy.fleamartapp.bll.services;

import be.keivy.fleamartapp.common.dtos.user.responses.OrganizerResponse;
import be.keivy.fleamartapp.common.dtos.user.responses.SecondHandDealerResponse;
import be.keivy.fleamartapp.common.dtos.user.responses.UserResponse;
import be.keivy.fleamartapp.domain.entities.User;

import java.util.List;

/** * Le service utilisateur. Contient des méthodes de gestion des utilisateurs.
 * Utilisé par le rôle ADMIN.
 */
public interface IUserService {

    /**
     * Get all users
     * @Return toute la liste des utilisateurs
     */
    List<UserResponse> getAll();

    List<OrganizerResponse> getAllOrganizer();

    List<SecondHandDealerResponse> getAllSecondHandDealer();

    UserResponse getById(Long id);

    UserResponse getByMail(String email);

    UserResponse getByPhone(String phone);

    OrganizerResponse addOrganizer();
}
