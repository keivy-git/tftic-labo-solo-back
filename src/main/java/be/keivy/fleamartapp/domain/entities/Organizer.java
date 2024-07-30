package be.keivy.fleamartapp.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "flea_market_organizer")
public class Organizer extends User {

    @Column(name = "national_register", nullable = false)
    private String nationalRegister;

    @Column(name = "organization_name")
    private String organizationName;

    @Column(name = "organization_phone")
    private String organizationPhone;
}
