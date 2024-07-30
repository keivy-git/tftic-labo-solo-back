package be.keivy.fleamartapp.dal.repositories;

import be.keivy.fleamartapp.domain.entities.SecondHandDealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondHandDealerRepository extends JpaRepository<SecondHandDealer, Long> {
}
