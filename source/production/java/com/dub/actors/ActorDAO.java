package com.dub.actors;

import com.dub.entities.Actor;


import java.util.List;

public interface ActorDAO
{
    List<Actor> getActors();
    
    /** Used to get the number of actor in the table*/
	public long getNumberOfActors();
	
	/** Used to get an actor by key */
	public Actor getActor(long id);
	
	/** Used to get an actor by name */
	public Actor getActor(String firstName, String lastName);
	   	   	   
	/** Using automatically generated id */ 	   
	public void add(Actor actor);
	
	/** Used to delete an existing actor */
	public void delete(long id);
		
	/** Used to update an existing actor */
	public void update(Actor actor);	
}
