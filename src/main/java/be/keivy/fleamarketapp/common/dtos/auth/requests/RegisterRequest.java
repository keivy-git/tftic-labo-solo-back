package be.keivy.fleamarketapp.common.dtos.auth.requests;

import be.keivy.fleamarketapp.domain.entities.Address;
import be.keivy.fleamarketapp.domain.entities.ZipCity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @Data : A shortcut for :
 * @ToString,
 * @EqualsAndHashCode,
 * @Getter on all fields,
 * @Setter on all non-final fields, and
 * @RequiredArgsConstructor!
 */

@Data
public abstract class RegisterRequest {

    @NotBlank(message = "There must be an email")
    @Email(message = "The email is not valid")
    private String email;

    @NotNull(message = "There must be a phone number")
    @Size(min = 8, message = "Password must be 8 characters minimum")
    private String password;

    @NotBlank(message = "There must be a firstname")
    private String firstname;

    @NotBlank(message = "There must be lastname")
    private String lastname;

    @NotBlank(message = "There must be a phone number")
    private String phoneNumber;
}
