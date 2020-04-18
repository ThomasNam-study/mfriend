package kr.purred.fc.mfriend.service;

import kr.purred.fc.mfriend.domain.Block;
import kr.purred.fc.mfriend.domain.Person;
import kr.purred.fc.mfriend.repository.BlockRepository;
import kr.purred.fc.mfriend.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PersonService
{
	@Autowired private PersonRepository personRepository;

	@Autowired private BlockRepository blockRepository;

	public List<Person> getPeopleExcludeBlocks ()
	{
		// List<Person> people = personRepository.findAll ();
		// List<Block> blocks = blockRepository.findAll ();
		// List<String> blockNames = blocks.stream ().map (Block::getName).collect (Collectors.toList ());

		// return people.stream ().filter (person -> !blockNames.contains (person.getName ())).collect(Collectors.toList());

		// return people.stream ().filter (person -> person.getBlock () == null).collect(Collectors.toList());

		return personRepository.findByBlockIsNull ();
	}

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

	public List<Person> getPeopleByBloodType (String bloodType)
	{
		return personRepository.findByBloodType (bloodType);
	}

	@Transactional
	public void addPerson (Person person)
	{
		personRepository.save (person);
	}
}
