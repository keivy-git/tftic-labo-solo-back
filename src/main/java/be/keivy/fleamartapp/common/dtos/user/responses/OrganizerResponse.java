package be.keivy.fleamartapp.common.dtos.user.responses;

import be.keivy.fleamartapp.domain.entities.Address;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
public class OrganizerResponse extends UserResponse {

    private final String nationalRegister;
    private final String organizationName;
    private final String organizationPhone;

    public OrganizerResponse(
            Long id,
            String email,
            String firstname,
            String lastname,
            String phoneNumber,
            Address address,
            Set<String> roles,
            String nationalRegister,
            String organizationName,
            String organizationPhone,
            boolean isEnabled,
            boolean isLocked) {
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
        this.nationalRegister = nationalRegister;
        this.organizationName = organizationName;
        this.organizationPhone = organizationPhone;
    }
}
