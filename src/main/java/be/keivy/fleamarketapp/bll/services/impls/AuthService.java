package be.keivy.fleamarketapp.bll.services.impls;

import be.keivy.fleamarketapp.bll.services.IAuthService;
import be.keivy.fleamarketapp.common.dtos.auth.requests.LoginRequest;
import be.keivy.fleamarketapp.common.dtos.auth.requests.OrganizerRegisterRequest;
import be.keivy.fleamarketapp.common.dtos.auth.requests.RegisterRequest;
import be.keivy.fleamarketapp.common.dtos.auth.responses.UserTokenResponse;
import be.keivy.fleamarketapp.common.exceptions.NotAllowedException;
import be.keivy.fleamarketapp.common.exceptions.NotFoundException;
import be.keivy.fleamarketapp.common.exceptions.auth.*;
import be.keivy.fleamarketapp.common.mappers.user.UserMapper;
import be.keivy.fleamarketapp.dal.repositories.RoleRepository;
import be.keivy.fleamarketapp.dal.repositories.UserRepository;
import be.keivy.fleamarketapp.domain.entities.Organizer;
import be.keivy.fleamarketapp.domain.entities.Role;
import be.keivy.fleamarketapp.domain.entities.SecondHandDealer;
import be.keivy.fleamarketapp.domain.entities.User;
import be.keivy.fleamarketapp.infrastructure.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

import static be.keivy.fleamarketapp.infrastructure.utils.Constants.ADMIN_ROLE;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService, IAuthService {

    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final UserDetailsChecker userDetailsChecker;
    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
    /**
     * Authentifie un utilisateur avec les informations de connexion fournies.
     * @param request la requête de connexion contenant le nom d'utilisateur et le mot de passe de l'utilisateur
     * @return un objet UserTokenResponse contenant les détails de l'utilisateur et le jeton d'authentification
     * @throws InvalidPasswordException si le mot de passe fourni ne correspond pas à celui stocké dans la base de données
     * @throws UserNotFoundException    si un utilisateur avec le nom d'utilisateur fourni n'existe pas dans la base de données
     */
    @Override
    public UserTokenResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new InvalidPasswordException();
        }

        if (!user.isEnabled() && user.isAccountNonLocked()) {
            user.setEnabled(true);
            userRepository.save(user);

            throw new AccountReactivatedException();
        }

        userDetailsChecker.check(user);

        String token = jwtUtils.generateToken(user);

        return UserTokenResponse.fromEntityWithToken(user, token);
    }

    /**
     * Enregistre un nouvel utilisateur dans le système.
     * @param request la requête d'enregistrement contenant les détails de l'utilisateur et le type d'utilisateur souhaité
     * @return un objet UserTokenResponse contenant les détails de l'utilisateur et le jeton d'authentification
     * @throws UserAlreadyExistsException si un utilisateur avec l'email fourni existe déjà dans la base de données
     */

    @Override
    public UserTokenResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new UserAlreadyExistsException();
        }

        Role role = roleRepository.findByName(request.getRoleName())
                .orElseThrow(() -> new NotFoundException(request.getRoleName() + " role not found"));

        request.setPassword(passwordEncoder.encode(request.getPassword()));

        User user = userMapper.toEntity(request, Set.of(role));

        userRepository.save(user);

        String token = jwtUtils.generateToken(user);

        return UserTokenResponse.fromEntityWithToken(user, token);
   }

    /**
     * Récupère l'organisateur actuellement authentifié.
     * @return l'organisateur authentifié
     * @throws UserNotAuthenticatedException si aucun organisateur n'est actuellement authentifié
     */
    @Override
    public Organizer getAuthenticatedOrganizer() throws UserNotAuthenticatedException {
        User authenticatedUser = getAuthenticatedUser();

        if(authenticatedUser instanceof Organizer organizer) {
            return organizer;
        }
        throw new UserNotAuthenticatedException();
    }

    /**
     * Récupère le brocanteur actuellement authentifié.
     * @return le brocanteur authentifié
     * @throws UserNotAuthenticatedException si aucun vendeur d'occasion n'est actuellement authentifié
     */
    @Override
    public SecondHandDealer getAuthenticatedSecondHandDealer() throws UserNotAuthenticatedException {
        User authenticatedUser = getAuthenticatedUser();

        if(authenticatedUser instanceof SecondHandDealer secondHandDealer) {
            return secondHandDealer;
        }
        throw new UserNotAuthenticatedException();
    }

    /**
     * Récupère l'utilisateur actuellement authentifié.
     * @return l'utilisateur authentifié
     * @throws UserNotAuthenticatedException si aucun utilisateur n'est actuellement authentifié
     */
    @Override
    public User getAuthenticatedUser() throws UserNotAuthenticatedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UserNotAuthenticatedException();
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof User authenticatedUser) {
            return authenticatedUser;
        }
        throw new UserNotAuthenticatedException();
    }

    /**
     * Vérifie si l'utilisateur est un administrateur.
     * @param user
     * @return true si l'utilisateur est un administrateur, false sinon
     */
    @Override
    public boolean isAdmin(User user) {
        return user.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(ADMIN_ROLE));
    }

    /**
     * Usurpe l'identité d'un utilisateur.
     * @param user l'utilisateur à usurper
     * @return un objet UserTokenResponse contenant les détails de l'utilisateur et le jeton d'authentification
     */
    @Override
    public UserTokenResponse impersonateUser(User user) {
        if(!user.isEnabled() || !user.isAccountNonLocked() || user.isExpired()) {
            throw new NotAllowedException("User cannot be impersonated due to account restrictions");
        }

        String token = jwtUtils.generateToken(user);

        return UserTokenResponse.fromEntityWithToken(user, token);
    }


}
