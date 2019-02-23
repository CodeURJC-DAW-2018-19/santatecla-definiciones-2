package daw.gestor;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import daw.gestor.concept.*;
import daw.gestor.users.User;
import daw.gestor.users.UserRepository;

@Component
public class DatabaseInitializer {
	
	@Autowired
	private UnitRepository unitRepository;
	
	@Autowired
	private ConceptRepository conceptRepository;

	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	public void init() {
		
		// Sample units
		Unit u1 = new Unit("Unidad 1");
		Unit u2 = new Unit("Unidad 2");
		unitRepository.save(u1);
		unitRepository.save(u2);

		//Sample concepts
		Concept c1 = new Concept("Concepto 1");
		Concept c2 = new Concept("Concepto 2");
		
		c1.SetUnit(u1);
		c2.SetUnit(u2);
		
		conceptRepository.save(c1);
		conceptRepository.save(c2);
		
		
	
		// Sample users

		userRepository.save(new User("user", "pass", "ROLE_USER"));
		userRepository.save(new User("user2", "pass", "ROLE_USER"));
		userRepository.save(new User("admin", "pass", "ROLE_USER", "ROLE_ADMIN"));
	}


}
