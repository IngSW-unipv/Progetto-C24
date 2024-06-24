package it.unipv.ingsfw.gasCorpCinema.utils;

import java.sql.Time;
import java.util.List;

import it.unipv.ingsfw.gasCorpCinema.model.movie.Movie;
import it.unipv.ingsfw.gasCorpCinema.model.projection.Projection;

public class ProjectionUtils {

	public static boolean canAddProjection(Projection projectionToAdd, List<Projection> allProjections, Movie movie) {
		for (Projection existingProjection : allProjections) {
			if (isOverlapping(existingProjection, projectionToAdd, movie)) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean isOverlapping(Projection p1, Projection p2, Movie movie) {
        Time start1 = p1.getStartTime();
        Time end1 = p1.getEndTime(movie);
        Time start2 = p2.getStartTime();
        Time end2 = p2.getEndTime(movie);

        return (start1.before(end2) && start2.before(end1));
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
