package it.unipv.ingsfw.gasCorpCinema.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.unipv.ingsfw.gasCorpCinema.model.Admin;
import it.unipv.ingsfw.gasCorpCinema.model.projection.Projection;
import it.unipv.ingsfw.gasCorpCinema.view.PaymentView;
import it.unipv.ingsfw.gasCorpCinema.view.SelectProjectionView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

public class SelectProjectionController implements Initializable {
	@FXML
	ListView<Projection> myListView;
	@FXML
	Button myButton;
	private Admin admin = new Admin();
	private SelectFilmController s= new SelectFilmController();
	private Stage stage;
	private Projection projection;
	@FXML
	private Spinner<Integer> mySpinner;
	@FXML
	private Label myLabel;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//myListView.getItems().addAll(admin.getProjectionbyMovie(s.getMovie());
		projection=myListView.getSelectionModel().getSelectedItem();
		SpinnerValueFactory <Integer> valueFactory = 
				new SpinnerValueFactory.IntegerSpinnerValueFactory(1,admin.getNumberOfAvailableSeats(projection));
		//così facendo lo spinner fa scelgiere solo il nuemro corretto di posti 
		valueFactory.setValue(1);
		mySpinner.setValueFactory(valueFactory);
		
	}
	
	public void pressButton() throws Exception {
		stage = new Stage();
		PaymentView v = new PaymentView();
		
		if(projection!=null) { 
			v.start(stage);
		}else {
			myLabel.setText("YOU MUST SELECT A PROJECTION!");
		}
	}

	public Projection getProjection() {
		return projection;
	}
	//serve per aggiornare il nuemro di posti disponibili per  una data proiezione
	public int getNumberOfTickets() {
		return mySpinner.getValue();
	}
	//serve per aggiornare il nuemro di posti disponibili per  una data proiezione e per il pagamento 
	
	
	
	
		
}
