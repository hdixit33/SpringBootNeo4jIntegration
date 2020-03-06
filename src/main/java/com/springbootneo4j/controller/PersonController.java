package com.springbootneo4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springbootneo4j.dto.Person;
import com.springbootneo4j.service.PersonService;


@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	PersonService personService;

	
	@PreAuthorize("hasAuthority('observation')")
	@RequestMapping(path = "/create", produces = { MediaType.APPLICATION_JSON_VALUE }, method = RequestMethod.POST)
	public Person createPerson(@RequestBody Person person) {
		try {
			//Person result = personService.createPerson(person);
			return null;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	

}