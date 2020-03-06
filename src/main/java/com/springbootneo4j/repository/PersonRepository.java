package com.springbootneo4j.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import com.springboot.neo4j.entity.PersonEntity;
import com.springbootneo4j.dto.Person;

@Repository
public interface PersonRepository extends Neo4jRepository<PersonEntity, Long> {
	Person findByName(String name);
}
