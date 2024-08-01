package be.keivy.fleamarketapp.domain.entities;

import be.keivy.fleamarketapp.domain.enums.RegistrationStatus;
import be.keivy.fleamarketapp.domain.enums.TypeMerchandise;
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

    @Column(name = "regs_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private RegistrationStatus registrationStatus;

    @Column(name = "type_merchandise", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeMerchandise typeMerchandise;

    @Column(name ="reserved_meter", nullable = false)
    private int reservedMeter;

    @ManyToOne
    @JoinColumn(name = "second_hand_dealer_id", nullable = false)
    private SecondHandDealer secondHandDealer;

    @ManyToOne
    @JoinColumn(name = "flea_market_id", nullable = false)
    private FleaMarket fleaMarket;
}
