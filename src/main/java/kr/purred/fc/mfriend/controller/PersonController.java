package kr.purred.fc.mfriend.controller;

import kr.purred.fc.mfriend.domain.Person;
import kr.purred.fc.mfriend.dto.ErrorResponse;
import kr.purred.fc.mfriend.dto.PersonDto;
import kr.purred.fc.mfriend.exception.PersonNotFoundException;
import kr.purred.fc.mfriend.exception.RenameNotPermittedException;
import kr.purred.fc.mfriend.repository.PersonRepository;
import kr.purred.fc.mfriend.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/person")
@RestController
@Slf4j
//@ControllerAdvice
public class PersonController
{
	@Autowired private PersonService personService;

	@Autowired private PersonRepository personRepository;

	@GetMapping
	public Page<Person> getAll (@PageableDefault Pageable pageable)
	{
		return personService.getAll (pageable);
	}

	@GetMapping("/{id}")
	public Person getPerson (@PathVariable("id") Long id)
	{
		return personService.getPerson (id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void postPerson (@RequestBody @Valid PersonDto personDto)
	{
		log.info ("{}", personDto);

		personService.addPerson (personDto);
	}

	@PutMapping("/{id}")
	public void putPerson (@PathVariable("id") Long id, @RequestBody PersonDto person)
	{
//		try {
			personService.modifyPerson (id, person);

			/*log.info ("person -> {}", personRepository.findAll ());
		}
		catch (RuntimeException ex)
		{
			log.error(ex.getMessage(), ex);
		}*/
	}

	@PatchMapping("/{id}")
	public void putPersonName (@PathVariable("id") Long id, @RequestParam String name)
	{
//		try {
			personService.modifyPerson(id, name);

/*			log.info("person -> {}", personRepository.findAll());
		}
		catch (RuntimeException ex)
		{
			log.error(ex.getMessage(), ex);
		}*/
	}

	@DeleteMapping("/{id}")
	public void deletePerson (@PathVariable("id") Long id)
	{
		personService.deletePerson (id);

		log.info ("person -> {}", personRepository.findAll ());

		// return personRepository.findDeletePeople().stream().anyMatch((p) -> p.getId().equals(id));
	}


}
