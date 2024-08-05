package be.keivy.fleamarketapp.bll.services.impls;

import be.keivy.fleamarketapp.bll.services.IAuthService;
import be.keivy.fleamarketapp.bll.services.IUserService;
import be.keivy.fleamarketapp.common.dtos.auth.requests.OrganizerRegisterRequest;
import be.keivy.fleamarketapp.common.dtos.auth.requests.SecondHandDealerRegisterRequest;
import be.keivy.fleamarketapp.common.dtos.auth.responses.UserTokenResponse;
import be.keivy.fleamarketapp.common.dtos.user.requests.OrganizerUpdateRequest;
import be.keivy.fleamarketapp.common.dtos.user.requests.SecondHandDealerUpdateRequest;
import be.keivy.fleamarketapp.common.dtos.user.responses.OrganizerResponse;
import be.keivy.fleamarketapp.common.dtos.user.responses.SecondHandDealerResponse;
import be.keivy.fleamarketapp.common.dtos.user.responses.UserResponse;
import be.keivy.fleamarketapp.common.exceptions.NotAllowedException;
import be.keivy.fleamarketapp.common.exceptions.role.RoleNotFoundException;
import be.keivy.fleamarketapp.common.exceptions.auth.UserAlreadyExistsException;
import be.keivy.fleamarketapp.common.exceptions.auth.UserNotFoundException;
import be.keivy.fleamarketapp.common.mappers.user.UserMapper;
import be.keivy.fleamarketapp.dal.repositories.OrganizerRepository;
import be.keivy.fleamarketapp.dal.repositories.RoleRepository;
import be.keivy.fleamarketapp.dal.repositories.SecondHandDealerRepository;
import be.keivy.fleamarketapp.dal.repositories.UserRepository;
import be.keivy.fleamarketapp.domain.entities.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final OrganizerRepository organizerRepository;
    private final SecondHandDealerRepository secondHandDealerRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final IAuthService authService;

    /**
     * Récupère tous les utilisateurs.
     * @return toute la liste des utilisateurs.
     */
    @Override
    public List<UserResponse> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::fromUser)
                .toList();
    }

    /**
     * Récupère tous les organisateurs
     * @return toute la liste des organisateurs
     */
    @Override
    public List<OrganizerResponse> getAllOrganizer() {
        return organizerRepository.findAll().stream()
                .map(userMapper::fromOrganizer)
                .toList();
    }

    /**
     * Récupère tous les brocanteurs.
     * @return toute la liste des brocanteurs.
     */
    @Override
    public List<SecondHandDealerResponse> getAllSecondHandDealer() {
        return secondHandDealerRepository.findAll()
                .stream()
                .map(userMapper::fromSecondHandDealer)
                .toList();
    }

    /**
     * Récupère un utilisateur par son identifiant.
     * @param id l'identifiant de l'utilisateur.
     * @return l'utilisateur correspondant à l'identifiant.
     */
    @Override
    public UserResponse getById(Long id) {
        return userMapper.fromUser(
                userRepository.findById(id).orElseThrow(UserNotFoundException::new)
        );
    }

    /**
     * Récupère un utilisateur par son email.
     * @param email l'email de l'utilisateur.
     * @return l'utilisateur correspondant à l'email.
     */
    @Override
    public UserResponse getByMail(String email) {
        return userMapper.fromUser(
                userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new)
        );
    }

    /**
     * Récupère un utilisateur par son numéro de téléphone.
     * @param phone le numéro de téléphone de l'utilisateur.
     * @return l'utilisateur correspondant au numéro de téléphone.
     */
    @Override
    public UserResponse getByPhone(String phone) {
        return userMapper.fromUser(
                userRepository.findByPhone(phone).orElseThrow(UserNotFoundException::new)
        );
    }

    /**
     * Ajoute un nouvel organisateur.
     * @return le nouvel organisateur ajouté.
     */
    @Override
    @Transactional
    public OrganizerResponse addOrganizer(OrganizerRegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        Role role = roleRepository.findByName("ORGANIZER")
                .orElseThrow(RoleNotFoundException::new);

        request.setPassword(passwordEncoder.encode(request.getPassword()));

        Organizer organizer = userMapper.toEntity(request, Set.of(role));

        return userMapper.fromOrganizer(organizerRepository.save(organizer));
    }
    /**
     * Ajoute un nouveau brocanteur.
     * @param request la requête d'enregistrement contenant les détails du brocanteur
     * @return le nouveau brocanteur ajouté.
     */
    @Override
    public SecondHandDealerResponse addSecondHandDealer(SecondHandDealerRegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        Role role = roleRepository.findByName("SECOND_HAND_DEALER")
                .orElseThrow(RoleNotFoundException::new);

        request.setPassword(passwordEncoder.encode(request.getPassword()));

        SecondHandDealer secondHandDealer = userMapper.toEntity(request, Set.of(role));

        return userMapper.fromSecondHandDealer(secondHandDealerRepository.save(secondHandDealer));
    }

    /**
     * Met à jour un organisateur existant.
     * @param id      l'identifiant de l'organisateur à mettre à jour.
     * @param request les nouvelles informations pour mettre à jour l'organisateur.
     * @return l'organisateur mis à jour.
     */
    @Override
    public OrganizerResponse updateOrganizer(Long id, OrganizerUpdateRequest request) {
        Organizer organizer = organizerRepository.findById(id).orElseThrow(UserNotFoundException::new);

        userMapper.updateEntityFromRequest(request, organizer);

        return userMapper.fromOrganizer(userRepository.save(organizer));
    }

    /**
     * Met à jour un brocanteur existant.
     * @param id      l'identifiant du brocanteur à mettre à jour.
     * @param request les nouvelles informations pour mettre à jour le brocanteur.
     * @return le brocanteur mis à jour.
     */
    @Override
    public SecondHandDealerResponse updateSecondHandDealer(Long id, SecondHandDealerUpdateRequest request) {
        SecondHandDealer secondHandDealer = secondHandDealerRepository.findById(id).orElseThrow(UserNotFoundException::new);

        userMapper.updateEntityFromRequest(request, secondHandDealer);

        return userMapper.fromSecondHandDealer(userRepository.save(secondHandDealer));
    }

    /**
     * Verrouille ou déverrouille un utilisateur.
     * @param id       l'identifiant de l'utilisateur.
     * @param isLocked true pour verrouiller l'utilisateur, false pour le déverrouiller.
     * @return l'utilisateur mis à jour après le verrouillage/déverrouillage.
     */
    @Override
    public UserResponse triggerLock(Long id, boolean isLocked) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

