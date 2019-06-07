package com.dub.spring.site.actors;


/* Command object */
public class CreateActorPhoto {   
	private String imageFile;
	private long actorId;
	
	public String getImageFile() {
		return imageFile;
	}
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}
	
	public long getActorId() {
		return actorId;
	}
	public void setActorId(long actorId) {
		this.actorId = actorId;
	}
	
}// class