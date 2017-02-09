package com.dub.actors;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import com.dub.entities.Actor;
import com.dub.entities.ActorPhoto;
import com.dub.exceptions.ActorNotFoundException;
import com.dub.site.actors.CreateActorPhoto;

@Service
public class DefaultActorServices implements ActorServices {
	
	@Resource
	private ActorDAO actorDAO;
	
	@Resource
	private PhotoDAO photoDAO;
	
	@Override
	public List<Actor> getAllActors() {
		List<Actor> actors = actorDAO.getActors();
		return actors;
	}

	@Override
	public Actor getActor(long id) {	
		return actorDAO.getActor(id);
	}

	@Override
	public Actor getActor(String firstName, String lastName) {
		return actorDAO.getActor(firstName, lastName);
	}

	@Override
	public void deleteActor(long id) {
			actorDAO.delete(id);
	}

	@Override
	public void addActor(Actor actor) {
		actorDAO.add(actor);
	}

	@Override
	public void updateActor(Actor actor) {
		actorDAO.update(actor);
		
	}

	@Override
	public long numberOfActors() {
		
		return actorDAO.getNumberOfActors();
	}

	@Override
	public byte[] getPhotoData(long id) {
		return photoDAO.getPhotoData(id);
	}


	@Override
	public long getPhotoId(Actor actor) {
		List<Long> list = getAllPhotoIds(actor);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return 0;
		}
	}
	
	@Override
	public List<Long> getAllPhotoIds(Actor actor) {
		return photoDAO.getAllPhotoIds(actor.getId());
	}
	
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
				throw new ActorNotFoundException();
			} else {
				throw e;
			}			
		} finally {
			blobIs.close();
		}		
	}
	
	@Override
	public void deletePhoto(long id) {
		photoDAO.delete(id);
	}

	@Override
	public ActorPhoto getPhoto(long id) {		
		return photoDAO.getPhoto(id);
	}

}