//%s : permet d'afficher si c'est true ou false de 'isLocked'
        if (!user.isAccountNonLocked() == isLocked) {
            throw new NotAllowedException(String.format("User field 'isLocked' already defined to '%s'", isLocked));
        }

         user.setLocked(isLocked);

        return userMapper.fromUser(userRepository.save(user));
    }

    /**
     * Active ou désactive un utilisateur.
     * @param id        l'identifiant de l'utilisateur.
     * @param isEnabled true pour activer l'utilisateur, false pour le désactiver.
     * @return l'utilisateur mis à jour après l'activation/la désactivation.
     */
    @Override
    public UserResponse triggerUnlock(Long id, boolean isEnabled) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        if (user.isEnabled() == isEnabled) {
            throw new NotAllowedException(String.format("User field 'isEnabled' already defined to '%s'", isEnabled));
        }

        user.setEnabled(isEnabled);

        return userMapper.fromUser(userRepository.save(user));
    }

    /**
     * Usurpe l'identité d'un utilisateur par son identifiant.
     * @param id l'identifiant de l'utilisateur à usurper.
     * @return le jeton d'utilisateur pour l'usurpation.
     */
    @Override
    public UserTokenResponse impersonateUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        return authService.impersonateUser(user);
    }

    /**
     * Usurpe l'identité d'un utilisateur par son email.
     * @param email l'email de l'utilisateur à usurper.
     * @return le jeton d'utilisateur pour l'usurpation.
     */
    @Override
    public UserTokenResponse impersonateUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);

        return authService.impersonateUser(user);
    }
}
