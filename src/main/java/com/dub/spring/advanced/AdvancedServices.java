package com.dub.spring.advanced;

import java.util.Date;
import java.util.List;

//import org.springframework.security.access.prepost.PreAuthorize;

import com.dub.spring.entities.Actor;
import com.dub.spring.entities.Director;
import com.dub.spring.entities.DisplayMovie;


//@PreAuthorize("hasAuthority('VIEW')")
public interface AdvancedServices {
	
	public List<DisplayMovie> getMoviesWithActor(String firstName, String lastName);

	public List<Actor> getActorsInMovie(String title, Date releaseDate);

	public List<DisplayMovie> getMoviesByDirector(String firstName, String lastName);

	public List<Actor> getActorsByDirector(String firstName, String lastName);

	public List<Director> getDirectorsByActor(String firstName, String lastName);

	//@PreAuthorize("hasAuthority('CREATE')")
	public void createActorFilm(Long actorId, Long movieId);

	//@PreAuthorize("hasAuthority('CREATE')")
	public void createActorFilmSpecial(
			Actor actor, 
			String title, Date releaseDate);		 
}
