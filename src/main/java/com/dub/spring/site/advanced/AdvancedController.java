package com.dub.spring.site.advanced;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dub.spring.advanced.AdvancedServices;
import com.dub.spring.entities.Actor;
import com.dub.spring.entities.Director;
import com.dub.spring.entities.DisplayMovie;
import com.dub.spring.site.NameForm;
import com.dub.spring.exceptions.ActorNotFoundException;
import com.dub.spring.exceptions.DuplicateActorException;
import com.dub.spring.exceptions.DuplicateEntryException;
import com.dub.spring.exceptions.MovieNotFoundException;
import com.dub.spring.site.movies.TitleDateForm;
import com.dub.spring.site.advanced.ActorMovieTransForm;



@Controller
public class AdvancedController {
	
	@Resource
	private AdvancedServices advancedServices;
		

	@InitBinder({"movieKey", "actorMovieTrans"})	  
	protected void initMovieActorsBinder(WebDataBinder binder) {
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");		
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	
	@RequestMapping(value = "/advancedQueries", method = RequestMethod.GET)
	public String advancedQueries() {
		return "advanced/advancedQueries";
	}
	
	@RequestMapping(value = "/actorMovies", method = RequestMethod.GET)
	public ModelAndView getActorMovies(ModelMap model) {
		model.addAttribute("actorName", new NameForm());
		return new ModelAndView("advanced/actorMovies", model);
	}
	
	@RequestMapping(value = "/actorMovies", method = RequestMethod.POST)
	public String getActorMovies(
				@Valid @ModelAttribute("actorName") NameForm form,
				BindingResult result, ModelMap model) {
			
		if (result.hasErrors()) {
			return "advanced/actorMovies";
		} else {		
			String firstName = form.getFirstName();
			String lastName = form.getLastName();
			List<DisplayMovie> movies = 
						advancedServices.getMoviesWithActor(firstName, lastName);
			model.put("firstName", firstName); 
			model.put("lastName", lastName); 
			if (movies.size() > 0) {					
				model.put("movies", movies);
				return "advanced/actorMoviesResult";					
			} else {
				model.put("backPage", "actorMovies");
				return "advanced/actorMoviesNoResult";	
			}
		}
	}
	
	@RequestMapping(value = "movieActors", method = RequestMethod.GET)
	public ModelAndView getMovieActors(ModelMap model) {
		model.addAttribute("movieKey", new TitleDateForm());
		return new ModelAndView("advanced/movieActors", model);
	}
	
	@RequestMapping(value = "movieActors", method = RequestMethod.POST)
	public String getMovieActors(
			@Valid @ModelAttribute("movieKey") TitleDateForm form,
			BindingResult result, ModelMap model) {
			
		if (result.hasErrors()) {
			return "advanced/movieActors";
		} else {
			String title = form.getTitle();
			Date releaseDate = form.getReleaseDate();
			List<Actor> actors = 
					advancedServices.getActorsInMovie(title, releaseDate);
			model.put("title", title);
			model.put("releaseDate", releaseDate);
			if (actors.size() > 0) {
				model.put("actors", actors);
				return "advanced/movieActorsResult";			
			} else {
				model.put("backPage", "movieActors");
				return "advanced/movieActorsNoResult";		
			}
		}
	}
	
	@RequestMapping(value = "directorMovies", method = RequestMethod.GET)
	public ModelAndView getDirectorMovies(ModelMap model) {
		model.addAttribute("directorName", new NameForm());
		return new ModelAndView("advanced/directorMovies", model);
	}
	
	@RequestMapping(value = "directorMovies", method = RequestMethod.POST)
	public String getDirectorMovies(
				@Valid @ModelAttribute("directorName") NameForm form,
				BindingResult result, ModelMap model) {
			
		if (result.hasErrors()) {
			return "advanced/directorMovies";
		} else {		
			String firstName = form.getFirstName();
			String lastName = form.getLastName();
			List<DisplayMovie> movies = 
						advancedServices.getMoviesByDirector(firstName, lastName);
			model.put("firstName", firstName); 
			model.put("lastName", lastName); 
			if (movies.size() > 0) {					
				model.put("movies", movies);
				return "advanced/directorMoviesResult";					
			} else {
				model.put("backPage", "directorMovies");
				return "advanced/directorMoviesNoResult";	
			}
		}
	}
	
	@RequestMapping(value = "directorActors", method = RequestMethod.GET)
	public ModelAndView getDirectorActors(ModelMap model) {
		model.addAttribute("directorName", new NameForm());
		return new ModelAndView("advanced/directorActors", model);
	}
	
	@RequestMapping(value = "directorActors", method = RequestMethod.POST)
	public String getDirectorActors(
				@Valid @ModelAttribute("directorName") NameForm form,
				BindingResult result, ModelMap model) {
			
		if (result.hasErrors()) {
			return "advanced/directorActors";
		} else {		
			String firstName = form.getFirstName();
			String lastName = form.getLastName();
			List<Actor> actors = 
						advancedServices.getActorsByDirector(firstName, lastName);
			model.put("firstName", firstName); 
			model.put("lastName", lastName); 
			if (actors.size() > 0) {					
				model.put("actors", actors);
				return "advanced/directorActorsResult";					
			} else {
				model.put("backPage", "directorActors");
				return "advanced/directorActorsNoResult";	
			}
		}
	}
	
	@RequestMapping(value = "actorDirectors", method = RequestMethod.GET)
	public ModelAndView getActorDirectors(ModelMap model) {
		model.addAttribute("actorName", new NameForm());
		return new ModelAndView("advanced/actorDirectors", model);
	}
	
	@RequestMapping(value = "actorDirectors", method = RequestMethod.POST)
	public String getActorDirectors(
				@Valid @ModelAttribute("actorName") NameForm form,
				BindingResult result, ModelMap model) {
			
		if (result.hasErrors()) {
			return "advanced/actorDirectors";
		} else {		
			String firstName = form.getFirstName();
			String lastName = form.getLastName();
			List<Director> directors = 
						advancedServices.getDirectorsByActor(firstName, lastName);
			model.put("firstName", firstName); 
			model.put("lastName", lastName); 
			if (directors.size() > 0) {					
				model.put("directors", directors);				
				return "advanced/actorDirectorsResult";					
			} else {
				model.put("backPage", "actorDirectors");
				return "advanced/actorDirectorsNoResult";	
			}
		}
	}
	
	@RequestMapping(value = "createActorMovie", method = RequestMethod.GET)
	public ModelAndView createActorMovie(ModelMap model) {
		model.addAttribute("actorMovie", new ActorMovieForm());
		return new ModelAndView("advanced/createActorMovie", model);
	}
	
	
	@RequestMapping(value = "createActorMovie", method = RequestMethod.POST)
	public String createActorMovie(
						@Valid @ModelAttribute("actorMovie") ActorMovieForm form,
						BindingResult result, ModelMap model) {
		
		if (result.hasErrors()) {
			return "advanced/createActorMovie";
		} else {		
			try {
				advancedServices.createActorFilm(
										form.getActorId(), 
										form.getMovieId());
				return "advanced/createActorMovieSuccess";			
			} catch (DuplicateEntryException e) {
				result.rejectValue("movieId", "duplicate", "entry already present");
				return "advanced/createActorMovie";
			} catch (ActorNotFoundException e) {
				result.rejectValue("actorId", "notFound", "actor not found");
				return "advanced/createActorMovie";
			} catch (MovieNotFoundException e) {
				result.rejectValue("movieId", "notFound", "film not found");
				return "advanced/createActorMovie";
			} 
		}		
	}
	
	@RequestMapping(value = "createActorMovieTrans", method = RequestMethod.GET)
	public ModelAndView createActorMovieTrans(ModelMap model) {
		model.addAttribute("actorMovieTrans", new ActorMovieTransForm());
		return new ModelAndView("advanced/createActorMovieTrans", model);
	}
	
	@RequestMapping(value = "createActorMovieTrans", method = RequestMethod.POST)
	public String createActorMovieTrans(
						@Valid @ModelAttribute("actorMovieTrans") ActorMovieTransForm form,
						BindingResult result, ModelMap model) 
	{
		if (result.hasErrors()) {
			return "advanced/createActorMovieTrans";
		} else {	
			Actor actor = new Actor();
			actor.setFirstName(form.getFirstName());
			actor.setLastName(form.getLastName());
			actor.setBirthDate(form.getBirthDate());		
			try {
				advancedServices.createActorFilmSpecial(
											actor, 
											form.getTitle(), 
											form.getReleaseDate());
				return "advanced/createActorMovieTransSuccess";
			} catch (MovieNotFoundException e) {
				result.rejectValue("title", "notFound", "movie not found");						
				return "advanced/createActorMovieTrans";			
			} catch (DuplicateActorException e) {
				result.rejectValue("firstName", "duplicate", "actor already present");						
				return "advanced/createActorMovieTrans";
			} 
		}
	}
	
}