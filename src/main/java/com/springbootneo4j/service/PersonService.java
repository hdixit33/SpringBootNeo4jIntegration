package com.springbootneo4j.service;

import java.util.List;

import com.springbootneo4j.dto.PersonDto;


/**
 * The Interface PersonService.
 */
public interface PersonService {
	
	/**
	 * Creates the person.
	 *
	 * @param person the person
	 * @return the person dto
	 */
	public PersonDto createPerson(PersonDto person);
	
	/**
	 * Update person.
	 *
	 * @param person the person
	 * @param uuid the uuid
	 * @return the person dto
	 */
	public PersonDto updatePerson(PersonDto person, String uuid);
	
	/**
	 * Gets the all persons.
	 *
	 * @return the all persons
	 */
	public List<PersonDto> getAllPersons();
	
	/**
	 * Delete person.
	 *
	 * @param uuid the uuid
	 * @return the person dto
	 */
	public PersonDto deletePerson(String uuid);
	
	/**
	 * Link persons.
	 *
	 * @param uuid the uuid
	 * @param uuid2 the uuid 2
	 * @return true, if successful
	 */
	public boolean linkPersons(String uuid, String uuid2);

}
