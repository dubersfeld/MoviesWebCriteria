package com.dub.spring.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="actorFilm")
public class ActorMovie implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
	private ActorMovieCompositeId id; 
	
	@EmbeddedId
	public ActorMovieCompositeId getId() {
		return id;
	}

	public void setId(ActorMovieCompositeId id) {
		this.id = id;
	}
}
