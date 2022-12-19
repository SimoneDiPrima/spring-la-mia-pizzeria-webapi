package org.pizzeria.italy.demo.repo;

import org.pizzeria.italy.demo.pojo.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepo extends JpaRepository<Promotion, Integer> {

}
