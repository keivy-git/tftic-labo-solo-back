package be.keivy.fleamarketapp.bll.services.impls;

import be.keivy.fleamarketapp.bll.services.IUserService;
import be.keivy.fleamarketapp.common.dtos.auth.responses.UserTokenResponse;
import be.keivy.fleamarketapp.common.dtos.user.requests.OrganizerUpdateRequest;
import be.keivy.fleamarketapp.common.dtos.user.requests.SecondHandDealerUpdateRequest;
import be.keivy.fleamarketapp.common.dtos.user.responses.OrganizerResponse;
import be.keivy.fleamarketapp.common.dtos.user.responses.SecondHandDealerResponse;
import be.keivy.fleamarketapp.common.dtos.user.responses.UserResponse;
import be.keivy.fleamarketapp.dal.repositories.UserRepository;
import be.keivy.fleamarketapp.domain.entities.SecondHandDealer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    /**
     * Récupère tous les utilisateurs.
     * @return toute la liste des utilisateurs.
     */
    @Override
    public List<UserResponse> getAll() {
        return List.of();
    }

    /**
     * Récupère tous les organisateurs
     * @return toute la liste des organisateurs
     */
    @Override
    public List<OrganizerResponse> getAllOrganizer() {
        return List.of();
    }

    /**
     * Récupère tous les brocanteurs.
     * @return toute la liste des brocanteurs.
     */
    @Override
    public List<SecondHandDealerResponse> getAllSecondHandDealer() {
        return List.of();
    }

    /**
     * Récupère un utilisateur par son identifiant.
     * @param id l'identifiant de l'utilisateur.
     * @return l'utilisateur correspondant à l'identifiant.
     */
    @Override
    public UserResponse getById(Long id) {
        return null;
    }

    /**
     * Récupère un utilisateur par son email.
     * @param email l'email de l'utilisateur.
     * @return l'utilisateur correspondant à l'email.
     */
    @Override
    public UserResponse getByMail(String email) {
        return null;
    }

    /**
     * Récupère un utilisateur par son numéro de téléphone.
     * @param phone le numéro de téléphone de l'utilisateur.
     * @return l'utilisateur correspondant au numéro de téléphone.
     */
    @Override
    public UserResponse getByPhone(String phone) {
        return null;
    }

    /**
     * Ajoute un nouvel organisateur.
     * @return le nouvel organisateur ajouté.
     */
    @Override
    public OrganizerResponse addOrganizer() {
        return null;
    }

    /**
     * Ajoute un nouveau brocanteur.
     * @return le nouveau brocanteur ajouté.
     */
    @Override
    public SecondHandDealer addSecondHandDealer() {
        return null;
    }

    /**
     * Met à jour un organisateur existant.
     * @param id      l'identifiant de l'organisateur à mettre à jour.
     * @param request les nouvelles informations pour mettre à jour l'organisateur.
     * @return l'organisateur mis à jour.
     */
    @Override
    public OrganizerResponse updateOrganizer(Long id, OrganizerUpdateRequest request) {
        return null;
    }

    /**
     * Met à jour un brocanteur existant.
     * @param id      l'identifiant du brocanteur à mettre à jour.
     * @param request les nouvelles informations pour mettre à jour le brocanteur.
     * @return le brocanteur mis à jour.
     */
    @Override
    public SecondHandDealerResponse updateSecondHandDealer(Long id, SecondHandDealerUpdateRequest request) {
        return null;
    }

    /**
     * Verrouille ou déverrouille un utilisateur.
     * @param id       l'identifiant de l'utilisateur.
     * @param isLocked true pour verrouiller l'utilisateur, false pour le déverrouiller.
     * @return l'utilisateur mis à jour après le verrouillage/déverrouillage.
     */
    @Override
    public UserResponse triggerLock(Long id, boolean isLocked) {
        return null;
    }

    /**
     * Active ou désactive un utilisateur.
     * @param id        l'identifiant de l'utilisateur.
     * @param isEnabled true pour activer l'utilisateur, false pour le désactiver.
     * @return l'utilisateur mis à jour après l'activation/la désactivation.
     */
    @Override
    public UserResponse triggerUnlock(Long id, boolean isEnabled) {
        return null;
    }

    /**
     * Usurpe l'identité d'un utilisateur par son identifiant.
     * @param id l'identifiant de l'utilisateur à usurper.
     * @return le jeton d'utilisateur pour l'usurpation.
     */
    @Override
    public UserTokenResponse impersonateUserById(Long id) {
        return null;
    }

    /**
     * Usurpe l'identité d'un utilisateur par son email.
     * @param email l'email de l'utilisateur à usurper.
     * @return le jeton d'utilisateur pour l'usurpation.
     */
    @Override
    public UserTokenResponse impersonateUserByEmail(String email) {
        return null;
    }
}
