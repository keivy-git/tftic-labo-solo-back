package be.keivy.fleamartapp.dal.repositories;

import be.keivy.fleamartapp.domain.entities.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
}
