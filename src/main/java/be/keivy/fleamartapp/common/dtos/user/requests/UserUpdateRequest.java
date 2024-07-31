package be.keivy.fleamartapp.common.dtos.user.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class UserUpdateRequest {

    @NotBlank(message = "There must be a firstname")
    private final String firstname;

    @NotBlank(message = "There must be lastname")
    private final String lastname;

    @NotBlank(message = "There must be a contact email")
    @Email(message = "The contact email is not valid")
    private final String email;

    @NotBlank(message = "There must be a phone number")
    private final String phoneNumber;

    @NotBlank(message = "There must be a street")
    private final String street;

    @NotBlank(message = "There must be a city")
    private final String city;

    @NotBlank(message = "There must be a zip")
    private final String zip;

    @NotBlank(message = "There must be a country")
    private final String country;

}