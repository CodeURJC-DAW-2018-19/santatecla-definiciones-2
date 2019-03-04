package daw.dManager.controllers;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import daw.dManager.services.Unit;
import daw.dManager.services.UnitService;

@Controller
public class UnitController {
	
	@Autowired
	private UnitService unitService;
	
	
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

}
