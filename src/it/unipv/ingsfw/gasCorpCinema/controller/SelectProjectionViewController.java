package it.unipv.ingsfw.gasCorpCinema.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import it.unipv.ingsfw.gasCorpCinema.model.Admin;
import it.unipv.ingsfw.gasCorpCinema.model.SaleProcess;
import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;
import it.unipv.ingsfw.gasCorpCinema.model.projection.Projection;
import it.unipv.ingsfw.gasCorpCinema.view.homePage.HomePageView;
import it.unipv.ingsfw.gasCorpCinema.view.payment.PaymentView;
import it.unipv.ingsfw.gasCorpCinema.view.selectFilm.SelectFilmView;
import it.unipv.ingsfw.gasCorpCinema.view.selectProjection.SelectProjectionView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SelectProjectionViewController implements Initializable {
	
	@FXML
	ListView<Projection> myListView;
	@FXML
	private Button myButton, backButton;
	@FXML
	Label myLabelTotal;
	@FXML
	private Spinner<Integer> mySpinner;
	@FXML
	private Label myLabel;
	
	private double total;
	private Admin admin = new Admin();
	private Stage stage;
	private Movie selectedMovie;
	private Projection projection;
	private int numberOfTickets;
	private SaleProcess saleProcess;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		saleProcess=SaleProcess.getInstance();
		selectedMovie=saleProcess.getMovie();
		if(selectedMovie != null) {
			myListView.getItems().addAll(admin.getprojectionsByMovie(selectedMovie));
			setListViewListener();
		}
		
		
//		myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Projection>() {
//            @Override
//            public void changed(ObservableValue<? extends Projection> observable, Projection oldValue, Projection newValue) {
//                if (newValue != null) {
//                    
//                	projection = myListView.getSelectionModel().getSelectedItem();;
//                    // Qui puoi aggiornare lo SpinnerValueFactory se necessario
//                }
//            }
//        });
			
//		SpinnerValueFactory <Integer> valueFactory = 
//				new SpinnerValueFactory.IntegerSpinnerValueFactory(1,admin.getNumberOfAvailableSeats(projection));
//		//così facendo lo spinner fa scelgiere solo il nuemro corretto di posti 
//		valueFactory.setValue(1);
//		mySpinner.setValueFactory(valueFactory);
		
	}
	
	public void setParameters(Movie selectedMovie) {
		this.selectedMovie = selectedMovie;
		
		if(myListView != null) {
			myListView.getItems().clear();
			myListView.getItems().addAll(admin.getprojectionsByMovie(selectedMovie));
			setListViewListener();
		}
	}//con sale process questo non serve
	
	 private void setListViewListener() {
	        myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Projection>() {
	            @Override
	            public void changed(ObservableValue<? extends Projection> observable, Projection oldValue, Projection newValue) {
	                if (newValue != null) {
	                    projection = newValue;
	                    updateSpinnerValueFactory();
	                }
	            }
	        });
	    }
	 
	 private void updateSpinnerValueFactory() {
	        if (projection != null) {
	            SpinnerValueFactory<Integer> valueFactory = 
	            new SpinnerValueFactory.IntegerSpinnerValueFactory(1, admin.getNumberOfAvailableSeats(projection));
	            valueFactory.setValue(1);
	            mySpinner.setValueFactory(valueFactory);
	            
	            numberOfTickets= mySpinner.getValue();
	            total = numberOfTickets * projection.getPrice();
	            myLabelTotal.setText(String.valueOf(total));
	            mySpinner.valueProperty().addListener(new ChangeListener<Integer>(){

	    			@Override
	    			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
	    				// TODO Auto-generated method stub
	    				
	    				total = mySpinner.getValue() * projection.getPrice();
	    	            myLabelTotal.setText(String.valueOf(total));
	    			}
	    		});
	        }
	    }
	 
	public void setSelectedMovie(Movie movie) {
        selectedMovie = movie;
    }
	//con sale process qeusto non serve
	
	public Projection getProjection() {
		return projection;
	}
	//serve per aggiornare il nuemro di posti disponibili per  una data proiezione
	public int getNumberOfTickets() {
		return mySpinner.getValue();
	}
	//serve per aggiornare il nuemro di posti disponibili per  una data proiezione e per il pagamento 
	
	public void pressButton() throws Exception {
		
		if(projection==null) { 
			myLabel.setText("YOU MUST SELECT A PROJECTION!");
		}else if(mySpinner==null) { 
			myLabel.setText("YOU MUST SELECT THE NUMBER OF TICKET YOU WANT!");
		}else {
			changeScene("../view/paymentView/Payment.fxml");
			Stage currentStage = (Stage) myButton.getScene().getWindow();
			currentStage.close();
		}
		
		//alla pressione del bottone se è stato selezionato un film cambia la vista per scegliere la proiezione
		//altrimenit visutlaizza il messaggio SELECT A FILM, discorso analogo per lo spinner		
	}
	
	public void changeScene(String fxml) throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
		Parent root = loader.load();
		
	    saleProcess.setNumberOfTickets(numberOfTickets);
	    saleProcess.setTotal(total);
	    saleProcess.setProjection(projection);
		
		Scene scene = new Scene(root);
		stage = new Stage();
		
		stage.setScene(scene);
		stage.show();
		

	}
	public void backView() throws Exception {
		
			Stage currentStage = (Stage) backButton.getScene().getWindow();
			
			stage = new Stage();
			SelectFilmView s = new SelectFilmView();
			s.start(stage);	
			
			currentStage.close();
	}
		
}
