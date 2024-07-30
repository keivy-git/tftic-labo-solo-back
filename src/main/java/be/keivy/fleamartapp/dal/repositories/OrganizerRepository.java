package be.keivy.fleamartapp.dal.repositories;

import be.keivy.fleamartapp.domain.entities.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizerRepository extends JpaRepository<Organizer, Long> {
}
