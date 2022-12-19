package org.pizzeria.italy.demo.controller;

import java.util.List;

import org.pizzeria.italy.demo.pojo.Pizza;
import org.pizzeria.italy.demo.pojo.Promotion;
import org.pizzeria.italy.demo.serv.PizzaService;
import org.pizzeria.italy.demo.serv.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/promotions")
public class PromotionController {
	@Autowired
	private PromotionService promoService;
	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping
	public String getHome(Model model) {
	List<Promotion> promotion = promoService.findAll();
	model.addAttribute("promotion",promotion);
	return "promotions";
	}
	
	@GetMapping("create")
	public String createPromozione(Model model) { 
		
		Promotion promotion = new Promotion();
		model.addAttribute("promotion", promotion);
		
		List<Pizza> pizze = pizzaService.findAll();
		model.addAttribute("pizze", pizze);
		
		return "promotions-create";
	}
	@PostMapping("create")
	public String storePromotion(
		@Valid Promotion promotion, 
		BindingResult bindingResult, 
		RedirectAttributes redirectAttributes
	) {
		
		if (bindingResult.hasErrors()) {
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			return "redirect:/promotions/create";
		}
		 
		try {
			
			List<Pizza> pizze = promotion.getPizze();
			for (Pizza pizza : pizze) {
				
				pizza.setPromotion(promotion);
			}
			
			promoService.save(promotion);
		} catch(Exception e) {
			
			redirectAttributes.addFlashAttribute("catchError", e.getMessage());
			
			return "redirect:/promotions/create";
		}
		
		return "redirect:/promotions";
	}
}
