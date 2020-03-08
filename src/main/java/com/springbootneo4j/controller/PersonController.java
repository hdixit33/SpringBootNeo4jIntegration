package com.springbootneo4j.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springbootneo4j.dto.PersonDto;
import com.springbootneo4j.service.PersonService;
import com.springbootneo4j.service.impl.PersonValidatorService;


/**
 * The Class PersonController.
 */
@RestController
@RequestMapping("/person")
public class PersonController {

	/** The person service. */
	@Autowired
	PersonService personService;
	
	@Autowired
	PersonValidatorService personValidatorService;

	/**
	 * Creates the person.
	 *
	 * @param person the person
	 * @return the person dto
	 */
	@RequestMapping(path = "/create", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public PersonDto createPerson(@RequestBody PersonDto person) {
		
			personValidatorService.validateRequest(person, "create");
			return personService.createPerson(person);

	}
	
	/**
	 * Update person.
	 *
	 * @param person the person
	 * @param uuid the uuid
	 * @return the person dto
	 */
	@RequestMapping(path = "/update/{uuid}", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.PUT)
	public PersonDto updatePerson(@RequestBody PersonDto person,
			@PathVariable("uuid") String uuid) {
		personValidatorService.validateUuid(uuid);
		person.setUuid(uuid);
		personValidatorService.validateRequest(person, "update");
		return personService.updatePerson(person, uuid);
	}
	
	/**
	 * Gets the all persons.
	 *
	 * @return the all persons
	 */
	@RequestMapping(path = "/get", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.GET)
	public List<PersonDto> getAllPersons() {
		
			
			return personService.getAllPersons();

	}
	
	/**
	 * Delete person.
	 *
	 * @param uuid the uuid
	 * @return the person dto
	 */
	@RequestMapping(path = "/delete/{uuid}", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.DELETE)
	public PersonDto deletePerson(@PathVariable("uuid") String uuid) {
		
			personValidatorService.validateUuid(uuid);
			return personService.deletePerson(uuid);

	}
	
	/**
	 * Link person.
	 *
	 * @param uuid1 the uuid 1
	 * @param uuid2 the uuid 2
	 * @return true, if successful
	 */
	@RequestMapping(path = "/linkPersons/{uuid1}/{uuid2}", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.PUT)
	public boolean linkPerson(@PathVariable("uuid1") String uuid1, @PathVariable("uuid2") String uuid2) {
		
			personValidatorService.validateUuid(uuid1);
			personValidatorService.validateUuid(uuid2);
			return personService.linkPersons(uuid1, uuid2);

	}

}