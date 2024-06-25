package it.unipv.ingsfw.gasCorpCinema.controller;

import java.sql.Time;
import it.unipv.ingsfw.gasCorpCinema.model.PersistenceFacade;
import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;
import it.unipv.ingsfw.gasCorpCinema.utils.AlertUtils;
import it.unipv.ingsfw.gasCorpCinema.utils.StringUtils;
import it.unipv.ingsfw.gasCorpCinema.utils.TimeUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class AddMovieViewController {

	@FXML
	private Button myButton;
	@FXML
	private TextField myTextField1, myTextField2, myTextField3, myTextField4;
	@FXML
	private CheckBox myCheckBox;

	private PersistenceFacade persistence = PersistenceFacade.getInstance(); 

	public void insertMovie() {
		try {
			if (validateFields()) {
				Movie movie = new Movie(0,
					myTextField1.getText(),
					myTextField2.getText(),
					Time.valueOf(myTextField3.getText() + ":00"),
					Integer.parseInt(myTextField4.getText()),
					isTop(myCheckBox.isSelected())
				);

				if (!isDuplicatedMovie(movie)) {
					persistence.insertMovie(movie);
					AlertUtils.showAlert(AlertType.INFORMATION,"Successo", "Film Inserito", movie.toString());
				}else {
					AlertUtils.showAlert(AlertType.ERROR,"Errore di Inserimento", "Il film " + movie.getTitle() + " Esiste già", "Prova ad inserire un altro film");
				}
			}
		} catch (NumberFormatException e) {
			AlertUtils.showAlert(AlertType.ERROR,"Errore di Formato", "Formato Numero Non Valido", "Verifica che i campi numerici contengano numeri validi.");
		} catch (IllegalArgumentException e) {
			AlertUtils.showAlert(AlertType.ERROR,"Errore di Formato", "Formato Orario Non Valido", "Verifica che il campo orario sia nel formato HH:MM o H:MM.");
		}
	}

	public String isTop(boolean isSelected) {
		if(isSelected) {
			return "Top";
		}else {
			return "Nor";
		}
	}

	private boolean validateFields() {
		if (StringUtils.isFieldEmpty(myTextField1)) {
			AlertUtils.showAlert(AlertType.ERROR,"Errore di Validazione", "Campo Nome Vuoto", "Il campo Nome non può essere vuoto.");
			return false;
		}
		if (StringUtils.isFieldEmpty(myTextField2)) {
			AlertUtils.showAlert(AlertType.ERROR,"Errore di Validazione", "Campo Genere Vuoto", "Il campo Genere non può essere vuoto.");
			return false;
		}
		if (StringUtils.isFieldEmpty(myTextField3)) {
			AlertUtils.showAlert(AlertType.ERROR,"Errore di Validazione", "Campo Durata Vuoto", "Il campo Durata non può essere vuoto.");
			return false;
		}
		if (!TimeUtils.isValidTimeFormat(myTextField3.getText())) {
			AlertUtils.showAlert(AlertType.ERROR,"Errore di Formato", "Formato Durata Non Valido", "Verifica che il campo Durata sia nel formato HH:MM o H:MM.");
			return false;
		}
		if (StringUtils.isFieldEmpty(myTextField4)) {
			AlertUtils.showAlert(AlertType.ERROR,"Errore di Validazione", "Campo Anno Vuoto", "Il campo Anno non può essere vuoto.");
			return false;
		}
		if (!StringUtils.isNumeric(myTextField4.getText())) {
			AlertUtils.showAlert(AlertType.ERROR,"Errore di Formato", "Formato Anno Non Valido", "Il campo Anno deve essere un numero.");
			return false;
		}
		return true;
	}


	public boolean isDuplicatedMovie(Movie movie) {
		if (persistence.getMovieByTitle(movie.getTitle()) != null) {
			return true;
		} else {
			return false;
		}
	}
	
}
