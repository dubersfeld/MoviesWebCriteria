package com.dub.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class ActorMovieCompositeId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long actorId;	
	private long movieId;
	
	@Basic
	@Column(name = "actorId")
	public long getActorId() {
		return actorId;
	}
	public void setActorId(long actorId) {
		this.actorId = actorId;
	}
	
	@Basic
	@Column(name = "filmId")
	public long getMovieId() {
		return movieId;
	}
	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}	
}
