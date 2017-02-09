package com.dub.actors;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.dub.entities.Actor;
import com.dub.entities.ActorPhoto;
import com.dub.site.actors.CreateActorPhoto;

public interface ActorServices {
	
	List<Actor> getAllActors();
	
	Actor getActor(long id);	
	Actor getActor(String firstName, String lastName);
	void deleteActor(long id);
	void addActor(Actor actor);
	void updateActor(Actor actor);
	long numberOfActors();
	
	void createActorPhoto(CreateActorPhoto photo) 
			throws FileNotFoundException, IOException;

	// here id is the key of actorImages table
	byte[] getPhotoData(long id);
	
	ActorPhoto getPhoto(long id);
		
	long getPhotoId(Actor actor);
	
	List<Long> getAllPhotoIds(Actor actor);
		
	void deletePhoto(long id);

}
