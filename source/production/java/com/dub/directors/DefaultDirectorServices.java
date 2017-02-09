package com.dub.directors;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dub.entities.Director;

@Service
public class DefaultDirectorServices implements DirectorServices {
	
	@Resource DirectorDAO directorDAO;

	@Override
	public List<Director> getAllDirectors() {
		return directorDAO.listAllDirectors();
	}

	@Override
	public Director getDirector(long id) {
		return directorDAO.getDirector(id);
	}

	@Override
	public Director getDirector(String firstName, String lastName) {
		return directorDAO.getDirector(firstName, lastName);
	}

	@Override
	public void deleteDirector(long id) {
		directorDAO.delete(id);	
	}

	@Override
	public void addDirector(Director director) {
		directorDAO.add(director);
	}

	@Override
	public void updateDirector(Director director) {
		directorDAO.update(director);
		
	}

	@Override
	public long numberOfDirectors() {
		return directorDAO.getNumberOfDirectors();
	}
	
}

