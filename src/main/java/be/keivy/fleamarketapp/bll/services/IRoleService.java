package be.keivy.fleamarketapp.bll.services;

import be.keivy.fleamarketapp.common.dtos.role.requests.RoleRequest;
import be.keivy.fleamarketapp.common.dtos.role.responses.RoleResponse;

import java.util.List;

public interface IRoleService {

    /**
     * Récupere la liste des roles existant
     * @return Une liste de RoleResponse
     */
    List<RoleResponse> getAll();

    /**
     * Recupere un role par son nom
     * @param name le nom du role
     * @return le role
     */
    RoleResponse getByName(String name);

    /**
     * Recupere un role par son id
     * @param id l'id du role
     * @return le role
     */
    RoleResponse getById(Long id);

    /**
     * Crée un role
     * @param roleRequest par une requête
     * @return un nouveau role en réponse
     */
    RoleResponse create(RoleRequest roleRequest);

    /**
     * Met à jour un role
     * @param id via son id
     * @param roleRequest avec un formulaire
     * @return une mise à jour du role
     */
    RoleResponse update(Long id, RoleRequest roleRequest);

    /**
     * Supprime un role
     * @param id via son ID
     * @return retourne une réponse
     */
    RoleResponse delete(Long id);
}
