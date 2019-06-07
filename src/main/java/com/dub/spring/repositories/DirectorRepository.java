package com.dub.spring.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dub.spring.entities.Director;



public interface DirectorRepository extends CrudRepository<Director, Long>
{
	List<Director> findByFirstNameAndLastName(String firstName, String lastName);
}
