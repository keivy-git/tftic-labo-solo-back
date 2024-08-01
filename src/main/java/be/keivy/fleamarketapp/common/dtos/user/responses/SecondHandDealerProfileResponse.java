package be.keivy.fleamarketapp.common.dtos.user.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class SecondHandDealerProfileResponse extends UserProfileResponse {

    private final LocalDate birthDate;

    public SecondHandDealerProfileResponse(
            Long id,
            String email,
            String firstname,
            String lastname,
            String phoneNumber,
            LocalDate birthDate) {
        super(id, email, firstname, lastname, phoneNumber);
        this.birthDate = birthDate;
    }
}
