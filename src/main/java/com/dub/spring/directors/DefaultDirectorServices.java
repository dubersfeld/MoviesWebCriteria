package com.dub.spring.directors;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dub.spring.entities.Director;

@Service
public class DefaultDirectorServices implements DirectorServices {
	
	@Resource
	private DirectorDAO directorDAO;
	
	@Override
	public List<Director> getAllDirectors() {
		List<Director> directors = directorDAO.listAllDirectors();
		return directors;
	}

	@Override
	public void deleteDirector(long id) {
		directorDAO.getDirector(id);	
		directorDAO.delete(id);		
	}

	@Override
	public void addDirector(Director director) {
		directorDAO.add(director);	
	}

	@Override
	public void updateDirector(Director director) {
		directorDAO.getDirector(director.getId());	
		directorDAO.update(director);		
	}

	@Override
	public long numberOfDirectors() {
		return directorDAO.getNumberOfDirectors();
	}

	@Override
	public Director getDirector(long id) {
		return directorDAO.getDirector(id);
	}

	@Override
	public Director getDirector(String firstName, String lastName) {
		return directorDAO.getDirector(firstName, lastName);
	}

}// class
