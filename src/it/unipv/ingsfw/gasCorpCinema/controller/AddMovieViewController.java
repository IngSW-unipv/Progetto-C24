package it.unipv.ingsfw.gasCorpCinema.controller;

import java.sql.Time;
import it.unipv.ingsfw.gasCorpCinema.model.Admin;
import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddMovieViewController {
    
    @FXML
    private Button myButton;
    @FXML
    private TextField myTextField1, myTextField2, myTextField3, myTextField4, myTextField5;
    
    private Admin admin = new Admin();
    private Movie movie;
    
    public void insertMovie() {
        try {
            if (validateFields()) {
                movie = new Movie(myTextField1.getText(),myTextField2.getText(),
                		Time.valueOf(myTextField3.getText() + ":00"),
                		Integer.parseInt(myTextField4.getText()),
                		Integer.parseInt(myTextField5.getText()));
                admin.insertMovie(movie);
            }
        } catch (NumberFormatException e) {
            showAlert("Errore di Formato", "Formato Numero Non Valido", "Verifica che i campi numerici contengano numeri validi.");
        } catch (IllegalArgumentException e) {
            showAlert("Errore di Formato", "Formato Orario Non Valido", "Verifica che il campo orario sia nel formato HH:MM o H:MM.");
        }
    }
    
    private boolean validateFields() {
        if (isFieldEmpty(myTextField1)) {
            showAlert("Errore di Validazione", "Campo Nome Vuoto", "Il campo Nome non può essere vuoto.");
            return false;
        }
        if (isFieldEmpty(myTextField2)) {
            showAlert("Errore di Validazione", "Campo Genere Vuoto", "Il campo Genere non può essere vuoto.");
            return false;
        }
        if (isFieldEmpty(myTextField3)) {
            showAlert("Errore di Validazione", "Campo Durata Vuoto", "Il campo Durata non può essere vuoto.");
            return false;
        }
        if (!isValidTimeFormat(myTextField3.getText())) {
            showAlert("Errore di Formato", "Formato Orario Non Valido", "Verifica che il campo Durata sia nel formato HH:MM o H:MM.");
            return false;
        }
        if (isFieldEmpty(myTextField4)) {
            showAlert("Errore di Validazione", "Campo Anno Vuoto", "Il campo Anno non può essere vuoto.");
            return false;
        }
        if (!isNumeric(myTextField4.getText())) {
            showAlert("Errore di Formato", "Formato Anno Non Valido", "Il campo Anno deve essere un numero.");
            return false;
        }
        if (isFieldEmpty(myTextField5)) {
            showAlert("Errore di Validazione", "Campo Rating Vuoto", "Il campo Rating non può essere vuoto.");
            return false;
        }
        if (!isNumeric(myTextField5.getText())) {
            showAlert("Errore di Formato", "Formato Rating Non Valido", "Il campo Rating deve essere un numero.");
            return false;
        }
        return true;
    }
    
    private boolean isFieldEmpty(TextField textField) {
        return textField == null || textField.getText().trim().isEmpty();
    }
    
    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
    
    private boolean isValidTimeFormat(String str) {
        return str.matches("\\d{1,2}:\\d{2}");
    }
    
    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }
}
