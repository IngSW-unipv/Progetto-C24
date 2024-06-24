package it.unipv.ingsfw.gasCorpCinema.view.custom;

import it.unipv.ingsfw.gasCorpCinema.model.cinemaHall.CinemaHall;
import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;
import it.unipv.ingsfw.gasCorpCinema.model.projection.Projection;
import javafx.scene.control.ListCell;

public class CustomListCell extends ListCell<CinemaHallProjectionMovie<CinemaHall, Projection, Movie>>{
	
	@Override
	protected void updateItem(CinemaHallProjectionMovie<CinemaHall, Projection, Movie> item, boolean isEmpty) {
		super.updateItem(item, isEmpty);
        if (isEmpty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            setText(item.toString());
        }
	}
	
}