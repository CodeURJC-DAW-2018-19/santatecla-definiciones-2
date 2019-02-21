package es.codeurjc.daw.library.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import es.codeurjc.daw.library.concept.*;
import es.codeurjc.daw.library.user.UserComponent;

@Controller
public class ConceptWebController {

	@Autowired
	private ConceptService service;
	
	@Autowired
	private UserComponent userComponent;

	@ModelAttribute
	public void addUserToModel(Model model) {
		boolean logged = userComponent.getLoggedUser() != null;
		model.addAttribute("logged", logged);
		if(logged) {
			model.addAttribute("admin", userComponent.getLoggedUser().getRoles().contains("ROLE_ADMIN"));
			model.addAttribute("userName",userComponent.getLoggedUser().getName());
		}
	}
	

	
	@GetMapping("/")
	public String showConcepts(Model model) {

		model.addAttribute("concepts", service.findAll());
	
		return "concept";
	}
	
	@GetMapping("/concept/{id}")
	public String showBook(Model model, @PathVariable long id) {
		
		Optional<ConceptUnit> unit = service.findOne(id);

		if(unit.isPresent()) {
			model.addAttribute("concepts", unit.get());
		}

		return "concepts";
	}
	
	@GetMapping("/newConcept")
	public String newBook(Model model) {
		return "conceptForm";
	}
	
	@GetMapping("/editConcept/{id}")
	public String newBook(Model model, @PathVariable long id) {
		
		Optional<ConceptUnit> unit = service.findOne(id);
		
		if(unit.isPresent()) {
			model.addAttribute("concept", unit.get());
		}
		
		return "conceptForm";
	}
	
	@PostMapping("/saveConcept")
	public String saveBook(Model model, ConceptUnit unit) {
		
		service.save(unit);
		
		return "conceptCreated";
	}
	
	@GetMapping("/deleteConcept/{id}")
	public String deleteBook(Model model, @PathVariable long id) {
		
		service.delete(id);
		
		return "conceptDeleted";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("hideLogin", true);
		return "login";
	}
	
	@GetMapping("/loginerror")
	public String loginError() {
		return "loginerror";
	}
	
	
	@GetMapping("/error")
	public String Error() {
		return "error";
	}
}
