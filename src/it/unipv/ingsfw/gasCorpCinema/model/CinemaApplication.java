package it.unipv.ingsfw.gasCorpCinema.model;

import it.unipv.ingsfw.gasCorpCinema.view.homePage.HomePageView;
import javafx.application.Application;
import javafx.stage.Stage;

public class CinemaApplication extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        HomePageView homePage = new HomePageView();
        homePage.start(stage);
    }
    
    public static void main(String[] args) {
        launch(args);
        
    }
}
