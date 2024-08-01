package be.keivy.fleamarketapp.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@EqualsAndHashCode(callSuper = true)
@Table(name = "second_hand_dealer")
public class SecondHandDealer extends User {

    @Column(name = "birth_date", nullable = false)
    private LocalDateTime birthDate;

    @OneToMany(mappedBy = "secondHandDealer")
    private Set<Registration> registrations;

    public SecondHandDealer() {
        this.registrations = new HashSet<>();
    }
}
