package it.unipv.ingsfw.gasCorpCinema.view.userRegistration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import it.unipv.ingsfw.gasCorpCinema.utils.StageUtils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UserRegistrationView extends Application {

	private static final String PAGE_TITLE_KEY = "HOME_PAGE_TITLE";
	private static final String FXML_KEY = "USER_REGISTRATION_FXML";
	private static final String CSS_KEY = "USER_REGISTRATION_CSS";

	@Override
	public void start(Stage stage){
		
		try {
			Properties p = new Properties(System.getProperties());
			p.load(new FileInputStream("Properties/Strings"));
			
			URL fxmlResource = new URL(p.getProperty(FXML_KEY));
			String cssResource = this.getClass().getResource(CSS_KEY).toExternalForm();
			
			Scene scene = StageUtils.setScene(fxmlResource, cssResource);
			
			if (scene != null) {
				StageUtils.setAndShowStage(stage, scene, PAGE_TITLE_KEY);
			} else {
				// TODO: Show alert in case of error!!!
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
