package daw.dManager.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JustificationRepository extends JpaRepository<Justification, Long> {

}