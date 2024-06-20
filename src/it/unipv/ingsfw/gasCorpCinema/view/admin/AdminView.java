package it.unipv.ingsfw.gasCorpCinema.view.admin;

import it.unipv.ingsfw.gasCorpCinema.utils.StageUtils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminView extends Application {

	private static final String PAGE_TITLE_KEY = "ADMIN_TITLE";
	private static final String FXML_KEY = "ADMIN";
	private static final String CSS_KEY = "ADMIN_CSS";

	@Override
	public void start(Stage stage){

		Scene scene = StageUtils.setScene(FXML_KEY, CSS_KEY);

		if (scene != null) {
			StageUtils.setAndShowStage(stage, scene, PAGE_TITLE_KEY);
		} else {
			// TODO: Show alert in case of error!!!
		}
	}
}
