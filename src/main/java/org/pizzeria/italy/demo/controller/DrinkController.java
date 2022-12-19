package org.pizzeria.italy.demo.controller;

import java.util.List;
import java.util.Optional;

import org.pizzeria.italy.demo.pojo.Drink;
import org.pizzeria.italy.demo.pojo.Pizza;
import org.pizzeria.italy.demo.serv.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/drink")
public class DrinkController {
	
	@Autowired
	private DrinkService drinkService;
	
	@GetMapping
	public String getHome(Model model) {
	List<Drink> drink = drinkService.findAll();
	model.addAttribute("drink",drink);
	return "drink-home";
	}
	@GetMapping("/create")
	public String getCreateDrink(Model model) {
		
		Drink drink = new Drink();
		model.addAttribute("drink", drink);
		
		return "drink-create";
	}
	@PostMapping("/create")
	public String storeDrink(@Valid @ModelAttribute("drink") Drink drink) {
		
		drinkService.save(drink);
		
		return "redirect:/drink";
	}
	@GetMapping("/show/{id}")
	public String getDrinkShow(@PathVariable("id") int id , Model model) {
		
		Optional<Drink> optDrink = drinkService.findById(id);
		Drink drink = optDrink.get();
	if (optDrink.isEmpty()) {
			
			System.err.println("Pizza non presente con id: " + id);
		}
	model.addAttribute("drink",drink);
		
		return "drink-show";
	}
	@GetMapping("/update/{id}")
	public String getDrinkUpdate(@PathVariable("id") int id, Model model) {
		
		Optional<Drink> optDrink = drinkService.findById(id);
		Drink drink = optDrink.get();
		model.addAttribute("drink", drink);
		
		return "drink-update";
	}
	@PostMapping("/update")
	public String updateDrink(@Valid Drink drink) {
		
		drinkService.save(drink);
		
		return "redirect:/drink";
	}
	@GetMapping("/delete/{id}")
	public String deleteDrink(@PathVariable("id") int id) {
		
		Optional<Drink> optDrink = drinkService.findById(id);
		Drink drink = optDrink.get();
		
		drinkService.delete(drink);
		
		return "redirect:/drink";
	}
	@GetMapping("/search")
		public String searchDrinkByName(Model model,
				@RequestParam(name="query" , required = false)String query) {
			List <Drink> drink = null;
			if(query == null) {
				drink = drinkService.findAll();
				model.addAttribute("drink",drink);
			}
			else {
				drink = drinkService.FindByName(query);
			}
			model.addAttribute("drink",drink);
			model.addAttribute("query",query);
			return "drink-search";
		}
	
	

}
