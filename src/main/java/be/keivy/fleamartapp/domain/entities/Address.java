package be.keivy.fleamartapp.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name="address")
public class Address extends BaseEntity<Long> {

    @Column(name="street", nullable = false)
    private String street;

    @ManyToOne
    @JoinColumn(nullable = false, name = "zip_city_id")
    private ZipCity zipCity;


}
