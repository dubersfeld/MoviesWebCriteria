package com.dub.spring.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "movie")
public class Movie implements Serializable {
	
	private static final long serialVersionUID = 1L;

	protected long id;
	protected String title;		
	protected Date releaseDate;		   
    protected long runningTime;	
    protected long directorId;
	 
	Set<Actor> actor;
    
	Director director;
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movieId")
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
 
	@Column(name = "title")
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "releaseDate")
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
		  
    @Column(name = "runningTime")
	public long getRunningTime() {
		return runningTime;
	}
	public void setRunningTime(long runningTime) {
		this.runningTime = runningTime;
	}
	
    @ManyToMany(mappedBy = "movie", fetch = FetchType.LAZY)   
	public Set<Actor> getActor() {
		return actor;
	}
	public void setActor(Set<Actor> actor) {
		this.actor = actor;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "directorId", insertable = false, updatable = false)   
	public Director getDirector() {
		return director;
	}
	public void setDirector(Director director) {
		this.director = director;
	}

    @Column(name = "directorId")
	public long getDirectorId() {
		return directorId;
	}
	public void setDirectorId(long directorId) {
		this.directorId = directorId;
	}
	
}// class
