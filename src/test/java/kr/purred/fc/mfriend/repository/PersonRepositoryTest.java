package kr.purred.fc.mfriend.repository;

import kr.purred.fc.mfriend.domain.Person;
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
	void crud ()
	{
		Person person = new Person ();

		person.setName ("TESTER!!");
		person.setAge (20);
		person.setBloodType ("A");

		personRepository.save (person);

		// System.out.println (personRepository.findAll ());
		List<Person> persons = personRepository.findAll ();

		assertThat(persons.size ()).isEqualTo (1);
		assertThat(persons.get (0).getName ()).isEqualTo ("TESTER!!");
		assertThat(persons.get (0).getAge ()).isEqualTo (20);
		assertThat(persons.get (0).getBloodType ()).isEqualTo ("A");
	}

	@Test
	void hashTest ()
	{
		Person test1 = new Person ("Test", 10, "A");
		Person test2 = new Person ("Test", 10, "B");
	}
}