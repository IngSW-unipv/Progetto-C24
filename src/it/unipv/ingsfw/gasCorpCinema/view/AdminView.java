package it.unipv.ingsfw.gasCorpCinema.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AdminView extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		
		Parent root = FXMLLoader.load(getClass().getResource("AdminView.fxml"));
		Scene scene = new Scene(root);
		//Image icon = new Image("/../Progetto-C24/Resources/Logo G.A.S. CORP CINEMA .png");
		
		stage.setScene(scene);
		stage.setTitle("G.A.S. CORP CINEMA");
		//stage.getIcons().add(icon);
		stage.show();
	}
}
