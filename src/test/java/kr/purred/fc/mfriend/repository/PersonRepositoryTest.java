package kr.purred.fc.mfriend.repository;

import kr.purred.fc.mfriend.domain.Person;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class PersonRepositoryTest
{
	@Autowired
	PersonRepository personRepository;

	@Test
	void findByName ()
	{
		List<Person> people = personRepository.findByName("tony");

		assertThat(people.size ()).isEqualTo(1);

		Person person = people.get(0);

		assertAll(
				() -> Assertions.assertThat(person.getName ()).isEqualTo("tony"),
				() -> Assertions.assertThat(person.getHobby ()).isEqualTo("reading"),
				() -> Assertions.assertThat(person.getAddress ()).isEqualTo("Seoul")
		);
	}

	/*@Test
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
	}*/

	/*@Test
	void hashTest ()
	{
		Person test1 = new Person ("Test", 10, "A");
		Person test2 = new Person ("Test", 10, "B");
	}*/
}