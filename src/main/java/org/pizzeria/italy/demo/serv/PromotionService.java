package org.pizzeria.italy.demo.serv;


import java.util.List;

import org.hibernate.Hibernate;
import org.pizzeria.italy.demo.pojo.Promotion;
import org.pizzeria.italy.demo.repo.PromotionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class PromotionService {
	@Autowired
	private PromotionRepo promotionRepo;
	
	public void save(Promotion promotion) {
		
		promotionRepo.save(promotion);
	}
	public void delete(Promotion promotion) {
		
		promotionRepo.delete(promotion);
	}
	public List<Promotion> findAll() {
		
		return promotionRepo.findAll();
	}
	@Transactional
	public List<Promotion> findAllWPizze() {
		
		List<Promotion> promotions = promotionRepo.findAll();
		
		for (Promotion p : promotions) {
			
			Hibernate.initialize(p.getPizze());
		}
		
		return promotions;
	}
}
