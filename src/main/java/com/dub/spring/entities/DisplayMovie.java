package com.dub.spring.entities;



public class DisplayMovie extends Movie {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String directorName;
	
	public DisplayMovie() {
	
	}
	
	public DisplayMovie(Movie source) {
		this.id = source.id;
		this.releaseDate = source.releaseDate;
		this.title = source.title;
		this.runningTime = source.runningTime;
	}

	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}	
	
}// class
