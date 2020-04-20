package kr.purred.fc.mfriend.repository;

import kr.purred.fc.mfriend.domain.Person;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonRepositoryTest
{
	@Autowired
	PersonRepository personRepository;

	@Test
	@Disabled
	void crud ()
	{
		Person person = new Person ();

		person.setName ("john");

		personRepository.save (person);

		// System.out.println (personRepository.findAll ());
		List<Person> persons = personRepository.findByName ("john");

		assertThat(persons.size ()).isEqualTo (1);
		assertThat(persons.get (0).getName ()).isEqualTo ("john");
		assertThat(persons.get (0).getAge ()).isEqualTo (20);
	}

	/*@Test
	void hashTest ()
	{
		Person test1 = new Person ("Test", 10, "A");
		Person test2 = new Person ("Test", 10, "B");
	}*/
}