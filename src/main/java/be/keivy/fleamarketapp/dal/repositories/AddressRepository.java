package be.keivy.fleamarketapp.dal.repositories;

import be.keivy.fleamarketapp.domain.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
