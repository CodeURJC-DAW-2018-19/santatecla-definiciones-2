package daw.dManager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JustificationService {
	
	@Autowired
	private JustificationRepository repository;

	public Optional<Justification> findOne(long id) {
		return repository.findById(id);
	}

	public List<Justification> findAll() {
		return repository.findAll();
	}

	public void save(Justification justification) {
		repository.save(justification);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}

}

