package be.keivy.fleamartapp.domain.entities;

import be.keivy.fleamartapp.domain.enums.RegistrationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@Table(name = "registration")
public class Registration extends BaseEntity<Long> {

    @Column(name = "subs_date_end", nullable = false)
    private LocalDateTime subsDateEnd;

    @Column(name = "subs_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private RegistrationStatus subsStatus;

    @Column(name ="reserved_meter", nullable = false)
    private int reservedMeter;
}
