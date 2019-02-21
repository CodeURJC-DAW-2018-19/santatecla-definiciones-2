package es.codeurjc.daw.library.concept;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/* This service will be used to include features to controller(s)
 */
@Service
public class ConceptService {

	@Autowired
	private UnitRepository repository;

	public Optional<ConceptUnit> findOne(long id) {
		return repository.findById(id);
	}

	public List<ConceptUnit> findAll() {
		return repository.findAll();
	}

	public void save(ConceptUnit  unit) {
		repository.save(unit);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}
}
