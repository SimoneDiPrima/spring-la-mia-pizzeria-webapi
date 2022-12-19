package org.pizzeria.italy.demo;

import org.pizzeria.italy.demo.pojo.Pizza;
import org.pizzeria.italy.demo.pojo.Promotion;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.pizzeria.italy.demo.pojo.Drink;
import org.pizzeria.italy.demo.pojo.Ingrediente;
import org.pizzeria.italy.demo.serv.DrinkService;
import org.pizzeria.italy.demo.serv.IngredienteService;
import org.pizzeria.italy.demo.serv.PizzaService;
import org.pizzeria.italy.demo.serv.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner{
	@Autowired
	private PizzaService P;
	@Autowired
	private DrinkService ds;
	@Autowired
	private PromotionService promoServ;
	@Autowired
	private IngredienteService ingServ;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		Pizza p1 = new Pizza("Margherita","una rotonda sul mare il nostro disco che suona",5);
		Pizza p2 = new Pizza("Romana", "la piu antica di tutte",7);
		Pizza p3 = new Pizza("Salsiccia e funghi", "la preferita dagli alpini", 9);
		Pizza p4 = new Pizza("Filomena", "la piu formaggiosa di sempre",9);
			
		P.save(p1);
		P.save(p2);
		P.save(p3);
		P.save(p4);
		
		Drink d1 = new Drink("coca","colaaaaaa",9);
		Drink d2 = new Drink("fanta","e vedi che ti mangi",9);
		Drink d3 = new Drink("sprite","Gente di mare",5);
		Drink d4 = new Drink("peroncino","la birra bevuta dai campioni",3);
		Drink d5 = new Drink("Gin Tonic"," dai che è venerdiiii",7);
		
		
		ds.save(d1);
		ds.save(d2);
		ds.save(d3);
		ds.save(d4);
		ds.save(d5);
		
		Promotion prom1 = new Promotion("Promozione Natalizia", LocalDate.parse("2022-12-01"), LocalDate.parse("2022-12-31"));
		Promotion prom2 = new Promotion("Promozione Pasqua", LocalDate.parse("2023-04-01"), LocalDate.parse("2023-05-01"));
		Promotion prom3 = new Promotion("Promozione Estiva", LocalDate.parse("2022-08-01"), LocalDate.parse("2022-09-01"));
		
		promoServ.save(prom1);
		promoServ.save(prom2);
		promoServ.save(prom3);
		
		Ingrediente ing1 = new Ingrediente("mozzarella");
		Ingrediente ing2 = new Ingrediente("pomodoro");
		Ingrediente ing3 = new Ingrediente("funghi");
		Ingrediente ing4 = new Ingrediente("prosciutto cotto");
		Ingrediente ing5 = new Ingrediente("prosciutto crudo");
		Ingrediente ing6 = new Ingrediente("salsiccia");
		
		ingServ.save(ing1);
		ingServ.save(ing2);
		ingServ.save(ing3);
		ingServ.save(ing4);
		ingServ.save(ing5);
		ingServ.save(ing6);
		
		List<Ingrediente> IngPizza = Arrays.asList();
		Pizza ingPizza1 = new Pizza("4 formaggi","voce mi apareceu",4,ing1);
		Pizza ingPizza2 = new Pizza("4 formaggi","dragostea din tei",4,ing1,ing2,ing3,ing4);
		
		P.save(ingPizza1);
		P.save(ingPizza2);
		
		
		Pizza prPizza1 = new Pizza("Mare e monti","la piu strana di sempre",4,prom1);
		Pizza prPizza2 = new Pizza("Pepperoni","la piu stranissima di sempre",4,prom2);
		Pizza prPizza3 = new Pizza("Sampdoria","la piu scarsa di sempre",4,prom3);
		
		P.save(prPizza1);
		P.save(prPizza2);
		P.save(prPizza3);
		
		
		List<Pizza> pizze = P.findAll();
		for (Pizza p : pizze) {
			
			System.out.println(
						p + "\n------------\n" 
						+ p.getPromotion() 
						+ "\n------------\n\n"
					);
		}
		
		
	}

}
