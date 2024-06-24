package it.unipv.ingsfw.gasCorpCinema.model.food;

import java.util.List;

public interface IFoodDAO {

	public Food getFoodByDescription(String description);
	public List<String> getAllDescriptions();
	public double getPriceOfFood(String description);
}
