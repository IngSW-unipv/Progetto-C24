package it.unipv.ingsfw.gasCorpCinema.model;

import it.unipv.ingsfw.gasCorpCinema.view.AdminView;
import it.unipv.ingsfw.gasCorpCinema.view.UserView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CinemaApplication extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        CinemaSingleton.getInstance(); // Ensure the instance is created
        AdminView v = new AdminView();
     //   v.start(stage);
        UserView u = new UserView();
        u.start(stage);
 	
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
