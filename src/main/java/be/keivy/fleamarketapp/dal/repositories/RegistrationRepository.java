package be.keivy.fleamarketapp.dal.repositories;

import be.keivy.fleamarketapp.domain.entities.Registration;
import be.keivy.fleamarketapp.domain.enums.RegistrationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long>, JpaSpecificationExecutor<Registration> {

    @Query("SELECT r FROM Registration r WHERE r.fleaMarket.id = :fleaMarketId")
    List<Registration> findByFleaMarketId(Long fleaMarketId);

    @Query("SELECT r FROM Registration r WHERE r.id = :id AND r.secondHandDealer.id = :secondHandDealerId")
    Optional<Registration> findByIdAndSecondHandDealerId(Long id, Long secondHandDealerId);

    @Query("SELECT r FROM Registration r WHERE r.secondHandDealer.id = :secondHandDealerId AND r.fleaMarket.id = :fleaMarketId")
    Optional<Registration> findBysSecondHandDealerIdAndFleaMarketId(Long secondHandDealerId, Long fleaMarketId);

    @Modifying
    @Query("DELETE FROM Registration r WHERE r.fleaMarket.id = :fleaMarketId")
    void deleteAllByOrganizerId(Long fleaMarketId);

    @Modifying
    @Query("UPDATE Registration r SET r.registrationStatus = :registrationStatus WHERE r.fleaMarket.id = :fleaMarketId")
    void updateAllStatusBySecondHandDealerId(Long fleaMarketId, RegistrationStatus registrationStatus);
}
