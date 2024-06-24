package it.unipv.ingsfw.gasCorpCinema.utils;

import java.sql.Time;
import java.util.List;

import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;
import it.unipv.ingsfw.gasCorpCinema.model.projection.Projection;

public class ProjectionUtils {

	public static boolean canAddProjection(Projection projectionToAdd, List<Projection> allProjections, Movie movie) {
		for (Projection existingProjection : allProjections) {
			if (isOverlapping(existingProjection, projectionToAdd, movie)) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean isOverlapping(Projection existingProjection, Projection projectionToAdd, Movie movie) {
        Time existingProjectionEndTime = existingProjection.getEndTime(movie);
        Time toAddProjectionStartTime = projectionToAdd.getTime();
        
        return (toAddProjectionStartTime.before(existingProjectionEndTime));
    }
	
	public static boolean priceIsAdeguate(Projection projection, Movie movie) {
		if(movie.getRating().equals("Top") && projection.getPrice() > 4.90) {
			return true;
		}else if(movie.getRating().equals("Nor") && projection.getPrice() <= 4.90){
			return true;
		}
		
		return false;
	}
}
