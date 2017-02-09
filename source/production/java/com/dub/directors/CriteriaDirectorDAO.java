package com.dub.directors;

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

import com.dub.entities.Director;
import com.dub.exceptions.DirectorNotFoundException;
import com.dub.exceptions.DuplicateDirectorException;

@Repository
public class CriteriaDirectorDAO implements DirectorDAO {
		
	@PersistenceContext
	private EntityManager entityManager;

	
	@Override
	@Transactional
	public void update(Director director) {
		entityManager.merge(director);
		entityManager.flush();		
	}
	
	@Override
	@Transactional
	public void delete(long id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaDelete<Director> deleteQuery = 
				cb.createCriteriaDelete(Director.class);
		Root<Director> director = deleteQuery.from(Director.class);
		deleteQuery.where(cb.equal(director.get("id"), id));
		this.getDirector(id);// throws ActorNotFoundException
		entityManager.createQuery(deleteQuery).executeUpdate();
		entityManager.flush();
	}
	
	@Override
	public List<Director> listAllDirectors() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Director> query = cb.createQuery(Director.class);
		query.from(Director.class);
		return entityManager.createQuery(query).getResultList();
	}

	
	@Override
	public long getNumberOfDirectors() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
		Root<Director> director = countQuery.from(Director.class);
		countQuery.select(cb.count(director));
		
		return entityManager.createQuery(countQuery).getSingleResult().longValue();		
	}
	
	@Override
	public Director getDirector(long id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Director> query = cb.createQuery(Director.class);
		Root<Director> director = query.from(Director.class);
		query.where(cb.equal(director.get("id"), id));		
		try {
			return entityManager.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			throw new DirectorNotFoundException();		
		}	
	}
	
	
	@Override
	public Director getDirector(String firstName, String lastName) 
	{		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Director> query = cb.createQuery(Director.class);
		Root<Director> director = query.from(Director.class);
		Predicate pr = cb.and(
		cb.equal(director.get("firstName"), firstName), 
		cb.equal(director.get("lastName"), lastName));
		query.where(pr);
		
		try {
			return entityManager.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			throw new DirectorNotFoundException();			
		}
	}
	
	@Override
	@Transactional
	public void add(Director director) {
		try {	
			entityManager.persist(director);
			entityManager.flush();
		} catch (Exception e) {
			String ex = ExceptionUtils.getRootCauseMessage(e);
			if (ex.contains("director_unique")) {
				throw new DuplicateDirectorException();
			} else {
				throw e;
			}
		}
	}
	
}

