package it.unipv.ingsfw.gasCorpCinema.model.food;

import java.util.List;

import it.unipv.ingsfw.gasCorpCinema.model.PersistenceFacade;

public class Food {
	
	private String description;
	private int quantity;
	private double price;
	
	private PersistenceFacade persistence = PersistenceFacade.getInstance(); 

	public Food(String description, int quantity, double price) {
		this.description=description;
		this.quantity=quantity;
		this.price=price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public List<String> getAllDescriptions() {
		return persistence.getAllDescriptions();
	}
	
	public Food getFoodByDescription(String description) {
		return persistence.getFoodByDescription(description);
	}
	
	public double getPriceOfFood(String description) {
		return persistence.getPriceOfFood(description);
	}
	

	
	
}
