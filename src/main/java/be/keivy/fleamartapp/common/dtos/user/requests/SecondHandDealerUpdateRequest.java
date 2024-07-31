package be.keivy.fleamartapp.common.dtos.user.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter @Setter
public class SecondHandDealerUpdateRequest extends UserUpdateRequest {

    @NotNull(message = "There must be a birth date")
    private final LocalDate birthDate;

    public SecondHandDealerUpdateRequest(
           String firstname,
           String lastname,
           String email,
           String phoneNumber,
           String street,
           String city,
           String zip,
           String country,
           LocalDate birthDate
    ) {
        super(
                firstname,
                lastname,
                email,
                phoneNumber,
                street,
                city,
                zip,
                country);
        this.birthDate = birthDate;
    }
}
