package be.keivy.fleamartapp.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @MappedSuperclass: Indique que cette classe n'est pas une entité complète,
 * mais plutôt une superclasse pour d'autres entités. Les entités qui héritent
 * de cette classe utiliseront ses champs dans leurs propres mappings de table.
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@MappedSuperclass
@Getter
public class BaseEntity <T extends Serializable> {

    @Id
    @GeneratedValue
    private T id;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Setter
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Setter
    private LocalDateTime updatedAt;
}
