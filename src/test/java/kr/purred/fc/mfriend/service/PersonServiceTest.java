package kr.purred.fc.mfriend.service;

import kr.purred.fc.mfriend.domain.Block;
import kr.purred.fc.mfriend.domain.Person;
import kr.purred.fc.mfriend.repository.BlockRepository;
import kr.purred.fc.mfriend.repository.PersonRepository;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
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

	@Test
	void bloodTypeTest ()
	{
		givenPerson ("martin", 10, "A");
		givenPerson ("david", 9, "B");
		givenPerson ("runa", 9, "AB");

		List<Person> list = personService.getPeopleByBloodType ("A");

		list.forEach (System.out::println);
	}

	@Test
	void birthdayTest ()
	{
		givenPerson2 ("martin", 10, "A", LocalDate.of (1982, 10, 10));
		givenPerson2 ("david", 9, "B", LocalDate.of (1992, 10, 10));
		givenPerson2 ("runa", 9, "AB", LocalDate.of (2003, 10, 10));

		List<Person> list = personRepository.findByBirthdayBetween (LocalDate.of (1991, 8, 1), LocalDate.of (2002, 8, 1));

		list.forEach (System.out::println);
	}

	@Test
	void getPeopleByName ()
	{
		List<Person> lists = personService.getPeopleByName ("martin");

		lists.stream ().forEach (System.out::println);
	}

	@Test
	void cascadeTest ()
	{
		givenPeople ();

		List<Person> personList = personRepository.findAll ();

		Person person = personList.get (2);

		person.getBlock ().setStartDate (LocalDate.now ());
		person.getBlock ().setEndDate (LocalDate.now ());

		personRepository.save (person);

		// MERGE 적용 후 동작
		// personRepository.findAll ().forEach (System.out::println);

		/*personRepository.delete (person);
		personRepository.findAll ().forEach (System.out::println);
		blockRepository.findAll ().forEach (System.out::println);*/

		person.setBlock (null);

		personRepository.save (person);

		personRepository.findAll ().forEach (System.out::println);
		blockRepository.findAll ().forEach (System.out::println);
	}

	@Test
	void getPerson ()
	{
		givenPeople ();

		Person person = personService.getPerson (3L);

		//System.out.println (person);
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

		// blockPerson.setBlock (givenBlock (name));
		blockPerson.setBlock (new Block (name));

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

	private void givenPerson2 (String name, int age, String bloodType, LocalDate birthday)
	{
		Person person = new Person (name, age, bloodType);

		person.setBirthday (birthday);
		personRepository.save (person);
	}
}