package it.unipv.ingsfw.gasCorpCinema.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import it.unipv.ingsfw.gasCorpCinema.model.SaleProcess;
import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;
import it.unipv.ingsfw.gasCorpCinema.model.projection.IProjectionDAO;
import it.unipv.ingsfw.gasCorpCinema.model.projection.Projection;
import it.unipv.ingsfw.gasCorpCinema.model.projection.ProjectionDAO;
import it.unipv.ingsfw.gasCorpCinema.model.role.Admin;
import it.unipv.ingsfw.gasCorpCinema.model.role.User;
import it.unipv.ingsfw.gasCorpCinema.utils.AlertUtils;
import it.unipv.ingsfw.gasCorpCinema.view.homePage.HomePageView;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SelectProjectionViewController implements Initializable {
	
	@FXML
	ListView<Projection> myListView;
	@FXML
	private Button myButton, backButton;
	@FXML
	private Label myLabel, myLabelTotal;
	@FXML
	private Spinner<Integer> mySpinner;
	@FXML
	private ImageView userImageView;
	
	private double total;
	private Admin admin = new Admin();
	private Movie selectedMovie;
	private Projection projection;
	private int numberOfTickets;
	private Stage stage;
	private SaleProcess saleProcess;
	private IProjectionDAO projectionDAO = new ProjectionDAO();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		saleProcess=SaleProcess.getInstance();
		
		selectedMovie=saleProcess.getMovie();
		if(selectedMovie != null) {
			myListView.getItems().addAll(projectionDAO.getAllProjectionsByMovie(selectedMovie.getIdMovie()));
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
	            new SpinnerValueFactory.IntegerSpinnerValueFactory(1, projectionDAO.getNumberOfAvailableSeats(projection));
	            valueFactory.setValue(1);
	            mySpinner.setValueFactory(valueFactory);
	            
	      
	        }
	        
		 mySpinner.valueProperty().addListener(new ChangeListener<Integer>(){
	    			@Override
	    			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
	    				// TODO Auto-generated method stub
	    				
	    				numberOfTickets= mySpinner.getValue();
	    				saleProcess.setNumberOfTickets(numberOfTickets);



	    				total= projection.getPrice() * mySpinner.getValue();
	    				BigDecimal total1= new BigDecimal(total);
	    				BigDecimal newTotal = total1.setScale(2, RoundingMode.HALF_DOWN);
	    				//l'uso del big decimal si rende necessario perche il prodotto tra doube genera un output errato (del tipo 14.0000001)
	    				saleProcess.setTotal(total);
	    				//così il sale process ha i valori aggiornati 
	    	            myLabelTotal.setText(String.valueOf(newTotal));
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
	
	public void pressButton() throws Exception {
		
		//String email = authentication.getEmail();
		String role = saleProcess.getRole();
		
				
		if(projection==null) { 
			myLabel.setText("YOU MUST SELECT A PROJECTION!");
		}else if(mySpinner==null) { 
			myLabel.setText("YOU MUST SELECT THE NUMBER OF TICKET YOU WANT!");
		}else {
			Properties p = new Properties(System.getProperties());

			p.load(new FileInputStream("Properties/Strings"));
			if (role != null) {
				// Ottieni il percorso della vista associata al ruolo
				String viewPath = p.getProperty(role.toLowerCase());

				if (viewPath != null) {
					
					File fxmlFile = new File(viewPath);
					URL fxmlResource = fxmlFile.toURI().toURL();
					changeScene(fxmlResource);
					Stage currentStage = (Stage) myButton.getScene().getWindow();
					currentStage.close();
				}
			}
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
	
	public void logout() throws Exception {
		boolean alert = AlertUtils.showAlertAndWait(AlertType.CONFIRMATION,"Logout","Stai per effettuare il logout",
				"Dopo aver fatto il logout verrai riportato alla homepage.");

		if(alert) {
			Stage currentStage = (Stage) userImageView.getScene().getWindow();

			stage = new Stage();
			HomePageView v = new HomePageView();
			v.start(stage);	

			currentStage.close();
		}
	}
		
}
