package com.dub.spring.actors;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.dub.spring.entities.Actor;
import com.dub.spring.entities.ActorPhoto;

//import org.springframework.security.access.prepost.PreAuthorize;

import com.dub.spring.site.actors.CreateActorPhoto;

//@PreAuthorize("hasAuthority('VIEW')")
public interface ActorServices {

	List<Actor> getAllActors();
	
	Actor getActor(long id);	
	Actor getActor(String firstName, String lastName);
	
	//@PreAuthorize("hasAuthority('DELETE')")
	void deleteActor(long id);
	
	//@PreAuthorize("hasAuthority('CREATE')")
	void addActor(Actor actor);
	
	//@PreAuthorize("hasAuthority('UPDATE')")
	void updateActor(Actor actor);
	
	long numberOfActors();
	
	//@PreAuthorize("hasAuthority('CREATE')")
	void createActorPhoto(CreateActorPhoto photo) 
			throws FileNotFoundException, IOException;

	// here id is the key of actorImages table
	byte[] getPhotoData(long id);
	
	ActorPhoto getPhoto(long id);
		
	long getPhotoId(Actor actor);
	
	List<Long> getAllPhotoIds(Actor actor);
	
	//@PreAuthorize("hasAuthority('DELETE')")
	void deletePhoto(long id);
			
}
