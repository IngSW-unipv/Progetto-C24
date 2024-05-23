package it.unipv.ingsfw.gasCorpCinema.model.cinemaHall;

public class CinemaHall {
	
	private int idHall;
	private int capacity;
	
	public CinemaHall(int idHall,int capacity) {
		this.idHall = idHall;
		this.capacity = capacity;
	}

	public int getIdHall() {
		return idHall;
	}

	public int getCapacity() {
		return capacity;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ID Hall:" + idHall + "|" + "Capacity:" + capacity;
	}
	
	
}
