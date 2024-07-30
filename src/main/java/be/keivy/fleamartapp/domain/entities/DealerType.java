package be.keivy.fleamartapp.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Table(name="dealer_type")
public class DealerType extends BaseEntity<Long> {

    private String type;
}
