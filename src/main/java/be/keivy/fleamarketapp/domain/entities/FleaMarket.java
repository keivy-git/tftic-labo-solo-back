package be.keivy.fleamarketapp.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@Table(name="flea_market")
public class FleaMarket extends BaseEntity<Long> {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name="short_description", length = 64)
    private String shortDescription;

    @Column(name = "url_picture", nullable = false)
    private String urlPicture;

    @Column(name = "date_begin", nullable = false)
    private LocalDateTime dateBegin;

    @Column(name = "date_end", nullable = false)
    private LocalDateTime dateEnd;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "price_per_meter", nullable = false)
    private int pricePerMeter;

    @Column(name="location_price")
    private int locationPrice;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @ManyToOne
    @JoinColumn(nullable = false, name = "organizer_id")
    private Organizer organizer;
}
