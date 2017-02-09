package com.dub.movies;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dub.directors.DirectorDAO;
import com.dub.entities.Director;
import com.dub.entities.DisplayMovie;
import com.dub.entities.Movie;

@Service
public class DefaultMovieServices implements MovieServices {

	@Resource
	private MovieDAO movieDAO;
	
	@Resource
	private DirectorDAO directorDAO;
	
	
	@Override
	public List<DisplayMovie> getAllMovies() {
		List<Movie> movies = movieDAO.getAllMovies();		
		List<DisplayMovie> list = new ArrayList<>();
		
		for (Movie movie : movies) {		
			Director director = 
					directorDAO.getDirector(movie.getDirectorId());
			String name = 
					director.getFirstName() + " " + director.getLastName();
			DisplayMovie movieDisplay = new DisplayMovie(movie);
			movieDisplay.setDirectorName(name);
			list.add(movieDisplay);			
		}
		
		return list;
	}

	@Override
	public long numberOfMovies() {		
		return movieDAO.getNumberOfMovies();
	}

	@Override
	public List<DisplayMovie> getMovie(String title) {
				
		List<Movie> movies = movieDAO.getMovie(title);
		List<DisplayMovie> list = new ArrayList<>();
		
		for (Movie movie : movies) {		
			Director director = 
					directorDAO.getDirector(movie.getDirectorId());
			String name = 
					director.getFirstName() + " " + director.getLastName();
			DisplayMovie movieDisplay = new DisplayMovie(movie);
			movieDisplay.setDirectorName(name);
			list.add(movieDisplay);			
		}
		
		return list;				
	}

	@Override
	public void createMovie(Movie movie) {
		movieDAO.create(movie);		
	}

	@Override
	public DisplayMovie getMovie(String title, Date releaseDate) {
		Movie movie = movieDAO.getMovie(title, releaseDate);
		if (movie != null) {
			Director director = 
				directorDAO.getDirector(movie.getDirectorId());
			String name = director.getFirstName() + " " + director.getLastName();
			DisplayMovie displayMovie = new DisplayMovie(movie);
			displayMovie.setDirectorName(name);
			return displayMovie;
		} else {
			return null;
		}
	}
	
	@Override
	public void deleteMovie(long id) {
		movieDAO.delete(id);
	}
	
	@Override
	public void updateMovie(Movie movie) {
		movieDAO.update(movie);
	}

	@Override
	public Movie getMovie(long id) {			
		Movie movie = movieDAO.getMovie(id);
		return movie;
	}
	
}
