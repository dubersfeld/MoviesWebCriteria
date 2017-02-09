package com.dub.site.movies;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dub.config.annotation.WebController;
import com.dub.movies.MovieServices;
import com.dub.entities.DisplayMovie;
import com.dub.entities.Movie;
import com.dub.exceptions.DirectorNotFoundException;
import com.dub.exceptions.DuplicateMovieException;
import com.dub.exceptions.MovieNotFoundException;


@WebController
public class MovieController {
	
	@Resource
	private MovieServices movieServices;
	
	
	@InitBinder({"movie", "movieKey"})	  
	protected void initBinder(WebDataBinder binder) {
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
		
	@RequestMapping(value = "movieQueries", method = RequestMethod.GET)
	public String movieQueries() {
		return "movies/movieQueries";
	}
	
	@RequestMapping(value = "listAllMovies", method = RequestMethod.GET)
	public String listAllMoviesWithDirName(Map<String, Object> model) {
			
		List<DisplayMovie> list = movieServices.getAllMovies();
		
        model.put("movies", list);
        return "movies/listMovies";
	}
		
	@RequestMapping(value = "numberOfMovies", method = RequestMethod.GET)
	public String numberOfMovies(Map<String, Object> model) {		
		Long number = movieServices.numberOfMovies();
		model.put("number", number);
		return "movies/numberOfMovies";
	}
	
	@RequestMapping(value = "getMovie", method = RequestMethod.GET)
	public ModelAndView getMovie(ModelMap model) {
		model.addAttribute("getMovie", new TitleForm());
		return new ModelAndView("movies/getMovie", model);
	}
	
	@RequestMapping(value = "getMovie", method = RequestMethod.POST)
	public String getMovie(
			@Valid @ModelAttribute("getMovie") TitleForm form,
			BindingResult result, ModelMap model) {
		
		if (result.hasErrors()) {
			return "movies/getMovie";
		} else {
			List<DisplayMovie> list = 
						movieServices.getMovie(form.getTitle());
			if (list.size() > 0) {
				model.put("movies", list);
				return "movies/getMovieResult";
			} else { 
				model.addAttribute("backPage", "getMovie");
				return "movies/getMovieNoResult";			
			}
		}
	}
	
	@RequestMapping(value = "getSingleMovie", method = RequestMethod.GET)
	public ModelAndView getSingleMovie(ModelMap model) {
		model.addAttribute("movieKey", new TitleDateForm());
		return new ModelAndView("movies/getSingleMovie", model);
	}
	
	@RequestMapping(value = "getSingleMovie", method = RequestMethod.POST)
	public String getSingleMovie(
			@Valid @ModelAttribute("movieKey") TitleDateForm form,
			BindingResult result, ModelMap model) {
		
		if (result.hasErrors()) {			
			return "movies/getSingleMovie";
		} else {	
			try {
				DisplayMovie displayMovie = movieServices.getMovie(
								form.getTitle(), form.getReleaseDate());												
				model.put("movie", displayMovie);
				return "movies/getSingleMovieResult";		
			} catch (MovieNotFoundException e) {
				model.addAttribute("backPage", "getSingleMovie");
				return "movies/getMovieNoResult";	
			} 
		}
	}
	
	@RequestMapping(value = "createMovie", method = RequestMethod.GET)
	public ModelAndView createMovie(ModelMap model) {
		model.addAttribute("movie", new MovieForm());		
		return new ModelAndView("movies/createMovie", model);
	}

	@RequestMapping(value = "createMovie", method = RequestMethod.POST)
	public String createMovie(
						@Valid @ModelAttribute("movie") MovieForm form,
						BindingResult result, ModelMap model) {
				
		if (result.hasErrors()) {			
			return "movies/createMovie";
		} else {
			try {
				Movie movie = new Movie();
				movie.setDirectorId(form.getDirectorId());
				movie.setReleaseDate(form.getReleaseDate());
				movie.setRunningTime(form.getRunningTime());
				movie.setTitle(form.getTitle());
				movieServices.createMovie(movie);
				return "movies/createMovieSuccess";		
			} catch (DuplicateMovieException e) {
				result.rejectValue("title", "duplicate", 
						"film already present");
				return "movies/createMovie";
			} catch (DirectorNotFoundException e) {
				result.rejectValue("directorId", "notFound", 
						"director not found");				
				return "movies/createMovie";
			}
						
		}
	}
	
	@RequestMapping(value = "deleteMovie", method = RequestMethod.GET)
	public ModelAndView deleteMovie(ModelMap model) {
		model.addAttribute("movieId", new MovieIdForm());
		return new ModelAndView("movies/deleteMovie", model);
	}
	
	@RequestMapping(value = "deleteMovie", method = RequestMethod.POST)
	public String deleteMovie(
						@Valid @ModelAttribute("movieId") MovieIdForm form,
						BindingResult result, ModelMap model) {
		
		if (result.hasErrors()) {
			return "movies/deleteMovie";
		} else {
			try {
				long id = form.getId();			
				movieServices.deleteMovie(id);			
				return "movies/deleteMovieSuccess";
			} catch (MovieNotFoundException e) {
				result.rejectValue("id", "notFound", "movie not found");				
				return "movies/deleteMovie";								
			} 	
		}
	}
	
	
	@RequestMapping(value = "updateMovie", method = RequestMethod.GET)
	public ModelAndView updateMovie(ModelMap model) {
		model.addAttribute("movieId", new MovieIdForm());
		return new ModelAndView("movies/updateMovie1", model);
	}
	
	@RequestMapping(value = "updateMovie1", method = RequestMethod.POST)
	public String updateMovie(
			@Valid @ModelAttribute("movieId") MovieIdForm form,
			BindingResult result, ModelMap model) {
		
		if (result.hasErrors()) {
			return "movies/updateMovie1";
		} else {
			try { 
				Movie movie = movieServices.getMovie(form.getId());
				model.addAttribute("movie", movie);									
				return "movies/updateMovie2";
			} catch (MovieNotFoundException e) {
				result.rejectValue("id", "notFound", "movie not found");									
				return "movies/updateMovie1";
			} 		
		}		 
	}
	
	@RequestMapping(value = "updateMovie2", method = RequestMethod.POST)
	public String updateMovie2(
			@Valid @ModelAttribute("movie") UpdateMovieForm form,
			BindingResult result, ModelMap model) {
		
		if (result.hasErrors()) {		
			return "movies/updateMovie2";
		} else {
			try {
				Movie movie = new Movie();
				movie.setDirectorId(form.getDirectorId());
				movie.setRunningTime(form.getRunningTime());
				movie.setId(form.getId());
				movie.setReleaseDate(form.getReleaseDate());
				movie.setTitle(form.getTitle());
				movieServices.updateMovie(movie);			
				return "movies/updateMovieSuccess";
			} catch (DirectorNotFoundException e) {
				result.rejectValue("directorId", "notFound", 
								"director not found");
				return "movies/updateMovie2";	
			} 
		}	 
	}	
	
}