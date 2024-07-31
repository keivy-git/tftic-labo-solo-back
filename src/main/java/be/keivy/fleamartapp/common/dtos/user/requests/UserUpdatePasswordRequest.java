package be.keivy.fleamartapp.common.dtos.user.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Ce model de requête est pour changer le pass word
 * <p>Cet enregistrement encapsule l'ancien mot de passe et le nouveau mot de passe</p>
 * @param oldPassword
 * @param newPassword
 */
public record UserUpdatePasswordRequest(
        @NotBlank(message ="password is required")
        String oldPassword,

        @NotBlank(message = "password is required")
        @Size(min = 8, message = "Password must be 8 characters minimum")
        String newPassword
) {
}
