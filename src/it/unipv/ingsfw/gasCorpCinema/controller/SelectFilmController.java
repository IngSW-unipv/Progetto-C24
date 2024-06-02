package it.unipv.ingsfw.gasCorpCinema.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.unipv.ingsfw.gasCorpCinema.model.Admin;
import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class SelectFilmController implements Initializable {

	//controller che gestisce le seguenti situazioni:
		//1 setta il titolo dei vari eventi
		//2 aggiorna il totale in tempo reale
		//3 click sul bottone pay
		//4 salva titolo e numero ticket così che possano essere decremetnati i posti disponibili nella sala
		
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
	
		
		
		
		//1 dobbiamo inizializzare i titoli
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
		}
		
		
		
		
		//2 aggiorniamo il totale in tempo reale
		//per fare ciò è necessario moltiplicare il numero di ticket selezionati per il costo del film
		

		
		
		
			

}
