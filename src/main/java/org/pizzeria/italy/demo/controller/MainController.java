package org.pizzeria.italy.demo.controller;

import java.util.List;
import java.util.Optional;

import org.pizzeria.italy.demo.pojo.Drink;
import org.pizzeria.italy.demo.pojo.Ingrediente;
import org.pizzeria.italy.demo.pojo.Pizza;
import org.pizzeria.italy.demo.pojo.Promotion;
import org.pizzeria.italy.demo.serv.IngredienteService;
import org.pizzeria.italy.demo.serv.PizzaService;
import org.pizzeria.italy.demo.serv.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller

public class MainController {
	
	@Autowired
	private PizzaService pizzaService;
	@Autowired
	private PromotionService promoService;
	@Autowired
	private IngredienteService ingService;
	
	@GetMapping("/")
	public String getHome(Model model) {
	List<Pizza> pizza = pizzaService.findAll();
	model.addAttribute("pizza",pizza);
	return "home";
	}
	@GetMapping("/pizza/{id}")
	public String getPIzza(@PathVariable("id") int id, Model model) {
		
		Optional<Pizza> optPizza = pizzaService.findById(id);
		
		if (optPizza.isEmpty()) {
			
			System.err.println("Pizza non presente con id: " + id);
		}
		
		Pizza pizza = optPizza.get();
		
		model.addAttribute("pizza",pizza );
		
		return "pizza-show";
	}
	@GetMapping("/pizza/create")
	public String createPizza(Model model) {
		
		Pizza pizza = new Pizza();
		model.addAttribute("pizza", pizza);
		List<Promotion>promotion = promoService.findAll();
		model.addAttribute("promotion",promotion);
		List<Ingrediente> ingrediente = ingService.findAll();
		model.addAttribute("ingrediente",ingrediente);
		
		return "pizza-create";
	}
	@PostMapping("/pizza/create")
	public String storePizza(@Valid @ModelAttribute("pizza") Pizza pizza) {
		
		pizzaService.save(pizza);
		
		return "redirect:/";
	}
	
	@GetMapping("/pizza/update/{id}")
	public String getPizzaUpdate(@PathVariable("id") int id, Model model) {
		
		Optional<Pizza> optPizza = pizzaService.findById(id);
		Pizza pizza = optPizza.get();
		model.addAttribute("pizza", pizza);
		
		return "pizza-update";
	}
	@PostMapping("/pizza/update")
	public String updatePizza(@Valid Pizza pizza) {
		
		pizzaService.save(pizza);
		
		return "redirect:/";
	}
	@GetMapping("/pizza/delete/{id}")
	public String deletePizza(@PathVariable("id") int id) {
		
		Optional<Pizza> optPizza = pizzaService.findById(id);
		Pizza pizza = optPizza.get();
		
		pizzaService.delete(pizza);
		
		return "redirect:/";
	}
	@GetMapping("/pizza/search")
	public String searchPizzaByName(Model model,
			@RequestParam(name="query" , required = false)String query) {
		List <Pizza> pizza = null;
		if(query == null) {
			pizza = pizzaService.findAll();
			model.addAttribute("pizza",pizza);
		}
		else {
			pizza =pizzaService.FindByName(query);
		}
		model.addAttribute("pizza",pizza);
		model.addAttribute("query",query);
		return "pizza-search";
	}

}


