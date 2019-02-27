package daw.dManager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {
	
	@Autowired
	private AnswerRepository repository;

	public Optional<Answer> findOne(long id) {
		return repository.findById(id);
	}

	public List<Answer> findAll() {
		return repository.findAll();
	}

	public void save(Answer answer) {
		repository.save(answer);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}

}
