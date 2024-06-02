package it.unipv.ingsfw.gasCorpCinema.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.unipv.ingsfw.gasCorpCinema.model.Admin;
import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SelectFilmController implements Initializable {

	//controller che gestisce le seguenti situazioni:
		//1 setta il titolo dei vari eventi
		//2 click sul bottone pay
		//3 salva titolo e numero ticket così che possano essere decremetnati i posti disponibili nella sala
		
		//un primo problema da risolvere è: a chi chiedere i film salvati nel DB? ci sono 2 soluzioni:
		//chiedere ad admin che per information expert conosce i film salvati (ha già il metodo pronto)
		//accedere direttamente nel DB ed estrarli
		//forse chiedere ad admin covniene dato che cmq admin non fa tante cose e non andiamo a creare tanto accoppiamento 
		
		private Admin admin = new Admin();
		List<Movie> movies = new ArrayList();
		//di tipo List e non arraylist cosi che la reference sia generica 
		@FXML
		private TextField title1;
		@FXML
		private TextField title2;
		@FXML
		private TextField title3;
		@FXML
		private TextField title4;
		private List<TextField> arrayTitles;
		@FXML
		private TextField totalToPay;
		@FXML
		private Spinner<Integer> spinner1;
		@FXML
		private Spinner<Integer> spinner2;
		@FXML
		private Spinner<Integer> spinner3;
		@FXML
		private Spinner<Integer> spinner4;
		@FXML
		private Button buttonPay;
		private Parent root;
		private Stage stage;
		private Scene scene;
	
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			arrayTitles.add(title1);
			arrayTitles.add(title2);
			arrayTitles.add(title3);
			arrayTitles.add(title4);
			
			movies=admin.getAllMovies();	//se avessi messo movies di tipo ArrayList qui per es dovevo fare il cast
			for(int i=0;i<4; i++) {
				arrayTitles.get(i).setText(movies.get(i).getTitle());
			}
			//dovendo scandire 2 oggetti e dovendone modifcare 1 non possiamo usare ne l'iterator ne
			//il ciclo for each ma solo un ciclo for normale 	
			
			
			SpinnerValueFactory <Integer> valueFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10);
			//abbiamo creato lo spinner value factory e abbiamo stabilito che i valori da riportare sono integer in particoalre da 0 a 10
			
			valueFactory1.setValue(0);
			//set valore di default del factory
			
			spinner1.setValueFactory(valueFactory1);
			//abbiamo associato lo spinner factory allo spinner
			
			//ora ripetiamo il tutto per gli altri 3 spinner
			SpinnerValueFactory <Integer> valueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10);
			SpinnerValueFactory <Integer> valueFactory3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10);
			SpinnerValueFactory <Integer> valueFactory4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10);
			valueFactory2.setValue(0);
			valueFactory3.setValue(0);
			valueFactory4.setValue(0);
			spinner2.setValueFactory(valueFactory2);
			spinner3.setValueFactory(valueFactory3);
			spinner4.setValueFactory(valueFactory4);
			
		}
	
		
		public void pay(ActionEvent event) throws IOException {
			root = FXMLLoader.load(getClass().getResource("Payment.fxml"));	
			//associamo la root al file payment.fxml
			stage =(Stage) ((Node)event.getSource()).getScene().getWindow();	
			// prendiamo la sorgente dell'evento event e ne facciamo il cast a Node, cosi facendo possiamo usare i metodi
			//getScene e getWindow che ci restituiscono "la finestra su cui si appoggia la scene) = stage
			// (non è nel formato giusto per questo alla fine si fa il cast)
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			//questo codice serve per far si che alla pressione del bottone pay cambi lo stage che diventa quello di Payment.fxml
		
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	
		
		
		
			

}
