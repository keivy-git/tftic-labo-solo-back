package be.keivy.fleamartapp.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
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


}
