package org.pizzeria.italy.demo.pojo;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table
public class Pizza {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@NotNull
	@Column(length = 128)
	private String name;


	@NotNull(message="descriptiom must not be null")
	@Size(min=10, max=130, message="description must be more than 10 charachters")
	@Column(name="description", length=130)
	private String description;
	
	@Min(value=0)
	private int price;
	
	@ManyToOne
	private Promotion promotion;
	
	@ManyToMany
	 private List<Ingrediente> ingredienti;
	
	public Pizza() { }
	public Pizza(String name, String description, int price) {
		setName(name);
		setDescription(description);
		setPrice(price);
		
	}
	public Pizza(String name, String description, int price, Promotion promotion) {
		this(name,description,price);
		setPromotion(promotion);
	}
	
	public Pizza(String name, String description, int price,Ingrediente... ingredienti) {
		this(name,description,price);
		setIngredienti(Arrays.asList(ingredienti));
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}
	
	public Promotion getPromotion() {
		return promotion;
	}
	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}
	
	public List<Ingrediente> getIngredienti() {
		return ingredienti;
	}
	public void setIngredienti(List<Ingrediente> ingredienti) {
		this.ingredienti = ingredienti;
	}
public void addIngrediente(Ingrediente ingrediente) {
		
		boolean finded = false;
		for (Ingrediente i : getIngredienti()) {
			
			if (i.getId() == ingrediente.getId())
				finded = true;
		}
		
		if (!finded)
			getIngredienti().add(ingrediente);
	}
	
		
	@Override
	public String toString(){
		return "\n" + getId()+"\nil nome della pizza è :" + getName()
		+ "\nil suo costo è di :" + getPrice()+ "€"; 
	}
	
}

