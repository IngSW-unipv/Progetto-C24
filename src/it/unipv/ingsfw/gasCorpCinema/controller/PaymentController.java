package it.unipv.ingsfw.gasCorpCinema.controller;



import it.unipv.ingsfw.gasCorpCinema.view.SelectFilmView;
import it.unipv.ingsfw.gasCorpCinema.view.SelectProjectionView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PaymentController {
	@FXML
	private Button myButton;
	@FXML
	private Label myLabel;
	private Stage stage;
	
	public void pressButton() throws Exception {
		myLabel.setText("SUCCESSFUL PAYMENT ✅");
		
		//stage = new Stage();
		//SelectFilmView v = new SelectFilmView();
		//v.start(stage);
	}
	
}
