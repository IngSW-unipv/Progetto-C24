package it.unipv.ingsfw.gasCorpCinema.view;

import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AllMoviesView extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("AllMoviesView.fxml"));
		Scene scene = new Scene(root);
		String imagePath = Paths.get("Resources/Logo G.A.S. CORP CINEMA .png").toUri().toString();
        Image icon = new Image(imagePath);
        
		String css = this.getClass().getResource("AdminView.css").toExternalForm();
		scene.getStylesheets().add(css);
		
		stage.setScene(scene);
		stage.setTitle("G.A.S. CORP CINEMA");
		stage.getIcons().add(icon);
		stage.show();
	}
}
