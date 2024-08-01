package be.keivy.fleamarketapp.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "organizer")
public class Organizer extends User {

    @Column(name = "national_register", nullable = false)
    private String nationalRegister;

    @Column(name = "organization_name")
    private String organizationName;

    @Column(name = "organization_phone")
    private String organizationPhone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

}
