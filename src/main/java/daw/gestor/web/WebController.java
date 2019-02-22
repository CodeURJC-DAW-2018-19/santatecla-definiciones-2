package daw.gestor.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import daw.gestor.concept.*;
import daw.gestor.users.UserComponent;

@Controller
public class WebController {

	@Autowired
	private ConceptService service;
	
	@Autowired
	private UnitService unitService;
	
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

		model.addAttribute("units", unitService.findAll());
		model.addAttribute("concepts", service.findAll());
	
		return "concepts";
	}
	
	@GetMapping("/concept/{id}")
	public String showBook(Model model, @PathVariable long id) {
		
		Optional<Concept> unit = service.findOne(id);

		if(unit.isPresent()) {
			model.addAttribute("concepts", unit.get());
		}

		return "concept";
	}
	
	@GetMapping("/newConcept")
	public String newConcept(Model model) {
		return "conceptForm";
	}
	
	@GetMapping("/editConcept/{id}")
	public String newConcept(Model model, @PathVariable long id) {
		
		Optional<Concept> unit = service.findOne(id);
		
		if(unit.isPresent()) {
			model.addAttribute("concept", unit.get());
		}
		
		return "conceptForm";
	}
	
	@PostMapping("/saveConcept")
	public String saveConcept(Model model, Concept concept) {
		
		service.save(concept);
		
		return "conceptCreated";
	}
	
	@GetMapping("/deleteConcept/{id}")
	public String deleteConcept(Model model, @PathVariable long id) {
		
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
	
	
	@GetMapping("/newUnit")
	public String newUnit(Model model) {
		return "unitForm";
	}
	
	@GetMapping("/editUnit/{id}")
	public String newUnitk(Model model, @PathVariable long id) {
		
		Optional<Unit> unit = unitService.findOne(id);
		
		if(unit.isPresent()) {
			model.addAttribute("concept", unit.get());
		}
		
		return "conceptForm";
	} 
	
	@PostMapping("/saveUnit")
	public String saveUnit(Model model, Unit unit) {
		
		unitService.save(unit);
		
		return "unitCreated";
	}
	
	@GetMapping("/deleteUnit/{id}")
	public String deleteUnit(Model model, @PathVariable long id) {
		
		unitService.delete(id);
		
		return "unitDeleted";
	}
	
	
}
