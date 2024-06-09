package it.unipv.ingsfw.gasCorpCinema.controller;



import java.net.URL;
import java.util.ResourceBundle;

import it.unipv.ingsfw.gasCorpCinema.model.Admin;
import it.unipv.ingsfw.gasCorpCinema.model.projection.Projection;
import it.unipv.ingsfw.gasCorpCinema.view.SelectFilmView;
import it.unipv.ingsfw.gasCorpCinema.view.SelectProjectionView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PaymentController implements Initializable {
	@FXML
	private Button myButton;
	@FXML
	private Label myLabel;
	@FXML
	private Label myLabelTotal;
	private Stage stage;
	private SelectProjectionController projectionController;
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//c'è da capire come recuperare il prezzo del ticket del film
		int total=projectionController.getNumberOfTickets();
		String totalString= String.valueOf(total);  
		myLabelTotal.setText(totalString);
		
	}
	
	public void pressButton() throws Exception {
		myLabel.setText("SUCCESSFUL PAYMENT ✅");
		Thread.sleep(1500);
		//si legge il messaggio "SUCCESSFUL PAYMENT" e dopo 1.5 sec cambia view
		//stage = new Stage();
		//SelectFilmView v = new SelectFilmView();
		//v.start(stage);
	}

	
	
}