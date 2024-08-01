package be.keivy.fleamarketapp.dal.repositories;

import be.keivy.fleamarketapp.domain.entities.FleaMarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FleaMarketRepository extends JpaRepository<FleaMarket, Long> {

    @Query("select fm from FleaMarket fm where fm.title = :title")
    Optional<FleaMarket> findByFleaMarket(String title);
}
