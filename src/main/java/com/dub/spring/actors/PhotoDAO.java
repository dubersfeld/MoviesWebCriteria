package com.dub.spring.actors;

import java.util.List;

import com.dub.spring.entities.ActorPhoto;


public interface PhotoDAO 
{	       
	/** Using automatically generated id */  
	void create(ActorPhoto photo);
	
	byte[] getPhotoData(long photoId);
	     
	ActorPhoto getPhoto(long id);
	   
	List<Long> getAllPhotoIds(long actorId);
	
	void delete(long photoId);   	   
}