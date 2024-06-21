package it.unipv.ingsfw.gasCorpCinema.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


import it.unipv.ingsfw.gasCorpCinema.model.Payment;
import it.unipv.ingsfw.gasCorpCinema.model.SaleProcess;
import it.unipv.ingsfw.gasCorpCinema.view.selectFilm.SelectFilmView;
import it.unipv.ingsfw.gasCorpCinema.view.selectProjection.SelectProjectionView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PaymentViewController implements Initializable {
	@FXML
	private Button myButton, backButton;
	@FXML
	private Label myLabel;
	@FXML
	private Label myLabelTotal;
	@FXML
	private Label labelName;
	@FXML
	private Label labelSurname;
	@FXML
	private Label labelNumberOfCC;
	@FXML
	private Label labelCVV;
	@FXML
	private DatePicker myDatePicker;
	
	private Stage stage;
	private double total;
	private SaleProcess saleProcess;
	private Payment payment;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		saleProcess=SaleProcess.getInstance();
		total=saleProcess.getTotal();
		myLabelTotal.setText(Double.toString(total)+ " €");
	}
	
	public void pressButton() throws Exception {
		String name = labelName.getText();
		String surname= labelSurname.getText();
		String numberOfCC= labelNumberOfCC.getText();
		
		if(payment.nameValidate(name)) { 
			myLabel.setText("INSERT A VALID NAME");
			return;
		}
		if(payment.nameValidate(surname)) { 
			myLabel.setText("INSERT A VALID SURNAME");
			return;
		}
		if(payment.cardNumberValidate(numberOfCC)) { 
			myLabel.setText("INSERT A VALID CARD");
			return;
		}
		LocalDate expirationDate = myDatePicker.getValue();
		if(payment.dateValidate(expirationDate)) {
			myLabel.setText("THE CREDIT CARD INSERT IS EXPIRED");
			return;
		}
		Thread.sleep(1500);
		myLabel.setText("SUCCESSFUL PAYMENT ✅");
		Thread.sleep(1500);
		//premo il bottone e dopo 1.5 sec si visualizza "SUCCESSFUL PAYMENT" e dopo altri 1.5 sec cambia view
		saleProcess.saleRegistration();
		//l'ideale sarebbe che il metodo saleRegistration restituisca un booleano a secodna che la registrazione
		//della vendiota vada a buon fine
		stage = new Stage();
		SelectFilmView v = new SelectFilmView();
		v.start(stage);
		
	}
	
	public void backView() throws Exception {		
		Stage currentStage = (Stage) backButton.getScene().getWindow();
		stage = new Stage();
		SelectProjectionView s = new SelectProjectionView();
		s.start(stage);	
		currentStage.close();
	}	
}
