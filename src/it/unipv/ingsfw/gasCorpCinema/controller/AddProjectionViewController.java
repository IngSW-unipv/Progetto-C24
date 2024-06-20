package it.unipv.ingsfw.gasCorpCinema.controller;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import it.unipv.ingsfw.gasCorpCinema.model.Admin;
import it.unipv.ingsfw.gasCorpCinema.model.cinemaHall.CinemaHall;
import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;
import it.unipv.ingsfw.gasCorpCinema.model.projection.Projection;
import it.unipv.ingsfw.gasCorpCinema.utils.AlertUtils;
import it.unipv.ingsfw.gasCorpCinema.utils.StringUtils;
import it.unipv.ingsfw.gasCorpCinema.utils.TimeUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddProjectionViewController implements Initializable {

    @FXML
    private ChoiceBox<CinemaHall> myChoiceBox1;
    @FXML
    private ChoiceBox<Movie> myChoiceBox2;
    @FXML
    private DatePicker myDatePicker;
    @FXML
    private TextField myTextField1, myTextField2;
    @FXML
    private Label myLabel1, myLabel2, myLabel3, myLabel4;
    @FXML
    private Button myButton;

    private Admin admin = new Admin();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        myChoiceBox1.getItems().addAll(admin.getAllHalls());
        myChoiceBox1.setOnAction(this::getIdHall);

        myChoiceBox2.getItems().addAll(admin.getAllMovies());
        myChoiceBox2.setOnAction(this::getTitle);
    }

    public void getDate(ActionEvent event) {
        LocalDate myDate = myDatePicker.getValue();
        String myFormattedDate = myDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        myLabel3.setText(myFormattedDate.toString());
    }

    public void getIdHall(ActionEvent event) {
        myLabel1.setText(String.valueOf(myChoiceBox1.getValue().getIdHall()));
    }

    public void getTitle(ActionEvent event) {
        myLabel2.setText(myChoiceBox2.getValue().getTitle());
    }

    public void createProjection() {
        if (validateFields()) {
            try {
                Projection myProjection = new Projection(myChoiceBox1.getValue().getIdHall(),
                    myChoiceBox1.getValue().getCapacity(),
                    myChoiceBox2.getValue().getTitle(),
                    Date.valueOf(myDatePicker.getValue()),
                    Time.valueOf(myTextField1.getText() + ":00"),
                    Double.parseDouble(myTextField2.getText()));
                
                String result = admin.createProjection(myProjection);
                String result2 = admin.cinemaHallIsAdeguate(myProjection);
                String result3 = admin.PriceIsAdeguate(myProjection);
                
                if(result == null && result2 == null && result3 == null) {
                	AlertUtils.showAlert(AlertType.INFORMATION, "Successo","Proiezione aggiunta",myProjection.toString());
                }else if(result != null) {
                	AlertUtils.showAlert(AlertType.ERROR, "Non è possibile aggiungere questa proiezione" , 
              			  "Conflitto con proiezione già presente in quella fascia oraria", result);
                }else if(result2 != null){
                	AlertUtils.showAlert(AlertType.ERROR, "Non è possibile aggiungere questa proiezione" , 
              			  "Il rating del film non è compatibile con la sala selezionata",
              			  "Film Top -> Capacità >= 300 | Film Nor -> capacità < 300" + "\n" + result2);
                }else if(result3 != null){
                	AlertUtils.showAlert(AlertType.ERROR, "Non è possibile aggiungere questa proiezione" , 
                			  "Il rating del film non è compatibile con il prezzo inserito",
                			  "Film Top -> Prezzo > 4.90 | Film Nor -> Prezzo <= 4.90" + "\n" + result3);
                }
                
            } catch (NumberFormatException e) {
            	AlertUtils.showAlert(AlertType.ERROR, "Errore di Formato", "Formato Numero Non Valido", "Verifica che i campi numerici contengano numeri validi.");
            } catch (IllegalArgumentException e) {
            	AlertUtils.showAlert(AlertType.ERROR, "Errore di Formato", "Formato Orario Non Valido", "Verifica che il campo orario sia nel formato HH:MM o H:MM.");
            }
        }
    }

    private boolean validateFields() {
        if (myChoiceBox1.getValue() == null) {
        	AlertUtils.showAlert(AlertType.ERROR, "Errore di Validazione", "Sala non selezionata", "Devi selezionare una sala.");
            return false;
        }
        if (myChoiceBox2.getValue() == null) {
        	AlertUtils.showAlert(AlertType.ERROR, "Errore di Validazione", "Film non selezionato", "Devi selezionare un film.");
            return false;
        }
        if (myDatePicker.getValue() == null) {
        	AlertUtils.showAlert(AlertType.ERROR, "Errore di Validazione", "Campo Data Vuoto", "Il campo Data non può essere vuoto.");
            return false;
        }
        if (StringUtils.isFieldEmpty(myTextField1)) {
        	AlertUtils.showAlert(AlertType.ERROR, "Errore di Validazione", "Campo Orario Vuoto", "Il campo Orario non può essere vuoto.");
            return false;
        }
        if (!TimeUtils.isValidTimeFormat(myTextField1.getText())) {
        	AlertUtils.showAlert(AlertType.ERROR, "Errore di Formato", "Formato Orario Non Valido", "Verifica che il campo Orario sia nel formato HH:MM o H:MM.");
            return false;
        }
        if (StringUtils.isFieldEmpty(myTextField2)) {
        	AlertUtils.showAlert(AlertType.ERROR, "Errore di Validazione", "Campo Prezzo Vuoto", "Il campo Prezzo non può essere vuoto.");
            return false;
        }
        if (!StringUtils.isNumeric(myTextField2.getText())) {
        	AlertUtils.showAlert(AlertType.ERROR, "Errore di Formato", "Formato Prezzo Non Valido", "Il campo Prezzo deve essere un numero.");
            return false;
        }
        
        if(!TimeUtils.isWithinValidTimeRange(myTextField1.getText())) {
        	AlertUtils.showAlert(AlertType.ERROR, "Errore di Validazione", "Campo Orario Non Valido", "Verifica che l'orario d'inizio sia compreso tra le 15:00 e le 23:00");
        	return false;
        }
        return true;
    }
    
}