package daw.dManager.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/* This service will be used to include features to controller(s)
 */
@Service
public class UnitService {

	@Autowired
	private UnitRepository repository;

	public Optional<Unit> findOne(long id) {
		return repository.findById(id);
	}
	
	public Page<Unit> findAll(Pageable page) {
		return repository.findAll(page);
	}

	public void save(Unit  unit) {
		repository.save(unit);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}

}
