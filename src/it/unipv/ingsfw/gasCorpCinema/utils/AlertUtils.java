package it.unipv.ingsfw.gasCorpCinema.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class AlertUtils {
	    
    public static void showAlert(AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }
    
    public static boolean showAlertAndWait(AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        if(alert.showAndWait().get()== ButtonType.OK) {
        	return true;
        }else {
        	return false;
        }
        
    }

}
