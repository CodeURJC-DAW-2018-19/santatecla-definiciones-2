package daw.dManager.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import daw.dManager.services.*;
import daw.dManager.users.User;
import daw.dManager.users.UserComponent;

@Controller
public class WebController {

	@Autowired
	private ConceptService service;
	
	@Autowired
	private UnitService unitService;
	
	@Autowired
	private UserComponent userComponent;
	

	private static final Path FILES_FOLDER = Paths.get(System.getProperty("user.dir"), "images");

	private AtomicInteger imageId = new AtomicInteger();
	private Map<Integer, Image> images = new ConcurrentHashMap<>();
	
	@PostConstruct
	public void init() throws IOException {

		if (!Files.exists(FILES_FOLDER)) {
			Files.createDirectories(FILES_FOLDER);
		}
	}

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
		model.addAttribute("concepts",service.findAll(new PageRequest(0, 2)));


		return "units";
	}
	
	@GetMapping("/table-concept")
	public String showMoreConcepts(Model model, Pageable page) {
		Page<Concept> concepts = service.findAll(page);

		model.addAttribute("concepts", concepts);
		model.addAttribute("indexConcept", concepts.getTotalPages());

		return "pageableConcepts";
	}
	
	
	@GetMapping("/concept/{id}")
	public String showBook(Model model, @PathVariable long id) {
		
		Optional<Concept> concept = service.findOne(id);

		if(concept.isPresent()) {
			model.addAttribute("concepts", concept.get());
			model.addAttribute("images", images.values());
		}

		return "concept";
	}
	
	@GetMapping("/newConcept")
	public String newConcept(Model model) {
		return "conceptForm";
	}
	
	@GetMapping("/editConcept/{id}")
	public String newConcept(Model model, @PathVariable long id) {
		
		Optional<Concept> concept = service.findOne(id);
		
		if(concept.isPresent()) {
			model.addAttribute("concept", concept.get());
			model.addAttribute("images", images.values());
		}
		
		return "conceptForm";
	}
	
	@PostMapping("/saveConcept/{id}")
	public String saveConcept(Model model, String title, @PathVariable long id) {
		Optional<Unit> unit = unitService.findOne(id);
		Concept concept = new Concept(title);
		service.save(concept, unit.get());
		
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
	
	
	@GetMapping("/newUnit")
	public String newUnit(Model model) {
		return "unitForm";
	}
	
	@GetMapping("/editUnit/{id}")
	public String newUnit(Model model, @PathVariable long id) {
		
		Optional<Unit> unit = unitService.findOne(id);
		
		if(unit.isPresent()) {
			model.addAttribute("concept", unit.get());
		}
		
		return "conceptForm";
	} 
	
	@RequestMapping("/saveUnit")
	public String saveUnit(Model model,@RequestParam String title) {
		Unit unit = new Unit(title);
		unitService.save(unit);
		return "unitCreated";
	}
	
	@GetMapping("/deleteUnit/{id}")
	public String deleteUnit(Model model, @PathVariable long id) {
		
		unitService.delete(id);
		
		return "unitDeleted";
	}
	
	@RequestMapping(value = "/image/upload", method = RequestMethod.POST)
	public String handleFileUpload(Model model, String title,
			@RequestParam("file") MultipartFile file) {
		
		int id = imageId.getAndIncrement();
		
		
		String fileName = "image-" + id + ".jpg";

		if (!file.isEmpty()) {
			try {

				File uploadedFile = new File(FILES_FOLDER.toFile(), fileName);
				file.transferTo(uploadedFile);

				images.put(id, new Image(id, null));
				
				Concept c = service.findOneByTitle(title).get();
				c.setImg(fileName.substring(6,7));

				return "uploaded";

			} catch (Exception e) {

				model.addAttribute("error", e.getClass().getName() + ":" + e.getMessage());

				return "uploaded";
			}
		} else {
			
			model.addAttribute("error", "The file is empty");

			return "error";
		}
	}

	// NOTE: The format "/image/{fileName:.+}" avoid Spring MVC remove file
	// extension.

	@RequestMapping("/image/{id}")
	public void handleFileDownload(@PathVariable String id, HttpServletResponse res)
			throws FileNotFoundException, IOException {
		
		String fileName = "image-" + id + ".jpg";
		
		Path image = FILES_FOLDER.resolve(fileName);
		
		
		if (Files.exists(image)) {
			res.setContentType("image/jpeg");
			res.setContentLength((int) image.toFile().length());
			FileCopyUtils.copy(Files.newInputStream(image), res.getOutputStream());

		} else {
			res.sendError(404, "File" + fileName + "(" + image.toAbsolutePath() + ") does not exist");
		}
	}
	
}
