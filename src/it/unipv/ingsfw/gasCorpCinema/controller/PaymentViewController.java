package it.unipv.ingsfw.gasCorpCinema.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import it.unipv.ingsfw.gasCorpCinema.model.PersistenceFacade;
import it.unipv.ingsfw.gasCorpCinema.model.SaleProcessSingleton;
import it.unipv.ingsfw.gasCorpCinema.model.Validation;
import it.unipv.ingsfw.gasCorpCinema.utils.AlertUtils;
import it.unipv.ingsfw.gasCorpCinema.view.homePage.HomePageView;
import it.unipv.ingsfw.gasCorpCinema.view.selectProjection.SelectProjectionView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class PaymentViewController implements Initializable {
	@FXML
	private Button myButton, backButton;
	@FXML
	private Label myLabel, myLabelTotal;
	@FXML
	private TextField labelName, labelSurname, labelNumberOfCC, labelCVV;
	@FXML
	private DatePicker myDatePicker;
	@FXML
	private ImageView userImageView;
	
	private Stage stage;
	private double total;
	private SaleProcessSingleton saleProcess;
	
	private PersistenceFacade persistence = PersistenceFacade.getInstance(); 
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		saleProcess=SaleProcessSingleton.getInstance();
		total=saleProcess.getTotalTicket();
		myLabelTotal.setText(Double.toString(total)+ " €");		
	}
	
	public void pressButton() throws Exception {
		String name = labelName.getText();
		String surname = labelSurname.getText().replaceAll("\\s+", "");
		String numberOfCC = labelNumberOfCC.getText().trim().replaceAll("\\s+", "");
		String cvv = labelCVV.getText().trim();
		
		if(!Validation.nameValidate(name)) { 
			myLabel.setText("INSERT A VALID NAME");
			return;
		}
		if(!Validation.nameValidate(surname)) { 
			myLabel.setText("INSERT A VALID SURNAME");
			return;
		}
		if(!Validation.cardNumberValidate(numberOfCC)) { 
			myLabel.setText("INSERT A VALID CARD");
			return;
		}
		LocalDate expirationDate = myDatePicker.getValue();
		if(!Validation.dateValidate(expirationDate)) {
			myLabel.setText("INSERT A VALID EXPIRATION DATE");
			return;
		}
		if(!Validation.cvvValidate(cvv)) { 
			myLabel.setText("INSERT A VALID CVV");
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
		persistence.decreaseNumberOfAvailableSeats(saleProcess.getProjection(),saleProcess.getNumberOfTickets());
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
	
	public void logout() throws Exception {
		boolean alert = AlertUtils.showAlertAndWait(AlertType.CONFIRMATION,"Logout","Stai per effettuare il logout",
				"Dopo aver fatto il logout verrai riportato alla homepage.");

		if(alert) {
			Stage currentStage = (Stage) userImageView.getScene().getWindow();

			stage = new Stage();
			HomePageView v = new HomePageView();
			v.start(stage);	

			currentStage.close();
		}
	}
}
