package com.dub.actors;

import com.dub.entities.ActorPhoto;
import com.dub.site.actors.CreateActorPhoto;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface PhotoDAO
{	   
	/** Using automatically generated id */ 
	void create(ActorPhoto photo);

	byte[] getPhotoData(long photoId);
	   
	ActorPhoto getPhoto(long photoId);
	    	   
	List<Long> getAllPhotoIds(long actorId);
	   
	void delete(long photoId);	  
}
