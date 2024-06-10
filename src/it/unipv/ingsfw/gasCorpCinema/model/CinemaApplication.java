package it.unipv.ingsfw.gasCorpCinema.model;

import it.unipv.ingsfw.gasCorpCinema.view.FirstPageView;
import javafx.application.Application;
import javafx.stage.Stage;

public class CinemaApplication extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        CinemaSingleton.getInstance(); // Ensure the instance is created
        FirstPageView v = new FirstPageView();
        v.start(stage);   
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
