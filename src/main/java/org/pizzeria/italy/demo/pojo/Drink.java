package org.pizzeria.italy.demo.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
@Entity
@Table
public class Drink {

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


	
	@Size(min=4, max=130, message="description must be more than 4 charachters")
	@Column(name="description", length=130)
	private String description;
	
	@Min(value=0)
	private int price;
	public Drink() {}
	public Drink(String name, String description, int price) {
		setName(name);
		setDescription(description);
		setPrice(price);
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
	@Override
	public String toString(){
		return "\n" + getId()+"\nil nome del drink è :" + getName()
		+ "\nil suo costo è di :" + getPrice()+ "€"; 
	}
	
	
}
