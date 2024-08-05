package be.keivy.fleamarketapp.dal.repositories;

import be.keivy.fleamarketapp.domain.entities.FleaMarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FleaMarketRepository extends JpaRepository<FleaMarket, Long>, JpaSpecificationExecutor<FleaMarket> {

    @Query("select fm from FleaMarket fm where fm.title = :title")
    Optional<FleaMarket> findByFleaMarket(String title);

    @Query("SELECT fm FROM FleaMarket fm WHERE fm.id = :id AND fm.organizer.id = :organizerId")
    Optional<FleaMarket> findByIdAndOrganizerId(Long id, Long organizerId);

    @Query("SELECT fm FROM FleaMarket fm WHERE fm.organizer.id = :id")
    List<FleaMarket> findAllByOrganizerId(Long id);

    @Query("SELECT fm FROM FleaMarket fm WHERE fm.organizer.organizationName = :organizationName")
    List<FleaMarket> findByOrganizationName(String organizationName);

    @Modifying
    @Query("UPDATE FleaMarket fm SET fm.isActive = :isActive WHERE fm.organizer.id = :organizerId")
    void updateAllActiveByOrganizerId(Long organizerId, boolean isActive);

    @Modifying
    @Query("UPDATE FleaMarket fm SET fm.isActive = :isActive WHERE fm.organizer.id IN :organizerId")
    void updateAllActiveFleaMarketByOrganizer(Long organizerId, boolean isActive);

    @Modifying
    @Query("UPDATE FleaMarket fm SET fm.isActive = false WHERE fm.organizer.organizationName IN :organizationName")
    void setInactiveForFleaMarketByOrganizationName(List<String> organizationName);

}
