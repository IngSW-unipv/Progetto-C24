package it.unipv.ingsfw.gasCorpCinema.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import it.unipv.ingsfw.gasCorpCinema.model.Admin;
import it.unipv.ingsfw.gasCorpCinema.model.SaleProcess;
import it.unipv.ingsfw.gasCorpCinema.model.User;
import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;
import it.unipv.ingsfw.gasCorpCinema.model.projection.Projection;
import it.unipv.ingsfw.gasCorpCinema.view.selectFilm.SelectFilmView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
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
	private User user = new User();
	private Movie selectedMovie;
	private Projection projection;
	private int numberOfTickets;
	private SaleProcess saleProcess;
	private String userEmail;
	
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
	            
	      
	        }
	        
		 mySpinner.valueProperty().addListener(new ChangeListener<Integer>(){
	    			@Override
	    			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
	    				// TODO Auto-generated method stub
	    				
	    				numberOfTickets= mySpinner.getValue();
	    				saleProcess.setNumberOfTickets(numberOfTickets);
	    				
	    				
	    				total=mySpinner.getValue() * projection.getPrice();
	    				saleProcess.setTotal(total);
	    				//così il sale process ha i valori aggiornati 
	    	            myLabelTotal.setText(String.valueOf(total));
	    			} 	    	            
		});       
	  }
	 
	
	public Projection getProjection() {
		return projection;
	}
	//serve per aggiornare il nuemro di posti disponibili per  una data proiezione
	public int getNumberOfTickets() {
		return mySpinner.getValue();
	}
	//serve per aggiornare il numero di posti disponibili per  una data proiezione e per il pagamento 
	
	public void setUserEmail(String email) {
		this.userEmail = email;
	}
	
	public void pressButton() throws Exception {
		
		String role = user.getRoleByEmail(this.userEmail);
		
		if(projection==null) { 
			myLabel.setText("YOU MUST SELECT A PROJECTION!");
		}else if(mySpinner==null) { 
			myLabel.setText("YOU MUST SELECT THE NUMBER OF TICKET YOU WANT!");
		}else {
//			Properties p = new Properties(System.getProperties());
//
//			p.load(new FileInputStream("Properties/Strings"));
//
//			if (role != null) {
//
//				// Ottieni il percorso della vista associata al ruolo
//				String viewPath = p.getProperty(role.toLowerCase());
//
//				if (viewPath != null) {
//					
//					File fxmlFile = new File(viewPath);
//					URL fxmlResource = fxmlFile.toURI().toURL();
//					changeScene(fxmlResource);
//				}
//			}
//		}
			Properties p = new Properties(System.getProperties());
			p.load(new FileInputStream("Properties/Strings"));
			
			String viewPath = p.getProperty("PAYMENT_FXML");
			File fxmlFile = new File(viewPath);
			URL fxmlResource = fxmlFile.toURI().toURL();
			
			changeScene(fxmlResource);
			Stage currentStage = (Stage) myButton.getScene().getWindow();
			currentStage.close();
		}
		
		//alla pressione del bottone se è stato selezionato un film cambia la vista per scegliere la proiezione
		//altrimenit visutlaizza il messaggio SELECT A FILM, discorso analogo per lo spinner		
	}
	
	public void changeScene(URL fxml) throws IOException {
		
		FXMLLoader loader = new FXMLLoader(fxml);
        Parent root = loader.load();
		
	    saleProcess.setNumberOfTickets(numberOfTickets);
	    saleProcess.setTotal(total);
	    saleProcess.setProjection(projection);
		
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		
		stage.setScene(scene);
		stage.show();
	}
	
	public void backView() throws Exception {
		
			Stage currentStage = (Stage) backButton.getScene().getWindow();
			
			Stage stage = new Stage();
			SelectFilmView s = new SelectFilmView();
			s.start(stage);	
			
			currentStage.close();
	}
		
}
