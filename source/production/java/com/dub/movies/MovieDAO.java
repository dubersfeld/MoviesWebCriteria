package com.dub.movies;

import java.util.Date;
import java.util.List;

import com.dub.entities.Movie;

public interface MovieDAO {
	      
    /** Used to create a new movie */
	public void create(Movie movie);
			  
	/** Used to update an existing movie */
	public void update(Movie movie);
	   
	/** Used to delete an existing movie */
	public void delete(long id);
	   	   
	/** Used to list all movies */
	public List<Movie> getAllMovies();
	   
	/** Used to get the number of movies in the table*/
	public long getNumberOfMovies();
	   
	/** Used to get a single movie */
	public Movie getMovie(long id);
	
	/** Used to get a single movie */
	public Movie getMovie(String title, Date releaseDate);
	   	   
	/** Used to get several movies */
	public List<Movie> getMovie(String title);
	   	   
}
