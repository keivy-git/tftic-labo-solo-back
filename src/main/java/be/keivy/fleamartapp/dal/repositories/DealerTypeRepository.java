package be.keivy.fleamartapp.dal.repositories;

import be.keivy.fleamartapp.domain.entities.DealerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealerTypeRepository extends JpaRepository<DealerType, Long> {
}
