package be.keivy.fleamarketapp.dal.repositories;

import be.keivy.fleamarketapp.domain.entities.ZipCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZipCityRepository extends JpaRepository<ZipCity, Long> {

    @Query("SELECT zc FROM ZipCity zc WHERE (:zip IS NULL OR zc.zip ilike :zip% ) AND (:city IS NULL OR zc.city ilike :city% )")
    List<ZipCity> findByZipAndCity(@Param("zip") String zip,
                                   @Param("city") String city);



}
