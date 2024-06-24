package it.unipv.ingsfw.gasCorpCinema.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;

import it.unipv.ingsfw.gasCorpCinema.model.SaleProcess;
import it.unipv.ingsfw.gasCorpCinema.model.food.Food;
import it.unipv.ingsfw.gasCorpCinema.model.food.FoodDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;



public class FoodChoiceController implements Initializable {
	
	@FXML
	private ChoiceBox <String>foodChoiceBox;
	@FXML
	private Spinner<Integer> quantitySpinner;
	@FXML
	private Button addButton, receiptButton, payButton;
	@FXML
	private Label totalLabel;
	@FXML
	private ListView <String> foodListView;
	private String selectedFoodDescription;
	private Food food;

	//private SpinnerValueFactory<Integer> spinner;
	private Food selectedFood;
	private FoodDAO foodDAO;
	private double totalFood;
	private SaleProcess saleProcess;
	
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		saleProcess=SaleProcess.getInstance();
		foodDAO = new FoodDAO();
		foodChoiceBox.getItems().addAll(foodDAO.getAllDescriptions());
		setListenerView();
		totalFood=0;
		totalLabel.setText(String.valueOf(totalFood));
		
	}
	
	
	private void setListenerView() {
		 foodChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
				if (newValue != null) {
                    selectedFoodDescription = newValue;
                    updateSpinnerValueFactory();   
                }	
			}
		 });   
	}
	
	private void updateSpinnerValueFactory() {
		if (selectedFoodDescription != null) {
			selectedFood = foodDAO.getFoodByDescription(selectedFoodDescription);
            SpinnerValueFactory<Integer> valueFactory = 
            new SpinnerValueFactory.IntegerSpinnerValueFactory(1,selectedFood.getQuantity() );
            valueFactory.setValue(1);
            quantitySpinner.setValueFactory(valueFactory);
		}
	}
	
	public void addButtonOnAction () {

		food = foodDAO.getFoodByDescription(selectedFoodDescription);
		foodListView.getItems().add(food.getDescription() + "  " + Double.toString(food.getPrice()) + "   "+ Integer.toString(quantitySpinner.getValue()));
		totalFood += quantitySpinner.getValue()* selectedFood.getPrice();
		totalLabel.setText(String.valueOf(totalFood));
		
	
	
	
	}
	
	public void payButtonOnAction() {
		saleProcess.setTotalFood(totalFood);
		System.out.println(saleProcess.getTotalFood());
	}
	

	

	
	
	
}
