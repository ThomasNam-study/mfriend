package kr.purred.fc.mfriend.controller;

import kr.purred.fc.mfriend.domain.Person;
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
}
