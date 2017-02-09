package com.dub.actors;


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

import com.dub.entities.Actor;

import com.dub.exceptions.ActorNotFoundException;
import com.dub.exceptions.DuplicateActorException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Repository;

@Repository
public class CriteriaActorDAO implements ActorDAO
{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	

	@Override
	public List<Actor> getActors() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Actor> query = cb.createQuery(Actor.class);
		query.from(Actor.class);
		return entityManager.createQuery(query).getResultList();		
	}
	

	@Override
	public long getNumberOfActors() 
	{	
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
		Root<Actor> actor = countQuery.from(Actor.class);
		countQuery.select(cb.count(actor));

		return entityManager.createQuery(countQuery)
					.getSingleResult()
					.longValue();		
	}

	@Override
	public Actor getActor(long id) 
	{
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Actor> query = cb.createQuery(Actor.class);
		Root<Actor> actor = query.from(Actor.class);
		query.where(cb.equal(actor.get("id"), id));
		
		try {
			return entityManager.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			throw new ActorNotFoundException();			
		}	
	
	}

	@Override
	public Actor getActor(String firstName, String lastName) 
	{		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Actor> query = cb.createQuery(Actor.class);
		Root<Actor> actor = query.from(Actor.class);
		Predicate pr = cb.and(
		cb.equal(actor.get("firstName"), firstName), 
		cb.equal(actor.get("lastName"), lastName));
		query.where(pr);		
		try {
			return entityManager.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			throw new ActorNotFoundException();			
		}
	}


	@Override
	@Transactional
	public void add(Actor actor) {
		try {
			entityManager.persist(actor);
			entityManager.flush();
		} catch (Exception e ) {
			String ex = ExceptionUtils.getRootCauseMessage(e);
			if (ex.contains("actor_unique")) {
				throw new DuplicateActorException();
			} else {
				throw e;
			}
		}
	}


	@Override
	@Transactional
	public void delete(long id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaDelete<Actor> deleteQuery = 
								cb.createCriteriaDelete(Actor.class); 
		Root<Actor> actor = deleteQuery.from(Actor.class);
		deleteQuery.where(cb.equal(actor.get("id"), id));
		getActor(id);// throws ActorNotFoundException
		entityManager.createQuery(deleteQuery).executeUpdate();
		entityManager.flush();
	}

	@Override
	@Transactional
	public void update(Actor actor) {
		entityManager.merge(actor);
		entityManager.flush();
	}

}
