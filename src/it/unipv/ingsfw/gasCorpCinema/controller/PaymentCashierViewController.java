package it.unipv.ingsfw.gasCorpCinema.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.unipv.ingsfw.gasCorpCinema.model.SaleProcess;
import it.unipv.ingsfw.gasCorpCinema.model.Validation;
import it.unipv.ingsfw.gasCorpCinema.model.food.FoodDAO;
import it.unipv.ingsfw.gasCorpCinema.model.food.IFoodDAO;
import it.unipv.ingsfw.gasCorpCinema.utils.AlertUtils;
import it.unipv.ingsfw.gasCorpCinema.view.food.FoodChoiceView;
import it.unipv.ingsfw.gasCorpCinema.view.homePage.HomePageView;
import it.unipv.ingsfw.gasCorpCinema.view.selectFilm.SelectFilmView;
import it.unipv.ingsfw.gasCorpCinema.view.selectProjection.SelectProjectionView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class PaymentCashierViewController implements Initializable {

	@FXML
	private Button posButton, endPaymentButton, backButton;
	@FXML
	private TextField importoTextField;
	@FXML
	private Label labelResto, paymentLabel, totalLabel, errorLabel;
	private double totalSale;
	private SaleProcess saleProcess;
	private Stage stage;
	private IFoodDAO foodDAO;
	private Validation validation;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		saleProcess=SaleProcess.getInstance();
		totalSale=saleProcess.getTotalSale();
		foodDAO = new FoodDAO();
		BigDecimal total1= new BigDecimal(totalSale);
		BigDecimal newTotal = total1.setScale(2, RoundingMode.HALF_DOWN);
		totalLabel.setText(String.valueOf(newTotal) + " €");
		validation = new Validation();
	}
	
	public void backView() throws Exception {		
		Stage currentStage = (Stage) backButton.getScene().getWindow();
		stage = new Stage();
		SelectFilmView s = new SelectFilmView();
		s.start(stage);	
		currentStage.close();
	}
	
		
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(input);
//        return matcher.matches();
//	
//	
	
	
	public void endPayment() {
		if(!validation.cvvValidate(importoTextField.getText())) {
			errorLabel.setText("Importo errato");
			return;
		}
		
		
		saleProcess.saleRegistration();
		saleProcess.reset();
		
		Stage currentStage = (Stage) backButton.getScene().getWindow();
		stage = new Stage();
		SelectFilmView s = new SelectFilmView();
		s.start(stage);	
		currentStage.close();
		
	}
	
	public void calcoloResto() {
		errorLabel.setText("");
		try {
			double resto = Double.parseDouble(importoTextField.getText()) - totalSale;
			BigDecimal total1= new BigDecimal(resto);
			BigDecimal totalArrounded = total1.setScale(2, RoundingMode.HALF_DOWN);
			BigDecimal newTotal = total1.setScale(2, RoundingMode.HALF_DOWN);
			if(resto<0) {
				labelResto.setText("All'importo mancano " +  totalArrounded + " euro");
				return;
			}
			labelResto.setText(String.valueOf(newTotal) + " €");
		}catch (NumberFormatException exception) {
			labelResto.setText("Inserire un valore valido");
		}
		
		
		
	}
	
}
