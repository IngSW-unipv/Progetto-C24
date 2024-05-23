package it.unipv.ingsfw.gasCorpCinema.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddMovieView extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("AddMovieView.fxml"));
		Scene scene = new Scene(root);
		
		stage.setScene(scene);
		stage.show();
	}

}
