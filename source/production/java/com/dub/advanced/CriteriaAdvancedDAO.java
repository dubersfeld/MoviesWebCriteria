package com.dub.advanced;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.transaction.Transactional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Repository;

import com.dub.actors.ActorDAO;
import com.dub.movies.MovieDAO;
import com.dub.entities.Actor;
import com.dub.entities.ActorMovie;
import com.dub.entities.ActorMovieCompositeId;
import com.dub.entities.Director;
import com.dub.entities.Movie;
import com.dub.exceptions.ActorNotFoundException;
import com.dub.exceptions.DuplicateEntryException;
import com.dub.exceptions.MovieNotFoundException;


@Repository
public class CriteriaAdvancedDAO implements AdvancedDAO {

	@Resource
	private ActorDAO actorDAO;
	
	@Resource
	private MovieDAO movieDAO;

	
	@PersistenceContext
	private EntityManager entityManager;
	

	@Override
	public List<Movie> getMoviesByActorName(String firstName, String lastName) 
	{	
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Movie> query = cb.createQuery(Movie.class);
		Root<Movie> movie = query.from(Movie.class);		
		Join<Movie, Actor> actor = movie.join("actor");
		query.where(cb.and(
				cb.equal(actor.get("firstName"), firstName),
				cb.equal(actor.get("lastName"), lastName)));
		
		return entityManager.createQuery(query).getResultList();		
	}
	

	@Override
	public List<Actor> getActorsByMovie(String title, Date releaseDate) 
	{
	    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	    CriteriaQuery<Actor> query = cb.createQuery(Actor.class);
	    Root<Actor> actor = query.from(Actor.class);
	    Join<Actor, Movie> movie = actor.join("movie");
	    query.where(cb.and(
	    		cb.equal(movie.get("title"), title),
				cb.equal(movie.get("releaseDate"), releaseDate)));
	    
	    return entityManager.createQuery(query).getResultList();
	}
	
	@Override
	public List<Movie> getMoviesByDirectorName(String firstName, String lastName) 
	{
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Movie> query = cb.createQuery(Movie.class);	
		Root<Movie> movie = query.from(Movie.class);
		Join<Movie, Director> director = movie.join("director");
		query.where(cb.and(
				cb.equal(director.get("firstName"), firstName),
				cb.equal(director.get("lastName"), lastName)));
		
		return entityManager.createQuery(query).getResultList();			
	}
	
	@Override
	public List<Actor> getActorsByDirectorName(
									String firstName, String lastName) 
	{
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Actor> query = builder.createQuery(Actor.class);
		Subquery<Actor> midquery1 = query.subquery(Actor.class);
		Subquery<Actor> midquery2 = query.subquery(Actor.class);
		Subquery<Actor> subquery = query.subquery(Actor.class);
		
		Root<Actor> actor = query.from(Actor.class);
		Root<ActorMovie> am = midquery1.from(ActorMovie.class);
		Root<Movie> movie = midquery2.from(Movie.class);
		Root<Director> director = subquery.from(Director.class);
		
		subquery.select(director.get("id"))
					.where(builder.and(
							builder.equal(director.get("firstName"), firstName),
							builder.equal(director.get("lastName"), lastName)));
		
		midquery2.select(movie.get("id"))
					.where(builder.in(movie.get("directorId")).value(subquery));
		
		midquery1.select(am.get("id").get("actorId"))
					.where(builder.in(am.get("id").get("movieId")).value(midquery2));
		
		query.where(builder.in(actor.get("id")).value(midquery1));
			
		return entityManager.createQuery(query).getResultList();	
	}
	
	@Override
	public List<Director> getDirectorsByActorName(
									String firstName, String lastName) 
	{
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Director> query = builder.createQuery(Director.class);
		Subquery<Director> midquery1 = query.subquery(Director.class);
		Subquery<Director> midquery2 = query.subquery(Director.class);
		Subquery<Director> subquery = query.subquery(Director.class);
		
		Root<Director> director = query.from(Director.class);
		Root<Movie> movie = midquery1.from(Movie.class);
		Root<ActorMovie> am = midquery2.from(ActorMovie.class);
		Root<Actor> actor = subquery.from(Actor.class);
		
		subquery.select(actor.get("id"))
					.where(builder.and(
							builder.equal(actor.get("firstName"), firstName),
							builder.equal(actor.get("lastName"), lastName)));
		
		midquery2.select(am.get("id").get("movieId"))
		.where(builder.in(am.get("id").get("actorId")).value(subquery));
		
		midquery1.select(movie.get("directorId"))
					.where(builder.in(movie.get("id")).value(midquery2));
		
		query.where(builder.in(director.get("id")).value(midquery1));
			
		return entityManager.createQuery(query).getResultList();		
	}
	
	@Override
	@Transactional
	public void createActorFilm(long actorId, long movieId) {
		ActorMovie am = new ActorMovie();
		ActorMovieCompositeId amci = new ActorMovieCompositeId();
		amci.setActorId(actorId);
		amci.setMovieId(movieId);
		am.setId(amci);
		try {	
			entityManager.persist(am);
			entityManager.flush();
		} catch (PersistenceException e) {
			String ex = ExceptionUtils.getRootCauseMessage(e);
			if (ex.contains("FOREIGN KEY") && ex.contains("actorId")) {
				throw new ActorNotFoundException();
			} else if (ex.contains("FOREIGN KEY") && ex.contains("filmId")) {
				throw new MovieNotFoundException();
			} else if (ex.contains("Duplicate")) {
				throw new DuplicateEntryException();
			}
		}	
	}
	

	@Override
	@Transactional
	public void createActorFilm(
				Actor actor, 
				String title, Date releaseDate) 
	{		
		Actor act = actorDAO.getActor(
					actor.getFirstName(), actor.getLastName());			
		Movie movie = movieDAO.getMovie(title, releaseDate);			
		
		if (movie != null) {
			this.createActorFilm(act.getId(), movie.getId());
		} else {
			throw new MovieNotFoundException();
		}
	}
}