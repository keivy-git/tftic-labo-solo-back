package be.keivy.fleamartapp.common.dtos.user.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrganizerUpdateRequest extends UserUpdateRequest {

    @NotBlank(message = "There must be a organization name")
    private final String organizationName;

    @NotBlank(message = "There must be a organization phone")
    private final String organizationPhone;

    public OrganizerUpdateRequest(
            String firstname,
            String lastname,
            String email,
            String phoneNumber,
            String street,
            String city,
            String zip,
            String country,
            String organizationName,
            String organizationPhone
    ) {
        super(
                firstname,
                lastname,
                email,
                phoneNumber,
                street,
                city,
                zip,
                country
        );
        this.organizationName = organizationName;
        this.organizationPhone = organizationPhone;
    }
}
