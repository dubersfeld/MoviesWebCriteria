package com.dub.movies;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Repository;

import com.dub.entities.Movie;
import com.dub.exceptions.DirectorNotFoundException;
import com.dub.exceptions.DuplicateMovieException;
import com.dub.exceptions.MovieNotFoundException;


@Repository
public class CriteriaMovieDAO implements MovieDAO {
	

	@PersistenceContext
	private EntityManager entityManager;
		
	
	@Override
	public List<Movie> getAllMovies() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Movie> query = cb.createQuery(Movie.class);
		query.from(Movie.class);
		return entityManager.createQuery(query).getResultList();		
	}
	
	
	@Override
	@Transactional
	public void create(Movie movie) {
		try {	
			entityManager.persist(movie);
			entityManager.flush();
		} catch (Exception e) {
			String ex = ExceptionUtils.getRootCauseMessage(e);
			if (ex.contains("movie_unique")) {
				throw new DuplicateMovieException();				
			} else if (ex.contains("FOREIGN KEY")) {
				throw new DirectorNotFoundException();
			} else {
				throw e;
			}
		}
	}

	@Override
	@Transactional
	public void update(Movie movie) {	
		entityManager.merge(movie);	
		entityManager.flush();
	}
	
	@Override
	@Transactional
	public void delete(long id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaDelete<Movie> deleteQuery = 
								cb.createCriteriaDelete(Movie.class); 
		Root<Movie> movie = deleteQuery.from(Movie.class);
		deleteQuery.where(cb.equal(movie.get("id"), id));
		this.getMovie(id);// throws MovieNotFoundException
		entityManager.createQuery(deleteQuery).executeUpdate();
		entityManager.flush();
	}
	
	
	@Override
	public long getNumberOfMovies() 
	{	
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
		Root<Movie> movie = countQuery.from(Movie.class);
		countQuery.select(cb.count(movie));

		return entityManager.createQuery(countQuery)
					.getSingleResult()
					.longValue();		
	}
	
	
	@Override
	public Movie getMovie(long id) 
	{
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Movie> query = cb.createQuery(Movie.class);
		Root<Movie> movie = query.from(Movie.class);
		query.where(cb.equal(movie.get("id"), id));	
		try {
			return entityManager.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			throw new MovieNotFoundException();			
		}	
	}
	
	
	@Override
	public Movie getMovie(String title, Date releaseDate) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Movie> query = cb.createQuery(Movie.class);
		Root<Movie> root = query.from(Movie.class);		
		Predicate pr = cb.and(
						cb.equal(root.get("title"), title), 
						cb.equal(root.get("releaseDate"), releaseDate));
		query.where(pr);
		
		try {
            return entityManager.createQuery(query).getSingleResult();
		} catch (NoResultException e) {				
			throw new MovieNotFoundException();
		}		
	}
	
	@Override
	public List<Movie> getMovie(String title) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Movie> query = cb.createQuery(Movie.class);
		Root<Movie> root = query.from(Movie.class);		
		Predicate pr = cb.equal(root.get("title"), title);
		query.where(pr);
		
		return entityManager.createQuery(query).getResultList();		
	}
	
}
