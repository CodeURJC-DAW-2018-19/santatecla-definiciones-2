package es.codeurjc.daw.library;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.codeurjc.daw.library.concept.*;
import es.codeurjc.daw.library.user.User;
import es.codeurjc.daw.library.user.UserRepository;

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
		
		// Sample concepts
		
		Concept c1 = new Concept("Concepto 1 dos tres cuatro");
		Concept c2 = new Concept("Concepto 2");
		
		conceptRepository.save(c1);
		conceptRepository.save(c2);

		
		// Sample units
		Unit u1 = new Unit("Unidad 1", c1);
		
		//u1.getConcepts().add(c2);

		
		unitRepository.save(u1);

		
		
	
		// Sample users

		userRepository.save(new User("user", "pass", "ROLE_USER"));
		userRepository.save(new User("admin", "pass", "ROLE_USER", "ROLE_ADMIN"));
	}


}
