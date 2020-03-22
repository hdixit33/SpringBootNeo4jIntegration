package com.springbootneo4j.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.springbootneo4j.dto.PersonDto;
import com.springbootneo4j.entity.Person;
import com.springbootneo4j.repository.PersonRepository;
import com.springbootneo4j.service.PersonService;



/**
 * The Class PersonServiceImpl.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class PersonServiceImpl implements PersonService {
	
	/** The person repository. */
	@Autowired
	PersonRepository personRepository;
	
	/* (non-Javadoc)
	 * @see com.springbootneo4j.service.PersonService#createPerson(com.springbootneo4j.dto.PersonDto)
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public PersonDto createPerson(PersonDto person) {
		Person personEntity = new Person();
		personEntity.setUuid(UUID.randomUUID().toString());
		personEntity.setName(person.getName());
		personEntity.setEmail(person.getEmail());
		personEntity.setPhoneNumber(person.getPhoneNumber());
		personEntity.setCreatedOn(new Date());
		personEntity.setModifiedOn(new Date());
		person.setUuid(personEntity.getUuid());
		personRepository.save(personEntity);
		return person;
	}
	
	/* (non-Javadoc)
	 * @see com.springbootneo4j.service.PersonService#updatePerson(com.springbootneo4j.dto.PersonDto, java.lang.String)
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public PersonDto updatePerson(PersonDto person, String uuid) {

		Person personEntity = personRepository.findByUuid(uuid);
		personEntity.setName(person.getName());
		personEntity.setEmail(person.getEmail());
		personEntity.setPhoneNumber(person.getPhoneNumber());
		personEntity.setModifiedOn(new Date());
		person.setUuid(personEntity.getUuid());
		personRepository.save(personEntity);
		return person;
	
	}
	
	/* (non-Javadoc)
	 * @see com.springbootneo4j.service.PersonService#getAllPersons()
	 */
	public List<PersonDto> getAllPersons() {
		List<Person> list = (List<Person>) personRepository.findAll();
		List<PersonDto> result = new ArrayList<> ();
		for (Person person : list) {
			PersonDto personDto = new PersonDto();
			personDto.setEmail(person.getEmail());
			personDto.setName(person.getName());
			personDto.setPhoneNumber(person.getPhoneNumber());
			personDto.setUuid(person.getUuid());
			result.add(personDto);
			
		}
		return result;
		
	}
	
	/* (non-Javadoc)
	 * @see com.springbootneo4j.service.PersonService#deletePerson(java.lang.String)
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public PersonDto deletePerson(String uuid) {
		Person personEntity = personRepository.findByUuid(uuid);
		PersonDto personDto = new PersonDto();
		personDto.setEmail(personEntity.getEmail());
		personDto.setName(personEntity.getName());
		personDto.setPhoneNumber(personEntity.getPhoneNumber());
		personDto.setUuid(personEntity.getUuid());
		personRepository.delete(personEntity);
		return personDto;
	}
	
	/* (non-Javadoc)
	 * @see com.springbootneo4j.service.PersonService#linkPersons(java.lang.String, java.lang.String)
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean linkPersons(String uuid1, String uuid2) {
		Person personEntity = personRepository.findByUuid(uuid1);
		Person personEntity2 = personRepository.findByUuid(uuid2);
		personEntity.setLinksTo(personEntity2);
		personRepository.save(personEntity);
		return true;
	}

}
