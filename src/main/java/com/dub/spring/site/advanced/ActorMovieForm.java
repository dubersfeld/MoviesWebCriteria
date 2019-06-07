package com.dub.spring.site.advanced;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/* Command object*/
public class ActorMovieForm {
	
	@Min(value  = 1, message = "{validate.min.actorId}")
	@NotNull(message = "{validate.required.actorId}")
	private long actorId;
	
	@Min(value  = 1, message = "{validate.min.directorId}")
	@NotNull(message = "{validate.required.directorId}")
	private long movieId;
	
	
	public long getActorId() {
		return actorId;
	}
	public void setActorId(long actorId) {
		this.actorId = actorId;
	}
	public long getMovieId() {
		return movieId;
	}
	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}
		
}
