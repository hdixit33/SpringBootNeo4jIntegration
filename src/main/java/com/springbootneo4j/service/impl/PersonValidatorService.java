package com.springbootneo4j.service.impl;

import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.springbootneo4j.constants.ErrorConstants;
import com.springbootneo4j.dto.PersonDto;
import com.springbootneo4j.entity.Person;
import com.springbootneo4j.exceptions.RequestValidationException;
import com.springbootneo4j.repository.PersonRepository;


/**
 * The Class PersonValidatorService.
 */
@Service
public class PersonValidatorService {
	
	/** The person repository. */
	@Autowired
	PersonRepository personRepository;

	/**
	 * Validate uuid.
	 *
	 * @param uuid the uuid
	 */
	public void validateUuid(String uuid) {
		if (uuid == null) {
			throw new RequestValidationException(
					ErrorConstants.INVALID_UUID_MSG);
		}
		Person person = personRepository.findByUuid(uuid);
		if (person == null) {
			throw new RequestValidationException(
					ErrorConstants.INVALID_UUID_MSG);
		}
	}

	/**
	 * Validate request.
	 *
	 * @param person the person
	 * @param requestType the request type
	 */
	public void validateRequest(PersonDto person, String requestType) {
		if (StringUtils.isNullOrEmpty(person.getName())) {
			throw new RequestValidationException("name"
					+ ErrorConstants.NULL_INPUT_MSG);
		}
		if (StringUtils.isNullOrEmpty(person.getEmail())) {
			throw new RequestValidationException("email"
					+ ErrorConstants.NULL_INPUT_MSG);
		}
		if (StringUtils.isNullOrEmpty(person.getPhoneNumber())) {
			throw new RequestValidationException("phoneNumber"
					+ ErrorConstants.NULL_INPUT_MSG);
		}

		if (requestType.equals("create")) {
			
			if (!CollectionUtils.isEmpty(personRepository.findByEmail(person.getEmail()))) {
				throw new RequestValidationException("email"
						+ ErrorConstants.FIELD_ALREADY_EXISTS);
			}
			if (!CollectionUtils.isEmpty(personRepository.findByPhoneNumber(person.getPhoneNumber()))) {
				throw new RequestValidationException("phoneNumber"
						+ ErrorConstants.FIELD_ALREADY_EXISTS);
			}
		} else if (requestType.equals("update")) {
			Person personEntity = personRepository.findByUuid(person.getUuid());
			if (!CollectionUtils.isEmpty(personRepository.findByEmail(person.getEmail()))
					&& !person.getEmail().equalsIgnoreCase(
							personEntity.getEmail())) {
				throw new RequestValidationException("email"
						+ ErrorConstants.FIELD_ALREADY_EXISTS);
			}
			if (!CollectionUtils.isEmpty(personRepository.findByPhoneNumber(person.getPhoneNumber()))
					&& !person.getPhoneNumber().equalsIgnoreCase(
							personEntity.getPhoneNumber())) {
				throw new RequestValidationException("phoneNumber"
						+ ErrorConstants.FIELD_ALREADY_EXISTS);
			}
		}
	}

}
