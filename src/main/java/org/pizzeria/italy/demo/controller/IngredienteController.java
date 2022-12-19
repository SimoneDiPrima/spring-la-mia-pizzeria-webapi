package org.pizzeria.italy.demo.controller;

import java.util.List;

import org.pizzeria.italy.demo.pojo.Ingrediente;
import org.pizzeria.italy.demo.pojo.Pizza;
import org.pizzeria.italy.demo.pojo.Promotion;
import org.pizzeria.italy.demo.serv.IngredienteService;
import org.pizzeria.italy.demo.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredients")
public class IngredienteController {
	@Autowired
	private IngredienteService ingredientService;
	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping
	public String getIngredients(Model model) {
		List<Ingrediente>ingrediente = ingredientService.findAll();
		model.addAttribute("ingrediente",ingrediente);
		return "ingredients";
	}
	
	@GetMapping("/create")
	public String createIngrediente(Model model) {
		
		Ingrediente ingrediente = new Ingrediente();
		model.addAttribute("ingrediente", ingrediente);
		
		List<Pizza> pizze = pizzaService.findAll();
		model.addAttribute("pizze", pizze);
		
		return "ingredients-create";
	}
	@PostMapping("/create")
	public String storeIngrediente(@Valid Ingrediente ingrediente) {
		
		for (Pizza p : ingrediente.getPizze()) {
			
			p.getIngredienti().add(ingrediente);
		}
		
		ingredientService.save(ingrediente);
		
		return "redirect:/ingredients";
	}
	@GetMapping("/update/{id}")
	public String updateIngredient(@PathVariable("id") int id, Model model) {
		Ingrediente ingrediente = ingredientService.findById(id);
		model.addAttribute("ingrediente",ingrediente);
		List<Pizza>pizze = pizzaService.findAll();
		model.addAttribute("pizze",pizze);
		return "ingredients-update";
	}
	
	@PostMapping("/update/{id}")
	public String editIngrediente(
			@PathVariable("id")int id,
			@Valid Ingrediente ingrediente
		) {
		
		Ingrediente oldIng = ingredientService.findById(id);
		
		
		for (Pizza p : oldIng.getPizze()) {
			
			p.getIngredienti().remove(oldIng);
		}
		
		for (Pizza p : ingrediente.getPizze()) {
			
			p.addIngrediente(ingrediente);
		}
		
		
		ingredientService.save(ingrediente);
		
		return "redirect:/";
	}
	
}
