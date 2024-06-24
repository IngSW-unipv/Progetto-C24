package it.unipv.ingsfw.gasCorpCinema.model;


import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;
import it.unipv.ingsfw.gasCorpCinema.model.projection.Projection;
import it.unipv.ingsfw.gasCorpCinema.model.register.Register;

public class SaleProcess {
	//la funzione di questa classe è quella di salvare tutte le info necessarie man mano 
	//che il cliente le sceglie, quando effettivmaente il cliente paga allora il sale process 
	//passa al register le info necessarie. 
	//In assenza di sale process ci sono 2 scenari:
	//_register prende da select projection le info che gli servono e aspetta che il cliente 
	//prema pay in payment controller per registrare effettivamente le cose (accoppiamento con payment e select projection cotnroller)
	//_l'alternativa è che select projection controller passi a payment le info necessarie e quando il cliente 
	//preme pay passa tutto a register (accoppiamento payment e select projection controller)
	//in entrabi i casi si crea accoppiamento, con sale process invece l'accoppiamento rimane basso poiche sale process nasce proprio per questo
	
	//NB è un sin gleton poiche vogliamo che select projection cotnroller e payment facciano
	//riferiemtno allo stesso sale process senza che questi 2 si passino l'istanza (riducendo l'accoppiamento)
	
	
	private static SaleProcess instance;
	private Movie movie;
	//arriva da select fil projection
	private int numberOfTickets;
	private double totalTicket;
	private Projection projection;
	//questi ultimi 3 arrivano da select projection controller
	private Register register;
	private String role;
	private double totalFood;
	private double totalSale;
	
	
	
	
	public double getTotalSale() {
		totalSale= totalTicket + totalFood;
		return totalSale;
	}


	public double getTotalFood() {
		return totalFood;
	}


	public void setTotalFood(double totalFood) {
		this.totalFood = totalFood;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	private SaleProcess() {
		register=Register.getInstance();
	}
	
	
	public static SaleProcess getInstance() {
		if (instance==null) {
			instance = new SaleProcess();
		}
		return instance;
	}

	public void setNumberOfTickets(int numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}

	public void setProjection(Projection projection) {
		this.projection = projection;
	}

	public int getNumberOfTickets() {
		return numberOfTickets;
	}

	public Projection getProjection() {
		return projection;
	}

	public double getTotalTicket() {
		return totalTicket;
	}

	public void setTotalTicket(double total) {
		this.totalTicket = total;
	}
	
	
	
	public Movie getMovie() {
		return movie;
	}


	public void setMovie(Movie movie) {
		this.movie = movie;
	}


	public void saleRegistration() {
		register.saleRegistration(projection, movie, numberOfTickets,totalTicket);
	}
	
	public void reset() {
		movie=null;
		//arriva da select fil projection
		numberOfTickets=0;
		totalTicket=0;
		projection=null;
		//questi ultimi 3 arrivano da select projection controller
		
	}
	//se il cliente entra seleziona film e proieione ma alla fine cambia idea e non paga dobbiamo 
	//resettare tutti i paramentri a 0 se no il prissimo cliente che si connette si ritrova 
	//con i dati del precedente
	
}
