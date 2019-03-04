package daw.dManager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import daw.dManager.services.*;
import daw.dManager.users.User;
import daw.dManager.users.UserComponent;

@Controller
public class UserController {

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
	public String showConcepts(Model model, @PageableDefault(size=1) Pageable page) {
		Page<Concept> concepts = service.findAll(page);
		
		model.addAttribute("units", unitService.findAll());
		model.addAttribute("concepts", concepts);
		
		model.addAttribute("showNext", !concepts.isLast());
		model.addAttribute("nextPage", concepts.getNumber()+1);
		return "units";
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
	
	@GetMapping("/register")
	public String register(Model model) {
		
		return "register";
	}
	
	@RequestMapping("/userCreated")
	public String createUser(Model model, @RequestParam String name, @RequestParam String pass ) {
		
		User user = new User(name, pass);
		userComponent.setLoggedUser(user);
		
		return "userCreated";
	}
	
	
	@GetMapping("/error")
	public String Error() {
		return "error";
		
	}
	
	
	
	
	
	
}
