package com.dub.spring.entities;


/* Command object*/
public class CreateActorMovie {
	
	private long actorId;
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
