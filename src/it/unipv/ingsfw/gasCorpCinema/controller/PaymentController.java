package it.unipv.ingsfw.gasCorpCinema.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.unipv.ingsfw.gasCorpCinema.model.SaleProcess;
import it.unipv.ingsfw.gasCorpCinema.view.SelectFilmView;
import it.unipv.ingsfw.gasCorpCinema.view.SelectProjectionView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PaymentController implements Initializable {
	@FXML
	private Button myButton, backButton;
	@FXML
	private Label myLabel;
	@FXML
	private Label myLabelTotal;
	private Stage stage;
	private double total;
	private int numberOfTikcets;
	private SaleProcess saleProcess;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		saleProcess=SaleProcess.getInstance();
		numberOfTikcets=saleProcess.getNumberOfTickets();
		total=saleProcess.getTotal();
		myLabelTotal.setText(Double.toString(total));
	}
	
	public void pressButton() throws Exception {
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
