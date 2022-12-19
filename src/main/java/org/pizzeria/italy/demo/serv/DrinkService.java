package org.pizzeria.italy.demo.serv;

import java.util.List;
import java.util.Optional;

import org.pizzeria.italy.demo.pojo.Drink;
import org.pizzeria.italy.demo.pojo.Pizza;
import org.pizzeria.italy.demo.repo.DrinkRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DrinkService {
	
	@Autowired
	private DrinkRepo drinkRepo;
	
	public void save(Drink drink) {
		drinkRepo.save(drink);
		
	}
	
	public List<Drink> findAll(){
		return drinkRepo.findAll();
	}
public void delete(Drink drink) {
		
		drinkRepo.delete(drink);
	}
	public void deleteById(int id) {
		
		drinkRepo.deleteById(id);
	}

	public Optional<Drink> findById(int id) {
		
		return drinkRepo.findById(id);
	}
	public List <Drink> FindByName(String name){
		return drinkRepo.findByNameContainingIgnoreCase(name);
	}

	
}
