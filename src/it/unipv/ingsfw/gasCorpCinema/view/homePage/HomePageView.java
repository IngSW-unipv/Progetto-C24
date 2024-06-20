package it.unipv.ingsfw.gasCorpCinema.view.homePage;

import java.net.URL;
import it.unipv.ingsfw.gasCorpCinema.utils.StageUtils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomePageView extends Application {
	
	private static final String PAGE_TITLE_KEY = "HOME_PAGE_TITLE";

	@Override
	public void start(Stage stage){
		
		URL fxmlResource = getClass().getResource("FirstPage.fxml");
		
		String cssResource = this.getClass().getResource("FirstPage.css").toExternalForm();
		
		Scene scene = StageUtils.setScene(fxmlResource, cssResource);
		
		if (scene != null) {
			StageUtils.setAndShowStage(stage, scene, PAGE_TITLE_KEY);
		} else {
			// TODO: Show alert in case of error!!!
		}
	}

}
