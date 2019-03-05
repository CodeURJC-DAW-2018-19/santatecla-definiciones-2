package daw.dManager.services;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConceptRepository extends JpaRepository<Concept, Long> {

	Optional<Concept> findOneByTitle(String title);
	

}