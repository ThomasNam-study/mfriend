package kr.purred.fc.mfriend.controller;

import kr.purred.fc.mfriend.domain.Person;
import kr.purred.fc.mfriend.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/person")
@RestController
public class PersonController
{
	@Autowired private PersonService personService;

	@GetMapping("/{id}")
	public Person getPerson (@PathVariable("id") Long id)
	{
		return personService.getPerson (id);
	}
}
