package it.unipv.ingsfw.gasCorpCinema.model;

import it.unipv.ingsfw.gasCorpCinema.view.UserView;
import javafx.application.Application;
import javafx.stage.Stage;

public class CinemaApplication extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        CinemaSingleton.getInstance(); // Ensure the instance is created
        UserView u = new UserView();
        u.start(stage);
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
