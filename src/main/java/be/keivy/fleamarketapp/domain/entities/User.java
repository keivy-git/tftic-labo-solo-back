package be.keivy.fleamarketapp.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @InheritanceType.TABLE_PER_CLASS
 * signifie que chaque classe concrète (non abstraite) qui hérite de User aura sa propre table dans la base de données,
 * avec toutes les colonnes héritées de User plus celles définies dans la sous-classe. Cette stratégie peut être utile pour les performances en lecture,
 * mais elle entraîne une duplication de colonnes dans les tables.
 * @EqualsAndHashCode(callSuper = true)
 * cette annotation génère des méthodes equals() et hashCode(), et l'option callSuper = true inclut les champs de la
 * superclasse BaseEntity dans ces méthodes. Cela assure une comparaison correcte et un calcul de hash code pour les
 * objets de type User, y compris les attributs hérités.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@AllArgsConstructor
@Getter @Setter
@EqualsAndHashCode(callSuper = true)
public abstract class User extends BaseEntity<Long> implements UserDetails {

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "is_enabled", nullable = false)
    private boolean isEnabled;

    @Column(name = "is_expired", nullable = false)
    private boolean isExpired;

    @Column(name = "is_locked", nullable = false)
    private boolean isLocked;

    @Column(name = "is_credentials_expired", nullable = false)
    private boolean isCredentialsExpired;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private Set<Role> roles;

    protected User() {
        this.isExpired = false;
        this.isLocked = false;
        this.isCredentialsExpired = false;
        this.isEnabled = true;
        this.roles = new HashSet<>();
    }

    protected User(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.roles.add(role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !isCredentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
