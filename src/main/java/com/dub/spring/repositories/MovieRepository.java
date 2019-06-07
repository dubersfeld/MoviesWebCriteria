package com.dub.spring.repositories;


import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dub.spring.entities.Movie;



public interface MovieRepository extends CrudRepository<Movie, Long>
{
	List<Movie> findByTitle(String title);
	List<Movie> findByTitleAndReleaseDate(String title, Date releaseDate);
}
