package it.unipv.ingsfw.gasCorpCinema.model.food;

import java.util.List;

public class Food {
	
	private String description;
	private int quantity;
	private double price;
	
	private IFoodDAO foodDAO;
	
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
		return foodDAO.getAllDescriptions();
	}
	
	public Food getFoodByDescription(String description) {
		return foodDAO.getFoodByDescription(description);
	}
	
	public double getPriceOfFood(String description) {
		return foodDAO.getPriceOfFood(description);
	}
	

	
	
}
