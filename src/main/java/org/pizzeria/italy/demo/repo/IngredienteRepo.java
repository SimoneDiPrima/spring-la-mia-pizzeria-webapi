package org.pizzeria.italy.demo.repo;

import org.pizzeria.italy.demo.pojo.Ingrediente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteRepo  extends JpaRepository<Ingrediente, Integer> {

}
