package kr.purred.fc.mfriend.service;

import kr.purred.fc.mfriend.domain.Person;
import kr.purred.fc.mfriend.dto.PersonDto;
import kr.purred.fc.mfriend.repository.BlockRepository;
import kr.purred.fc.mfriend.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class PersonService
{
	@Autowired private PersonRepository personRepository;

	@Transactional(readOnly = true)
	public Person getPerson (Long id)
	{
		Person person = personRepository.findById (id).orElse (null);

		log.info ("{}", person);

		return person;
	}

	public List<Person> getPeopleByName (String name)
	{
//		List<Person> people = personRepository.findAll ();
//
//		return people.stream ().filter (person -> person.getName ().equals (name)).collect(Collectors.toList());

		return personRepository.findByName (name);
	}

	@Transactional
	public void addPerson (PersonDto personDto)
	{
		Person person = new Person();

		person.set(personDto);
		person.setName(personDto.getName());

		personRepository.save (person);
	}

	@Transactional
	public void modifyPerson (Long id, PersonDto personDto)
	{
		Person person = personRepository.findById (id).orElseThrow (() -> new RuntimeException ("아이디가 존재 하지 않음"));

		if (!person.getName ().equals (personDto.getName ()))
			throw new RuntimeException ("이름이 드랍니다.");

		person.set (personDto);

		personRepository.save (person);
	}

	@Transactional
	public void modifyPerson (Long id, String name)
	{
		Person person = personRepository.findById (id).orElseThrow (() -> new RuntimeException ("아이디가 존재 하지 않음"));

		person.setName (name);

		personRepository.save (person);
	}

	@Transactional
	public void deletePerson (Long id)
	{
		// personRepository.deleteById (id);

		Person person = personRepository.findById (id).orElseThrow (() -> new RuntimeException ("아이디가 존재 하지 않음"));

		person.setDeleted (true);

		personRepository.save (person);
	}
}
