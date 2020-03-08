package com.springbootneo4j.service.impl;

import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootneo4j.constants.ErrorConstants;
import com.springbootneo4j.dto.PersonDto;
import com.springbootneo4j.entity.Person;
import com.springbootneo4j.exceptions.RequestValidationException;
import com.springbootneo4j.repository.PersonRepository;

@Service
public class PersonValidatorService {
	@Autowired
	PersonRepository personRepository;

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

	public void validateRequest(PersonDto person) {
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
	}

}
