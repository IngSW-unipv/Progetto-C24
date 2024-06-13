package it.unipv.ingsfw.gasCorpCinema.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import it.unipv.ingsfw.gasCorpCinema.model.Admin;
import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;
import it.unipv.ingsfw.gasCorpCinema.model.projection.Projection;
import it.unipv.ingsfw.gasCorpCinema.view.FirstPageView;
import it.unipv.ingsfw.gasCorpCinema.view.PaymentView;
import it.unipv.ingsfw.gasCorpCinema.view.SelectFilmView;
import it.unipv.ingsfw.gasCorpCinema.view.SelectProjectionView;
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

public class SelectProjectionController implements Initializable {
	
	@FXML
	ListView<Projection> myListView;
	@FXML
	private Button myButton;
	private Button backButton;
	@FXML
	Label myLabelTotal;
	private double total;
	private Admin admin = new Admin();
	private Stage stage;
	private Projection projection;
	@FXML
	private Spinner<Integer> mySpinner;
	@FXML
	private Label myLabel;
	
	private Movie selectedMovie;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
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
	}
	
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
	            
	            total = mySpinner.getValue() * projection.getPrice();
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
		}
		
		if(mySpinner==null) { 
			myLabel.setText("YOU MUST SELECT THE NUMBER OF TICKET YOU WANT!");
		}
		//total= admin.getPriceOfProjection(projection);
		//myLabelTotal.setText(Double.toString(total));
		//movie = film selezionato
//		stage = new Stage();
//		SelectProjectionView v = new SelectProjectionView();
//		
//		if(movie!=null) {
//			v.start(stage);
//		}else {
//			myLabel.setText("SELECT A FILM!");
//		}
		//alla pressione del bottone se è stato selezionato un film cambia la vista per scegliere la proiezione
		//altrimenit visutlaizza il messaggio SELECT A FILM	
		changeSceneAdmin("../view/Payment.fxml",total);
		
		Stage currentStage = (Stage) myButton.getScene().getWindow();
		currentStage.close();
		
	}
	
	public void changeSceneAdmin(String fxml,double total) throws IOException {
//      Parent pane = FXMLLoader.load(getClass().getResource(fxml));
  	 
//		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
//		Parent root = loader.load();
//		SelectProjectionController controller = loader.getController();
//		controller.setSelectedMovie(movie);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
		//SelectProjectionController controller = new SelectProjectionController(movie);
		//loader.setController(controller);
		Parent root = loader.load();
		PaymentController controller = loader.getController();
		controller.setParameters(total);
	    
		Scene scene = new Scene(root);
		stage = new Stage();
		
		stage.setScene(scene);
		stage.show();
		
	    // Imposta la nuova scena
//	    stage = new Stage();
//		SelectProjectionView v = new SelectProjectionView();
//		try {
//			v.start(stage);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	public void backView() throws Exception {
		
			Stage currentStage = (Stage) backButton.getScene().getWindow();
			
			stage = new Stage();
			SelectFilmView s = new SelectFilmView();
			s.start(stage);	
			
			currentStage.close();
	}
		
}
