package it.unipv.ingsfw.gasCorpCinema.controller;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;
import it.unipv.ingsfw.gasCorpCinema.model.SaleProcessSingleton;
import it.unipv.ingsfw.gasCorpCinema.model.Validation;
import it.unipv.ingsfw.gasCorpCinema.utils.AlertUtils;
import it.unipv.ingsfw.gasCorpCinema.view.selectFilm.SelectFilmView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
	private SaleProcessSingleton saleProcess;
	private Stage stage;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		saleProcess=SaleProcessSingleton.getInstance();
		totalSale=saleProcess.getTotalSale();
		BigDecimal total1= new BigDecimal(totalSale);
		BigDecimal newTotal = total1.setScale(2, RoundingMode.HALF_DOWN);
		totalLabel.setText(String.valueOf(newTotal) + " €");
	}
	
	public void backView() throws Exception {		
		Stage currentStage = (Stage) backButton.getScene().getWindow();
		stage = new Stage();
		SelectFilmView s = new SelectFilmView();
		s.start(stage);	
		currentStage.close();
	}
	
	public void endPayment() {
		if(!Validation.cvvValidate(importoTextField.getText())) {
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
	
	public void possButtonAction() {
		
		boolean alert = AlertUtils.showAlertAndWait(AlertType.CONFIRMATION,"Messaggio di informazione", 
				"Pagamento avvenuto con successo", 
				"Verrai riportato alla pagina di selezione dei film.");

		if(alert) {
			saleProcess.saleRegistration();
			saleProcess.reset();
			Stage currentStage = (Stage) posButton.getScene().getWindow();

			stage = new Stage();
			SelectFilmView s = new SelectFilmView();
			s.start(stage);	

			currentStage.close();
		}
	}
}
