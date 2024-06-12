package it.unipv.ingsfw.gasCorpCinema.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import it.unipv.ingsfw.gasCorpCinema.model.Admin;
import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;
import it.unipv.ingsfw.gasCorpCinema.view.AddMovieView;
import it.unipv.ingsfw.gasCorpCinema.view.FirstPageView;
import it.unipv.ingsfw.gasCorpCinema.view.SelectFilmView;
import it.unipv.ingsfw.gasCorpCinema.view.SelectProjectionView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class SelectFilmController implements Initializable {
		//un primo problema da risolvere è: a chi chiedere i film salvati nel DB? 
		//chiedere ad admin che per information expert conosce i film salvati (ha già il metodo pronto) 
		//con un'accoppiamento ancora esiguo
	
	@FXML
	private ListView<Movie> myListView;
	@FXML
	private Button buttonConfirm;
	@FXML
	private Label myLabel;
	private Label userLabel;
	@FXML
	private Button logoutButton;
	
	private Movie selectedMovie;
	private Admin admin = new Admin();
	private Stage stage;
	private String userEmail;
	
	 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		myListView.getItems().addAll(admin.getAllMovies());
		
		if (myListView.getItems().isEmpty()) {
            myLabel.setText("Nessun film disponibile");
		}
		
		myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Movie>(){

			@Override
			public void changed(ObservableValue<? extends Movie> arg0, Movie arg1, Movie arg2) {
				// TODO Auto-generated method stub
				
				if (myListView.getItems().isEmpty()) {
	                myLabel.setText("Nessun film disponibile");
	            } else {
	            	selectedMovie = myListView.getSelectionModel().getSelectedItem();
					myLabel.setText(String.valueOf(selectedMovie));
	            }
			}
		});
	}
	//ListView=lista di film disponibili al cinema 
	
	public void setUserEmail(String email) {
        this.userEmail = email;
        userLabel.setText(email);
    }
	
//	public Movie getMovie() {
//		return selectedMovie;
//	}
	//serve per far si che nel SelectProjectionController si sappia di che film si tratta	
	
	public void pressButton() throws Exception {
		//movie = film selezionato
//		stage = new Stage();
//		SelectProjectionView v = new SelectProjectionView();
//		
//		if(movie!=null) {
//			v.start(stage);
//		}else {
//			myLabel.setText("SELECT A FILM!");
//		}
		//alla pressione del bottone se è stato selezionato un film cambia la vista per
		//scegliere la proiezione, altrimenit visutlaizza il messaggio SELECT A FILM	
		changeSceneAdmin("../view/SelectProjection.fxml",selectedMovie);
		
	}
	
	public void changeSceneAdmin(String fxml,Movie movie) throws IOException {
//      Parent pane = FXMLLoader.load(getClass().getResource(fxml));
  	 
//		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
//		Parent root = loader.load();
//		SelectProjectionController controller = loader.getController();
//		controller.setSelectedMovie(movie);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
		//SelectProjectionController controller = new SelectProjectionController(movie);
		//loader.setController(controller);
		Parent root = loader.load();
		SelectProjectionController controller = loader.getController();
		controller.setParameters(movie);
	    
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
	
	public void logout() throws Exception {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("Stai per effettuare il logout");
		alert.setContentText("Dopo aver fatto il logout verrai riportato alla homepage.");
		
		if(alert.showAndWait().get()== ButtonType.OK) {
			Stage currentStage = (Stage) logoutButton.getScene().getWindow();
			
			stage = new Stage();
			FirstPageView v = new FirstPageView();
			v.start(stage);	
			
			currentStage.close();
		}
	}
}

	
	


