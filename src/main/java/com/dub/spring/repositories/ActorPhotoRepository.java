package com.dub.spring.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dub.spring.entities.ActorPhoto;



public interface ActorPhotoRepository extends CrudRepository<ActorPhoto, Long>
{
	List<ActorPhoto> findByActorId(long actorId);
}
