package com.dub.spring.entities;


public class ActorWithPhoto extends Actor 
{   
	private static final long serialVersionUID = 1L;
	private long photoId;
	private boolean photoAvailable;
	
	
	public ActorWithPhoto() {
	}
	
	public ActorWithPhoto(Actor source) {
		this.birthDate = source.birthDate;
		this.firstName = source.firstName;
		this.lastName = source.lastName;
		this.id = source.id;
		this.photoAvailable = false;
	}

	public long getPhotoId() {
		return photoId;
	}

	public void setPhotoId(long photoId) {
		this.photoId = photoId;
	}
	
	public boolean isPhotoAvailable() {
		return photoAvailable;
	}

	public void setPhotoAvailable(boolean photoAvailable) {
		this.photoAvailable = photoAvailable;
	}
	
}