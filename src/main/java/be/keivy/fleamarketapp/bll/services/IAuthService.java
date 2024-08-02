package be.keivy.fleamarketapp.bll.services;


import be.keivy.fleamarketapp.common.dtos.auth.requests.LoginRequest;
import be.keivy.fleamarketapp.common.dtos.auth.requests.RegisterRequest;
import be.keivy.fleamarketapp.common.dtos.auth.responses.UserTokenResponse;
import be.keivy.fleamarketapp.common.exceptions.auth.InvalidPasswordException;
import be.keivy.fleamarketapp.common.exceptions.auth.UserAlreadyExistsException;
import be.keivy.fleamarketapp.common.exceptions.auth.UserNotAuthenticatedException;
import be.keivy.fleamarketapp.common.exceptions.auth.UserNotFoundException;
import be.keivy.fleamarketapp.domain.entities.Organizer;
import be.keivy.fleamarketapp.domain.entities.SecondHandDealer;
import be.keivy.fleamarketapp.domain.entities.User;

public interface IAuthService {

    /**
     * Authentifie un utilisateur avec les informations de connexion fournies.
     * @param request la requête de connexion contenant le nom d'utilisateur et le mot de passe de l'utilisateur
     * @return un objet UserTokenResponse contenant les détails de l'utilisateur et le jeton d'authentification
     * @throws InvalidPasswordException si le mot de passe fourni ne correspond pas à celui stocké dans la base de données
     * @throws UserNotFoundException si un utilisateur avec le nom d'utilisateur fourni n'existe pas dans la base de données
     */
    UserTokenResponse login(LoginRequest request);

    /**
     * Enregistre un nouvel utilisateur dans le système.
     * @param request la requête d'enregistrement contenant les détails de l'utilisateur et le type d'utilisateur souhaité
     * @return un objet UserTokenResponse contenant les détails de l'utilisateur et le jeton d'authentification
     * @throws UserAlreadyExistsException si un utilisateur avec l'email fourni existe déjà dans la base de données
     */
    UserTokenResponse register(RegisterRequest request);

    /**
     * Récupère l'organisateur actuellement authentifié.
     * @return l'organisateur authentifié
     * @throws UserNotAuthenticatedException si aucun organisateur n'est actuellement authentifié
     */
    Organizer getAuthenticatedOrganizer() throws UserNotAuthenticatedException;

    /**
     * Récupère le brocanteur actuellement authentifié.
     * @return le brocanteur authentifié
     * @throws UserNotAuthenticatedException si aucun vendeur d'occasion n'est actuellement authentifié
     */
    SecondHandDealer getAuthenticatedSecondHandDealer() throws UserNotAuthenticatedException;


    /**
     * Récupère l'utilisateur actuellement authentifié.
     * @return l'utilisateur authentifié
     * @throws UserNotAuthenticatedException si aucun utilisateur n'est actuellement authentifié
     */
    User getAuthenticatedUser() throws UserNotAuthenticatedException;

    /**
     * Vérifie si l'utilisateur est un administrateur.
     * @return true si l'utilisateur est un administrateur, false sinon
     */
    boolean isAdmin(User user);

    /**
     * Usurpe l'identité d'un utilisateur.
     * @param user l'utilisateur à usurper
     * @return un objet UserTokenResponse contenant les détails de l'utilisateur et le jeton d'authentification
     */
    UserTokenResponse impersonateUser(User user);
}
