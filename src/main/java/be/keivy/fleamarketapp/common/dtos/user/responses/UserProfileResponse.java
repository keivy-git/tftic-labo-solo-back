package be.keivy.fleamarketapp.common.dtos.user.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public abstract class UserProfileResponse {
    private final Long id;
    private final String email;
    private final String firstname;
    private final String lastname;
    private final String phoneNumber;
}
