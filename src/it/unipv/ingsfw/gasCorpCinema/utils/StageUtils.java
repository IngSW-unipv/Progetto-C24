package it.unipv.ingsfw.gasCorpCinema.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;

import it.unipv.ingsfw.gasCorpCinema.view.allMovies.AllMoviesView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class StageUtils {
	
	private static final String LOGO_KEY = "LOGO";
	
	public static void setAndShowStage(Stage stage, Scene scene, String resourcesKey) {
		
		Properties p = new Properties(System.getProperties());
		
		try {
			p.load(new FileInputStream("Properties/strings"));
			
			String title = p.getProperty(resourcesKey);
			String logo = p.getProperty(LOGO_KEY);
			String imagePath = Paths.get(logo).toUri().toString();
		    Image icon = new Image(imagePath);
			
			stage.setScene(scene);
			stage.setTitle(title);
			stage.getIcons().add(icon);
			stage.show();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Scene setScene(URL fxmlResource, String cssResource) {
		
		Parent root;
		try {
			root = FXMLLoader.load(fxmlResource);
			Scene scene = new Scene(root);
		    
		    String css = cssResource;
			scene.getStylesheets().add(css);
			
			return scene;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void addToStageAndStart(Application application) throws Exception {
		Stage stage = new Stage();
		application.start(stage);
	}
	
}
