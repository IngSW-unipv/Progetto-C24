package it.unipv.ingsfw.gasCorpCinema.view.addMovie;

import java.net.URL;
import it.unipv.ingsfw.gasCorpCinema.utils.StageUtils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddMovieView extends Application {

	private static final String PAGE_TITLE_KEY = "ADMIN_TITLE";

	@Override
	public void start(Stage stage) throws Exception {

		URL fxmlResource = getClass().getResource("AddMovieView.fxml");

		String cssResource = this.getClass().getResource("F.css").toExternalForm();

		Scene scene = StageUtils.setScene(fxmlResource, cssResource);

		if (scene != null) {
			StageUtils.setAndShowStage(stage, scene, PAGE_TITLE_KEY);
		} else {
			// TODO: Show alert in case of error!!!
		}
	}

}
