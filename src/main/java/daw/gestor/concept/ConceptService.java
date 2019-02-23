package daw.gestor.concept;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/* This service will be used to include features to controller(s)
 */
@Service
public class ConceptService {

	@Autowired
	private ConceptRepository repository;

	public Optional<Concept> findOne(long id) {
		return repository.findById(id);
	}

	public List<Concept> findAll() {
		return repository.findAll();
	}

	public void save(Concept  concept, Unit unit) {
		concept.SetUnit(unit);
		repository.save(concept);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}
}
