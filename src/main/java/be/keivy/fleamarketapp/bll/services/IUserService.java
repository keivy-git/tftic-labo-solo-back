package be.keivy.fleamarketapp.bll.services;

import be.keivy.fleamarketapp.common.dtos.auth.responses.UserTokenResponse;
import be.keivy.fleamarketapp.common.dtos.user.requests.OrganizerUpdateRequest;
import be.keivy.fleamarketapp.common.dtos.user.requests.SecondHandDealerUpdateRequest;
import be.keivy.fleamarketapp.common.dtos.user.responses.OrganizerResponse;
import be.keivy.fleamarketapp.common.dtos.user.responses.SecondHandDealerResponse;
import be.keivy.fleamarketapp.common.dtos.user.responses.UserResponse;
import be.keivy.fleamarketapp.domain.entities.SecondHandDealer;

import java.util.List;

/** * Le service utilisateur. Contient des méthodes de gestion des utilisateurs.
 * Utilisé par le rôle ADMIN.
 */
public interface IUserService {

    /**
     * Récupère tous les utilisateurs.
     * @return toute la liste des utilisateurs.
     */
    List<UserResponse> getAll();

    /**
     * Récupère tous les organisateurs
     * @return toute la liste des organisateurs
     */
    List<OrganizerResponse> getAllOrganizer();

    /**
     * Récupère tous les brocanteurs.
     * @return toute la liste des brocanteurs.
     */
    List<SecondHandDealerResponse> getAllSecondHandDealer();

    /**
     * Récupère un utilisateur par son identifiant.
     * @param id l'identifiant de l'utilisateur.
     * @return l'utilisateur correspondant à l'identifiant.
     */

    UserResponse getById(Long id);
    /**
     * Récupère un utilisateur par son email.
     *
     * @param email l'email de l'utilisateur.
     * @return l'utilisateur correspondant à l'email.
     */
    UserResponse getByMail(String email);

    /**
     * Récupère un utilisateur par son numéro de téléphone.
     *
     * @param phone le numéro de téléphone de l'utilisateur.
     * @return l'utilisateur correspondant au numéro de téléphone.
     */
    UserResponse getByPhone(String phone);

    /**
     * Ajoute un nouvel organisateur.
     * @return le nouvel organisateur ajouté.
     */
    OrganizerResponse addOrganizer();

    /**
     * Ajoute un nouveau brocanteur.
     * @return le nouveau brocanteur ajouté.
     */
    SecondHandDealer addSecondHandDealer();

    /**
     * Met à jour un organisateur existant.
     * @param id l'identifiant de l'organisateur à mettre à jour.
     * @param request les nouvelles informations pour mettre à jour l'organisateur.
     * @return l'organisateur mis à jour.
     */
    OrganizerResponse updateOrganizer(Long id, OrganizerUpdateRequest request);

    /**
     * Met à jour un brocanteur existant.
     * @param id l'identifiant du brocanteur à mettre à jour.
     * @param request les nouvelles informations pour mettre à jour le brocanteur.
     * @return le brocanteur mis à jour.
     */
    SecondHandDealerResponse updateSecondHandDealer(Long id, SecondHandDealerUpdateRequest request);

    /**
     * Verrouille ou déverrouille un utilisateur.
     * @param id l'identifiant de l'utilisateur.
     * @param isLocked true pour verrouiller l'utilisateur, false pour le déverrouiller.
     * @return l'utilisateur mis à jour après le verrouillage/déverrouillage.
     */
    UserResponse triggerLock(Long id, boolean isLocked);


    /**
     * Active ou désactive un utilisateur.
     * @param id l'identifiant de l'utilisateur.
     * @param isEnabled true pour activer l'utilisateur, false pour le désactiver.
     * @return l'utilisateur mis à jour après l'activation/la désactivation.
     */
    UserResponse triggerUnlock(Long id, boolean isEnabled);

    /**
     * Usurpe l'identité d'un utilisateur par son identifiant.
     * @param id l'identifiant de l'utilisateur à usurper.
     * @return le jeton d'utilisateur pour l'usurpation.
     */
    UserTokenResponse impersonateUserById(Long id);

    /**
     * Usurpe l'identité d'un utilisateur par son email.
     * @param email l'email de l'utilisateur à usurper.
     * @return le jeton d'utilisateur pour l'usurpation.
     */
    UserTokenResponse impersonateUserByEmail(String email);
}
