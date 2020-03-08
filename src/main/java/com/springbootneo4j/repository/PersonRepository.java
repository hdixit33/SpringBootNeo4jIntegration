package com.springbootneo4j.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import com.springbootneo4j.entity.Person;


/**
 * The Interface PersonRepository.
 */
@Repository
public interface PersonRepository extends Neo4jRepository<Person, Long> {
	
	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @return the person
	 */
	public Person findByName(String name);
	
	/**
	 * Find by uuid.
	 *
	 * @param uuid the uuid
	 * @return the person
	 */
	public Person findByUuid(String uuid);
	
}
