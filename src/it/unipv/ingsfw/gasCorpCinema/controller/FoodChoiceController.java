package it.unipv.ingsfw.gasCorpCinema.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;

import it.unipv.ingsfw.gasCorpCinema.model.SaleProcess;
import it.unipv.ingsfw.gasCorpCinema.model.food.Food;
import it.unipv.ingsfw.gasCorpCinema.model.food.FoodDAO;
import it.unipv.ingsfw.gasCorpCinema.model.food.IFoodDAO;
import it.unipv.ingsfw.gasCorpCinema.view.payment.PaymentCashierView;
import it.unipv.ingsfw.gasCorpCinema.view.selectProjection.SelectProjectionView;
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
import javafx.stage.Stage;



public class FoodChoiceController implements Initializable {
	
	@FXML
	private ChoiceBox <String>foodChoiceBox;
	@FXML
	private Spinner<Integer> quantitySpinner;
	@FXML
	private Button addButton, backButton, payButton;
	@FXML
	private Label totalLabel, labelError;
	@FXML
	private ListView <String> foodListView;
	private String selectedFoodDescription;
	private Food food;

	//private SpinnerValueFactory<Integer> spinner;
	private Food selectedFood;
	private IFoodDAO foodDAO;
	private double totalFood;
	private SaleProcess saleProcess;
	private Stage stage;
	
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		saleProcess=SaleProcess.getInstance();
		foodDAO = new FoodDAO();
		foodChoiceBox.getItems().addAll(foodDAO.getAllDescriptions());
		totalFood=0;
		totalLabel.setText(String.valueOf(totalFood));
		setListenerView();
	}
	
	
	private void setListenerView() {
		 foodChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
				if (newValue != null) {
                    selectedFoodDescription = newValue;
                    selectedFood=foodDAO.getFoodByDescription(selectedFoodDescription);
                    if(selectedFood.getQuantity()<1) {
        				labelError.setText("il prodotto selezionato è terminato");
                    }else {
                    	labelError.setText("");
                    	updateSpinnerValueFactory();
                    }
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
		if(selectedFood.getQuantity()<1) {
			return;
		}
		food = foodDAO.getFoodByDescription(selectedFoodDescription);
		foodListView.getItems().add(food.getDescription() + "  " + Double.toString(food.getPrice()) + "   "+ Integer.toString(quantitySpinner.getValue()));
		totalFood += quantitySpinner.getValue()* selectedFood.getPrice();
		totalLabel.setText(String.valueOf(totalFood));
		foodDAO.decreaseQuantityOfFood(quantitySpinner.getValue(), foodChoiceBox.getSelectionModel().getSelectedItem());
		
	
	
	}
	
	public void payButtonOnAction() {
		saleProcess.setTotalFood(totalFood);
		Stage currentStage = (Stage) payButton.getScene().getWindow();
		stage = new Stage();
		PaymentCashierView p = new PaymentCashierView();
		p.start(stage);
		currentStage.close();
		
	}
	public void backView() throws Exception {		
		Stage currentStage = (Stage) backButton.getScene().getWindow();
		stage = new Stage();
		SelectProjectionView s = new SelectProjectionView();
		s.start(stage);	
		currentStage.close();
	}
	

	

	
	
	
}
