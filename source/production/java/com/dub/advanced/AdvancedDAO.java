package com.dub.advanced;

import java.util.Date;
import java.util.List;

import com.dub.entities.Actor;
import com.dub.entities.Director;
import com.dub.entities.Movie;


public interface AdvancedDAO {
	
	public List<Movie> getMoviesByActorName(String firstName, String lastName);
	
	public List<Actor> getActorsByMovie(String title, Date releaseDate);
	
	public List<Movie> getMoviesByDirectorName(String firstName, String lastName);
	
	public List<Actor> getActorsByDirectorName(String firstName, String lastName);

	public List<Director> getDirectorsByActorName(String firstName, String lastName);

	public void createActorFilm(long actorId, long movieId);
	
	public void createActorFilm(Actor actor, String title, Date releaseDate);

}
