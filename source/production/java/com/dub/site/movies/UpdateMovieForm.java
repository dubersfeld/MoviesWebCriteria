package com.dub.site.movies;


import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


public class UpdateMovieForm {
	
	private long id;
	private String title;	
	protected Date releaseDate;
	
	@NotNull(message = "{validate.directorId.required}")
	@Min(value  = 1, message = "{validate.min.directorId}")
	protected long directorId;
	
	@NotNull(message = "{validate.runningTime.required}")
	protected long runningTime;
	
	
 
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	

	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
		  
	
	public long getDirectorId() {
		return directorId;
	}
	public void setDirectorId(long directorId) {
		this.directorId = directorId;
	}
	
	
	public long getRunningTime() {
		return runningTime;
	}
	public void setRunningTime(long runningTime) {
		this.runningTime = runningTime;
	}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
}
