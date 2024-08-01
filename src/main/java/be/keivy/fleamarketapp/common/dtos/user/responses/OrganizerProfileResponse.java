package be.keivy.fleamarketapp.common.dtos.user.responses;

import be.keivy.fleamarketapp.domain.entities.Address;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrganizerProfileResponse extends UserProfileResponse {
    private final String organizationName;
    private final String organizationPhone;
    private final Address address;


    public OrganizerProfileResponse(
            Long id,
            String email,
            String firstname,
            String lastname,
            String phoneNumber,
            String organizationName,
            String organizationPhone,
            Address address) {
        super(id, email, firstname, lastname, phoneNumber );
        this.organizationName = organizationName;
        this.organizationPhone = organizationPhone;
        this.address = address;
    }
}
