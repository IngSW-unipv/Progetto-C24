package it.unipv.ingsfw.gasCorpCinema.controller;



import java.net.URL;
import java.util.ResourceBundle;

import it.unipv.ingsfw.gasCorpCinema.model.Admin;
import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;
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
	private Button backButton;
	@FXML
	private Label myLabel;
	@FXML
	private Label myLabelTotal;
	private Stage stage;
	private SelectProjectionController projectionController;
	private double total;
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		myLabelTotal.setText(Double.toString(total));
		
	}
	
	public void setParameters(double total) {
		this.total=total;
		
	}
	
	public void pressButton() throws Exception {
		myLabel.setText("SUCCESSFUL PAYMENT ✅");
		Thread.sleep(1500);
		//si legge il messaggio "SUCCESSFUL PAYMENT" e dopo 1.5 sec cambia view
		//Thread.sleep(1500);
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
