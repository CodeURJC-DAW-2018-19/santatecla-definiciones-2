package daw.dManager;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import daw.dManager.services.*;
import daw.dManager.users.User;
import daw.dManager.users.UserRepository;

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
		Unit u3 = new Unit("Estructuras de datos");
		Unit u4 = new Unit("Arquitectura de computadores");
		Unit u5 = new Unit("Ingeniería de Requisitos");
		Unit u6 = new Unit("Calidad del Software");
		Unit u7 = new Unit("Orientación a Objetos");
		Unit u8 = new Unit("Diseño y Análisis de Algoritmos");
		Unit u9 = new Unit("Procesos de Software");
		Unit u10 = new Unit("Estadística");
		Unit u11 = new Unit("Investigación Operativa");
		Unit u12 = new Unit("Introducción a la Informática");
		unitRepository.save(u1);
		unitRepository.save(u2);
		unitRepository.save(u3);
		unitRepository.save(u4);
		unitRepository.save(u5);
		unitRepository.save(u6);
		unitRepository.save(u7);
		unitRepository.save(u8);
		unitRepository.save(u9);
		unitRepository.save(u10);
		unitRepository.save(u11);
		unitRepository.save(u12);
		

		//Sample concepts
		Concept c1 = new Concept("Plan Director de Seguridad");
		Concept c2 = new Concept("ARP Poisoning");
		Concept c3 = new Concept("TCP/UDP");
		Concept c4 = new Concept("Fichero Malicioso");
		Concept c5 = new Concept("Ataque Hacker");
		Concept c6 = new Concept("Plan Director de Seguridad");
		Concept c7 = new Concept("ARP Poisoning");
		Concept c8 = new Concept("TCP/UDP");
		Concept c9 = new Concept("Fichero Malicioso");
		Concept c10 = new Concept("Ataque Hacker");
		
		c1.SetUnit(u1);
		c2.SetUnit(u2);
		c3.SetUnit(u1);
		c4.SetUnit(u1);
		c5.SetUnit(u2);
		
		c6.SetUnit(u1);
		c7.SetUnit(u1);
		c8.SetUnit(u1);
		c9.SetUnit(u1);
		c10.SetUnit(u1);
		
		conceptRepository.save(c1);
		conceptRepository.save(c2);
		conceptRepository.save(c3);
		conceptRepository.save(c4);
		conceptRepository.save(c5);
		
		conceptRepository.save(c6);
		conceptRepository.save(c7);
		conceptRepository.save(c8);
		conceptRepository.save(c9);
		conceptRepository.save(c10);
		
		
	
		// Sample users

		userRepository.save(new User("user", "pass", "ROLE_USER"));
		userRepository.save(new User("user2", "pass", "ROLE_USER"));
		userRepository.save(new User("admin", "pass", "ROLE_USER", "ROLE_ADMIN"));
	}


}