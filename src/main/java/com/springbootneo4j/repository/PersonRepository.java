package com.springbootneo4j.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import com.springbootneo4j.dto.Person;
import com.springbootneo4j.entity.PersonEntity;

@Repository
public interface PersonRepository extends Neo4jRepository<PersonEntity, Long> {
	Person findByName(String name);
}
