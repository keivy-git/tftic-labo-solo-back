package be.keivy.fleamartapp.dal.repositories;

import be.keivy.fleamartapp.domain.entities.ZipCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZipCityRepository extends JpaRepository<ZipCity, Long> {
}
