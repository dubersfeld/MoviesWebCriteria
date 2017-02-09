package com.dub.actors;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import com.dub.entities.ActorPhoto;
import com.dub.exceptions.PhotoNotFoundException;

import org.springframework.stereotype.Repository;

@Repository
public class CriteriaPhotoDAO implements PhotoDAO
{	
	@PersistenceContext
	private EntityManager entityManager;
	
		
	@Override
	@Transactional
	public void create(ActorPhoto photo) 
	{
		entityManager.persist(photo);
		entityManager.flush();
	}


	@Override
	public ActorPhoto getPhoto(long photoId) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ActorPhoto> query = 
									cb.createQuery(ActorPhoto.class);
		Root<ActorPhoto> actorPhoto = query.from(ActorPhoto.class);
		query.where(cb.equal(actorPhoto.get("id"), photoId));
		
		try {
			return entityManager.createQuery(query)
									.getSingleResult();
		} catch(NoResultException e) {
			throw new PhotoNotFoundException();
		}	
	}
	
	@Override
	public byte[] getPhotoData(long photoId) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ActorPhoto> query = 
									cb.createQuery(ActorPhoto.class);
		Root<ActorPhoto> actorPhoto = query.from(ActorPhoto.class);
		query.where(cb.equal(actorPhoto.get("id"), photoId));
		
		try {
			ActorPhoto photo = entityManager.createQuery(query)
									.getSingleResult();			
			byte[] data = photo.getImageData();
			return data;
		} catch(NoResultException e) {
			throw new PhotoNotFoundException();
		}	
	}

	@Override
	public List<Long> getAllPhotoIds(long actorId) 
	{
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = cb.createQuery(Long.class);
		Root<ActorPhoto> photo = query.from(ActorPhoto.class);
		query.select(photo.get("id"));
		query.where(cb.equal(photo.get("actorId"), actorId));
		
		return entityManager.createQuery(query).getResultList();	
	}

	@Override
	@Transactional
	public void delete(long photoId) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaDelete<ActorPhoto> deleteQuery = 
								cb.createCriteriaDelete(ActorPhoto.class); 
		Root<ActorPhoto> photo = deleteQuery.from(ActorPhoto.class);
		deleteQuery.where(cb.equal(photo.get("id"), photoId));
		entityManager.createQuery(deleteQuery).executeUpdate();
		entityManager.flush();
	}
		
}
