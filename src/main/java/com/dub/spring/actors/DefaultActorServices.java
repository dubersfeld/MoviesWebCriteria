package com.dub.spring.actors;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.dub.spring.site.actors.CreateActorPhoto;
import com.dub.spring.entities.Actor;
import com.dub.spring.entities.ActorPhoto;
import com.dub.spring.exceptions.ActorNotFoundException;
import com.dub.spring.exceptions.PhotoNotFoundException;


@Service
public class DefaultActorServices implements ActorServices {
	
    private static final Logger log = LogManager.getLogger();
    
	@Resource
	private ActorDAO actorDAO;
	
	@Resource
	private PhotoDAO photoDAO;
	
	
	@Override
	public List<Actor> getAllActors() {
		return actorDAO.getActors();
	}

	@Override
	public void deleteActor(long id) {
		actorDAO.getActor(id);// throws	
		actorDAO.delete(id);
	}

	@Override
	public void addActor(Actor actor) {
		actorDAO.add(actor);	
	}

	@Override
	public void updateActor(Actor actor) {
		actorDAO.getActor(actor.getId());// throws	
		actorDAO.update(actor);		
	}

	@Override
	public long numberOfActors() {
		return actorDAO.getNumberOfActors();
	}

	@Override
	public Actor getActor(long id) {
		return actorDAO.getActor(id);
	}

	@Override
	public Actor getActor(String firstName, String lastName) {
		return actorDAO.getActor(firstName, lastName);
	}

	// photo methods
	
	@Override
	public void createActorPhoto(CreateActorPhoto crPhoto) 
			throws FileNotFoundException, IOException 
	{
		ActorPhoto photo = new ActorPhoto();
		File blobIn = new File(crPhoto.getImageFile());
		InputStream blobIs = new FileInputStream(blobIn);
		
		try {
			byte[] data = new byte[blobIs.available()];
			blobIs.read(data);
			photo.setImageData(data);
			photo.setActorId(crPhoto.getActorId());
			photoDAO.create(photo);
		} catch (Exception e) {
			String ex = ExceptionUtils.getRootCauseMessage(e);
			if (ex.contains("FOREIGN KEY")) {
			    log.warn("ActorNotFoundException");
				throw new ActorNotFoundException();
			} else {
				throw e;
			}			
		} finally {
			blobIs.close();
		}		
	}
		
	@Override
	public byte[] getPhotoData(long id) {
		ActorPhoto photo = photoDAO.getPhoto(id);
		if (photo.getImageData() != null) {
			return photo.getImageData();
		} else {
			throw new PhotoNotFoundException();
		}
	}
	
	@Override
	public ActorPhoto getPhoto(long id) {
		return photoDAO.getPhoto(id);// throws
	}

	@Override
	public long getPhotoId(Actor actor) {
		List<Long> list = getAllPhotoIds(actor);
		if (!list.isEmpty()) {
			return list.get(0);
		} else {	
			throw new PhotoNotFoundException();
		}
	}

	@Override
	public List<Long> getAllPhotoIds(Actor actor) {
		return photoDAO.getAllPhotoIds(actor.getId());
	}


	@Override
	public void deletePhoto(long id) {
		photoDAO.getPhoto(id);// throws	
		photoDAO.delete(id);
	}
}
