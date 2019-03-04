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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import daw.dManager.services.Concept;
import daw.dManager.services.ConceptService;
import daw.dManager.services.Image;
import daw.dManager.services.Unit;
import daw.dManager.services.UnitService;

@Controller
public class ConceptController {
	
	@Autowired
	private UnitService unitService;
	
	@Autowired
	private ConceptService service;
	
	private static final Path FILES_FOLDER = Paths.get(System.getProperty("user.dir"), "images");

	private AtomicInteger imageId = new AtomicInteger();
	private Map<Integer, Image> images = new ConcurrentHashMap<>();
	
	@PostConstruct
	public void init() throws IOException {

		if (!Files.exists(FILES_FOLDER)) {
			Files.createDirectories(FILES_FOLDER);
		}
	}
	
	
	
	
	@GetMapping("/concept/{id}")
	public String showBook(Model model, @PathVariable long id) {
		
		Optional<Concept> unit = service.findOne(id);

		if(unit.isPresent()) {
			model.addAttribute("concepts", unit.get());
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
		
		Optional<Concept> unit = service.findOne(id);
		
		if(unit.isPresent()) {
			model.addAttribute("concept", unit.get());
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
	
	@RequestMapping(value = "/image/upload", method = RequestMethod.POST)
	public String handleFileUpload(Model model,
			@RequestParam("file") MultipartFile file) {

		int id = imageId.getAndIncrement();
		
		String fileName = "image-" + id + ".jpg";

		if (!file.isEmpty()) {
			try {

				File uploadedFile = new File(FILES_FOLDER.toFile(), fileName);
				file.transferTo(uploadedFile);

				images.put(id, new Image(id, null));

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