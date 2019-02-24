package daw.gestor;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import daw.gestor.services.*;
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
		Unit u1 = new Unit("Introducción");
		Unit u2 = new Unit("Ataques y Contramedidas");
		unitRepository.save(u1);
		unitRepository.save(u2);

		//Sample concepts
		Concept c1 = new Concept("Plan Director de Seguridad");
		Concept c2 = new Concept("ARP Poisoning");
		Concept c3 = new Concept("TCP/UDP");
		Concept c4 = new Concept("Fichero Malicioso");
		Concept c5 = new Concept("Ataque Hacker");
		
		c1.SetUnit(u1);
		c2.SetUnit(u2);
		c3.SetUnit(u1);
		c4.SetUnit(u1);
		c5.SetUnit(u2);
		
		conceptRepository.save(c1);
		conceptRepository.save(c2);
		conceptRepository.save(c3);
		conceptRepository.save(c4);
		conceptRepository.save(c5);
		
		
	
		// Sample users

		userRepository.save(new User("user", "pass", "ROLE_USER"));
		userRepository.save(new User("user2", "pass", "ROLE_USER"));
		userRepository.save(new User("admin", "pass", "ROLE_USER", "ROLE_ADMIN"));
	}


}
