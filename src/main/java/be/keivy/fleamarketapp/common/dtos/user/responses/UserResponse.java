package be.keivy.fleamarketapp.common.dtos.user.responses;

import be.keivy.fleamarketapp.domain.entities.Address;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
public class UserResponse {
    protected final Long id;
    protected final String email;
    protected final String firstname;
    protected final String lastname;
    protected final String phoneNumber;
    protected final String street;
    protected final String city;
    protected final Set<String> roles;
    protected final boolean isEnabled;
    protected final boolean isLocked;

    public UserResponse(
            Long id,
            String email,
            String firstname,
            String lastname,
            String phoneNumber,
            Address address,
            Set<String> roles,
            boolean isEnabled,
            boolean isLocked) {
        this.id = id;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.street = address.getStreet();
        this.city = address.getZipCity().getCity();
        this.roles = roles;
        this.isEnabled = isEnabled;
        this.isLocked = isLocked;
    }
}
