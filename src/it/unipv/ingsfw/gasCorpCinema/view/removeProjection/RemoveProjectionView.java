package it.unipv.ingsfw.gasCorpCinema.view.removeProjection;

import it.unipv.ingsfw.gasCorpCinema.utils.StageUtils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RemoveProjectionView extends Application{
	
	private static final String PAGE_TITLE_KEY = "ADMIN_TITLE";
	private static final String FXML_KEY = "REMOVE_PROJECTION_FXML";
	private static final String CSS_KEY = "REMOVE_PROJECTION_CSS";

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
