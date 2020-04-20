package kr.purred.fc.mfriend.controller;

import kr.purred.fc.mfriend.domain.Person;
import kr.purred.fc.mfriend.dto.PersonDto;
import kr.purred.fc.mfriend.repository.PersonRepository;
import kr.purred.fc.mfriend.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/person")
@RestController
@Slf4j
public class PersonController
{
	@Autowired private PersonService personService;

	@Autowired private PersonRepository personRepository;

	@GetMapping("/{id}")
	public Person getPerson (@PathVariable("id") Long id)
	{
		return personService.getPerson (id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void postPerson (@RequestBody Person person)
	{
		log.info ("{}", person);

		personService.addPerson (person);
	}

	@PutMapping("/{id}")
	public void putPerson (@PathVariable("id") Long id, @RequestBody PersonDto person)
	{
		personService.modifyPerson (id, person);

		log.info ("person -> {}", personRepository.findAll ());
	}

	@PatchMapping("/{id}")
	public void putPersonName (@PathVariable("id") Long id, @RequestParam String name)
	{
		personService.modifyPerson (id, name);

		log.info ("person -> {}", personRepository.findAll ());
	}

	@DeleteMapping("/{id}")
	public void deletePerson (@PathVariable("id") Long id)
	{
		personService.deletePerson (id);

		log.info ("person -> {}", personRepository.findAll ());

		// return personRepository.findDeletePeople().stream().anyMatch((p) -> p.getId().equals(id));
	}
}
