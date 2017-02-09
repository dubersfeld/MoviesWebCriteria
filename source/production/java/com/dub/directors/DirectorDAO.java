package com.dub.directors;

import java.util.List;

import com.dub.entities.Director;


public interface DirectorDAO {

	   /** Used to update an existing director */
	   public void update(Director director);
	   
	   /** Used to delete an existing director */
	   public void delete(long id);
	   
	   /** Used to list all directors */
	   public List<Director> listAllDirectors();
	   
	   /** Used to get the number of director in the table*/
	   public long getNumberOfDirectors();
	   
	   /** Used to get a director by key */
	   public Director getDirector(long id);
	   	   	   
	   /** Used to get an director by name */
	   public Director getDirector(String firstName, String lastName);
	   	   
	   /** Using automatically generated id */ 
	   public void add(Director director);
	   	
}
