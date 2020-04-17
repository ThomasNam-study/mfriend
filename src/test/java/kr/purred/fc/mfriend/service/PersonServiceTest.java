package kr.purred.fc.mfriend.service;

import kr.purred.fc.mfriend.domain.Block;
import kr.purred.fc.mfriend.domain.Person;
import kr.purred.fc.mfriend.repository.BlockRepository;
import kr.purred.fc.mfriend.repository.PersonRepository;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PersonServiceTest
{
	@Autowired
	private PersonService personService;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private BlockRepository blockRepository;

	@Test
	void getPeopleExcludeBlocks ()
	{
		givenPeople ();
		givenBlocks ();

		List<Person> personList = personService.getPeopleExcludeBlocks ();

		// System.out.println (personList);

		personList.forEach (System.out::println);
	}

	private void givenPeople ()
	{
		givenPerson ("martin", 10, "A");
		givenPerson ("david", 9, "B");
		//givenPerson ("Dennis", 15, "AB");
		givenBlockPerson ("Dennis", 15, "AB");
		// givenPerson ("martin", 11, "AB");
		givenBlockPerson ("martin", 11, "AB");
	}

	private void givenBlockPerson (String name, int age, String bloodType)
	{
		Person blockPerson = new Person (name, age, bloodType);

		blockPerson.setBlock (givenBlock (name));

		personRepository.save (blockPerson);
	}

	private void givenBlocks ()
	{
		givenBlock ("martin");
	}

	private Block givenBlock (String name)
	{
		return blockRepository.save (new Block (name));
	}

	private void givenPerson (String name, int age, String bloodType)
	{
		personRepository.save (new Person (name, age, bloodType));
	}
}