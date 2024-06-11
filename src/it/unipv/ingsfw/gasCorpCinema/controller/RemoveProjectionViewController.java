package it.unipv.ingsfw.gasCorpCinema.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.unipv.ingsfw.gasCorpCinema.model.Admin;
import it.unipv.ingsfw.gasCorpCinema.model.projection.Projection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class RemoveProjectionViewController implements Initializable {
	
	@FXML
	private ListView<Projection> myListView;
	@FXML
	private Label myLabel;
	@FXML
	private Button myButton;
	
	Projection selectedProjection;
	Admin admin = new Admin();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		myListView.getItems().addAll(admin.getAllProjections());
		
		if (myListView.getItems().isEmpty()) {
            myLabel.setText("Nessuna proiezione disponibile");
		}
		
		myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Projection>(){

			@Override
			public void changed(ObservableValue<? extends Projection> arg0, Projection arg1, Projection arg2) {
				// TODO Auto-generated method stub
				
				if (myListView.getItems().isEmpty()) {
	                myLabel.setText("Nessuna proiezione disponibile");
	            } else {
	            	selectedProjection = myListView.getSelectionModel().getSelectedItem();
					myLabel.setText(String.valueOf(selectedProjection));
	            }
			}
		});
	}
	
	public void removeProjection() {
		admin.deleteProjection(selectedProjection);
		myListView.getItems().remove(selectedProjection);
	}
}
