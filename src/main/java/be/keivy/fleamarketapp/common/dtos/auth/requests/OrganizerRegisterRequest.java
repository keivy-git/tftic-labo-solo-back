package be.keivy.fleamarketapp.common.dtos.auth.requests;

import be.keivy.fleamarketapp.dal.repositories.RoleRepository;
import be.keivy.fleamarketapp.domain.entities.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import static org.hibernate.metamodel.mapping.internal.AnyDiscriminatorPart.ROLE_NAME;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrganizerRegisterRequest extends RegisterRequest {

    @NotNull(message = "There must be a national register")
    private String nationalRegister;

    private String organizationName;

    private String organizationPhone;

    @NotBlank(message = "There must be a address")
    private Address address;

    @Override
    public String getRoleName() {
        return "";
    }
}
