package it.unipv.ingsfw.gasCorpCinema.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import it.unipv.ingsfw.gasCorpCinema.model.SaleProcess;
import it.unipv.ingsfw.gasCorpCinema.model.Validation;
import it.unipv.ingsfw.gasCorpCinema.utils.AlertUtils;
import it.unipv.ingsfw.gasCorpCinema.view.food.FoodChoiceView;
import it.unipv.ingsfw.gasCorpCinema.view.homePage.HomePageView;
import it.unipv.ingsfw.gasCorpCinema.view.selectProjection.SelectProjectionView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class PaymentCashierViewController implements Initializable {
	@FXML
	private Button myButton, backButton, foodButton;
	@FXML
	private Label myLabel, myLabelTotal, labelFood;
	@FXML
	private TextField labelName, labelSurname, labelNumberOfCC, labelCVV;
	@FXML
	private ChoiceBox<String> foodChoice, drinkChoice;
	@FXML
    private Spinner<Integer> foodSpinner, drinkSpinner;
	@FXML
	private DatePicker myDatePicker;
	
	private Stage stage;
	private double total;
	private SaleProcess saleProcess;
	private Validation validation;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		saleProcess = SaleProcess.getInstance();
        total = saleProcess.getTotalTicket();
        myLabelTotal.setText(Double.toString(total) + " €");
    }
	
	public void foodChoiceButton() {
		Stage currentStage = (Stage) foodButton.getScene().getWindow();
        stage = new Stage();
        FoodChoiceView s = new FoodChoiceView();
        s.start(stage);
        currentStage.close();
	}

    public void pressButton() throws Exception {
		String name = labelName.getText();
		String surname= labelSurname.getText();
		String numberOfCC= labelNumberOfCC.getText();
		
		if(!validation.nameValidate(name)) { 
			myLabel.setText("INSERT A VALID NAME");
			return;
		}
		if(!validation.nameValidate(surname)) { 
			myLabel.setText("INSERT A VALID SURNAME");
			return;
		}
		if(!validation.cardNumberValidate(numberOfCC)) { 
			myLabel.setText("INSERT A VALID CARD");
			return;
		}
		LocalDate expirationDate = myDatePicker.getValue();
		if(!validation.dateValidate(expirationDate)) {
			myLabel.setText("THE CREDIT CARD INSERT IS EXPIRED");
			return;
		}
		
//		myLabel.setText("SUCCESSFUL PAYMENT ✅");
//
//		System.out.println("prima del thread");
//		Thread.sleep(4000);
//		System.out.println("dopo del thread");
		alertSuccessfullPayment();
		//premo il bottone e dopo 1.5 sec si visualizza "SUCCESSFUL PAYMENT" e dopo altri 1.5 sec cambia view
		saleProcess.saleRegistration();
		saleProcess.reset(); //dobbaimo resettare tutti i dati di sale process
		System.out.println(saleProcess.getNumberOfTickets());
		//l'ideale sarebbe che il metodo saleRegistration restituisca un booleano a secodna che la registrazione
		//della vendiota vada a buon fine
//		stage = new Stage();
//		SelectFilmView v = new SelectFilmView();
//		v.start(stage);	
	}
    
    public void alertSuccessfullPayment() throws Exception {
		boolean alert = AlertUtils.showAlertAndWait(AlertType.CONFIRMATION,"Pagamento effettuato con succcesso","Stai per effettuare il logout",
				"Dopo aver fatto il logout verrai riportato alla homepage.");

		if(alert) {
			Stage currentStage = (Stage) myButton.getScene().getWindow();

			stage = new Stage();
			HomePageView v = new HomePageView();
			v.start(stage);	
			
			currentStage.close();
		}
	}

    public void backView() throws Exception {
        Stage currentStage = (Stage) backButton.getScene().getWindow();
        stage = new Stage();
        SelectProjectionView s = new SelectProjectionView();
        s.start(stage);
        currentStage.close();
    }
}
