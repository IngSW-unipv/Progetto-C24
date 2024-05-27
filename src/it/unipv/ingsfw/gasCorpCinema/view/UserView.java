package it.unipv.ingsfw.gasCorpCinema.view;

import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class UserView extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		try {
            Parent root = FXMLLoader.load(getClass().getResource("FirstPage.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Cinema Application");
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
		
	}

}
