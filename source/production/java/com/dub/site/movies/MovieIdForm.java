package com.dub.site.movies;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;



public class MovieIdForm {

	@Min(value  = 1, message = "{validate.min.movieId}")
	@NotNull(message = "{validate.required.movieId}")
	long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}