package com.dub.movies;

import java.util.Date;
import java.util.List;

import com.dub.entities.DisplayMovie;
import com.dub.entities.Movie;

public interface MovieServices {

	List<DisplayMovie> getAllMovies();
	
	Movie getMovie(long id);
	
	// should be unique
	DisplayMovie getMovie(String title, Date releaseDate);
		
	// can give several matches
	List<DisplayMovie> getMovie(String title);
	
	void deleteMovie(long id);
	
	void createMovie(Movie movie);	
	void updateMovie(Movie movie);	
	long numberOfMovies();
}
