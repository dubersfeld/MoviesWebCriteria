package com.dub.site.actors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


import org.springframework.web.multipart.MultipartFile;


/* Command object */
public class ActorPhotoMultiForm {
	
	@Min(value  = 1, message = "{validate.min.actorId}")
	@NotNull(message = "{validate.required.actorId}")
	private long actorId;
	
	private MultipartFile uploadedFile;

	public long getActorId() {
		return actorId;
	}

	public void setActorId(long actorId) {
		this.actorId = actorId;
	}

	public MultipartFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(MultipartFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

}
