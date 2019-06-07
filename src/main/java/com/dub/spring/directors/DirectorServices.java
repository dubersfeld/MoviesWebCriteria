package com.dub.spring.directors;

import java.util.List;

import com.dub.spring.entities.Director;

//import org.springframework.security.access.prepost.PreAuthorize;

//@PreAuthorize("hasAuthority('VIEW')")
public interface DirectorServices {

	List<Director> getAllDirectors();
	
	Director getDirector(long id);	
	Director getDirector(String firstName, String lastName);
	
	//@PreAuthorize("hasAuthority('DELETE')")
	void deleteDirector(long id);
	
	//@PreAuthorize("hasAuthority('CREATE')")
	void addDirector(Director director);
	
	//@PreAuthorize("hasAuthority('UPDATE')")
	void updateDirector(Director director);
	
	long numberOfDirectors();
}// interface

