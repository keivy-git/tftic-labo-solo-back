package be.keivy.fleamarketapp.common.dtos.user.responses;

import be.keivy.fleamarketapp.domain.entities.Address;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter @Setter
public class SecondHandDealerResponse extends UserResponse {

    private final LocalDateTime birthDate;

    public SecondHandDealerResponse(
            Long id,
            String email,
            String firstname,
            String lastname,
            String phoneNumber,
            Address address,
            Set<String> roles,
            boolean isEnabled,
            boolean isLocked,
            LocalDateTime birthDate
    ) {
        super(
                id,
                email,
                firstname,
                lastname,
                phoneNumber,
                address,
                roles,
                isEnabled,
                isLocked
        );
        this.birthDate = birthDate;
    }
}
