package kr.purred.fc.mfriend.service;

import kr.purred.fc.mfriend.domain.Block;
import kr.purred.fc.mfriend.domain.Person;
import kr.purred.fc.mfriend.domain.dto.Birthday;
import kr.purred.fc.mfriend.dto.PersonDto;
import kr.purred.fc.mfriend.repository.BlockRepository;
import kr.purred.fc.mfriend.repository.PersonRepository;
import net.bytebuddy.asm.Advice;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

// @SpringBootTest
@ExtendWith(MockitoExtension.class)
class PersonServiceTest
{
	/**
	 * 테스트 대상이 되는 클래스
	 */
	// @Autowired
	@InjectMocks
	private PersonService personService;

	// @Autowired
	@Mock
	private PersonRepository personRepository;

	@Test
	void getPersonTest ()
	{
		when(personRepository.findById(1L))
				.thenReturn(Optional.of(new Person("martin")));

		Person person = personService.getPerson(1L);

		assertThat(person.getName()).isEqualTo("martin");
	}

	@Test
	void getPersonIfNotFound ()
	{
		when(personRepository.findById(1L))
				.thenReturn(Optional.empty());

		Person person = personService.getPerson(1L);

		assertThat(person).isNull();
	}

	@Test
	void modifyIfPersonNotFound ()
	{
		when(personRepository.findById(1L))
				.thenReturn(Optional.empty());

		PersonDto dto = new PersonDto();

		dto.setName("martin");
		dto.setBirthday(LocalDate.now());
		dto.setAddress("Seoul");
		dto.setHobby("Programming");
		dto.setPhoneNumber("010-5349-6254");

		assertThrows(RuntimeException.class, () -> personService.modifyPerson (1L, dto));
	}

	@Test
	void modifyIfPersonDiff ()
	{
		when(personRepository.findById(1L))
				.thenReturn(Optional.of(new Person("tony")));

		PersonDto dto = new PersonDto();

		dto.setName("martin");
		dto.setBirthday(LocalDate.now());
		dto.setAddress("Seoul");
		dto.setHobby("Programming");
		dto.setPhoneNumber("010-5349-6254");

		assertThrows(RuntimeException.class, () -> personService.modifyPerson (1L, dto));
	}

	@Test
	void modify ()
	{
		when(personRepository.findById(1L))
				.thenReturn(Optional.of(new Person("martin")));

		PersonDto dto = new PersonDto();

		dto.setName("martin");
		dto.setBirthday(LocalDate.now());
		dto.setAddress("Seoul");
		dto.setHobby("Programming");
		dto.setPhoneNumber("010-5349-6254");

		personService.modifyPerson (1L, dto);

		verify (personRepository, times(1)).save (any(Person.class));
	}

	@Test
	void put ()
	{
		PersonDto dto = new PersonDto();

		dto.setName("martin");
		dto.setBirthday(LocalDate.now());
		dto.setAddress("Seoul");
		dto.setHobby("Programming");
		dto.setPhoneNumber("010-5349-6254");

		personService.addPerson(dto);

		verify(personRepository, times(1)).save(any(Person.class));
		verify(personRepository, times(1)).save(argThat (new IsPersonWillBeUpdated ()));
	}

	@Test
	void birthdayTest ()
	{
		/*givenPerson2 ("martin", 10, "A", LocalDate.of (1982, 7, 10));
		givenPerson2 ("david", 9, "B", LocalDate.of (1992, 8, 10));
		givenPerson2 ("runa", 9, "AB", LocalDate.of (2003, 10, 10));*/

		// List<Person> list = personRepository.findByBirthdayBetween (LocalDate.of (1991, 8, 1), LocalDate.of (2002, 8, 1));
		List<Person> list = personRepository.findByMonthOfBirthday (8);

		list.forEach (System.out::println);

		list = personRepository.findByMonthOfBirthdayAndDay (8, 10);

		list.forEach (System.out::println);
	}

	@Test
	void getPeopleByName ()
	{
		when(personRepository.findByName("martin"))
				.thenReturn(Lists.newArrayList(new Person("martin")));

		List<Person> lists = personService.getPeopleByName ("martin");

		assertThat (lists.size()).isEqualTo(1);
		assertThat (lists.get(0).getName ()).isEqualTo("martin");
	}

	/*@Test
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

		*//*personRepository.delete (person);
		personRepository.findAll ().forEach (System.out::println);
		blockRepository.findAll ().forEach (System.out::println);*//*

		person.setBlock (null);

		personRepository.save (person);

		personRepository.findAll ().forEach (System.out::println);
		blockRepository.findAll ().forEach (System.out::println);
	}*/

	@Test
	void getPerson ()
	{
		Person person = personService.getPerson (3L);

		assertThat(person).isNotNull ();
		assertThat(person).isEqualTo ("dennis");
	}

	/*private void givenPeople ()
	{
		givenPerson ("martin", 10, "A");
		givenPerson ("david", 9, "B");
		//givenPerson ("Dennis", 15, "AB");
		givenBlockPerson ("Dennis", 15, "AB");
		// givenPerson ("martin", 11, "AB");
		givenBlockPerson ("martin", 11, "AB");
	}*/

	/*private void givenBlocks ()
	{
		givenBlock ("martin");
	}*/

	/*private Block givenBlock (String name)
	{
		return blockRepository.save (new Block (name));
	}*/

	/*private void givenPerson (String name, int age, String bloodType)
	{
		personRepository.save (new Person (name, age, bloodType));
	}*/

	/*private void givenPerson2 (String name, int age, String bloodType, LocalDate birthday)
	{
		Person person = new Person (name, age, bloodType);

		// person.setBirthday (birthday);
		person.setBirthday (new Birthday (birthday));
		personRepository.save (person);
	}*/



	@Test
	void modifyByNameIfPersonNotFound ()
	{
		when(personRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(RuntimeException.class, () -> personService.modifyPerson(1L, "우하하"));
	}

	@Test
	void modifyByName ()
	{
		when(personRepository.findById(1L))
				.thenReturn(Optional.of(new Person("martin")));

		personService.modifyPerson(1L, "dany");

		verify(personRepository).save(argThat(new IsPersonWillBeNameUpdated()));
	}

	@Test
	void deleteByIfPersonNotFound ()
	{
		when(personRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(RuntimeException.class, () -> personService.deletePerson(1L));
	}

	@Test
	void deleteByIfPersonFound ()
	{
		when(personRepository.findById(1L))
				.thenReturn(Optional.of(new Person("martin")));

		personService.deletePerson(1L);

		verify(personRepository, times(1)).save(argThat(new IsPersonWillDeleted ()));
	}

	private static class IsPersonWillBeUpdated implements ArgumentMatcher<Person>
	{
		@Override
		public boolean matches (Person person)
		{
			return person.getName ().equals ("martin") && person.getHobby ().equals ("Programming") && person.getPhoneNumber ().equals ("010-5349-6254");
		}
	}

	private static class IsPersonWillBeNameUpdated implements ArgumentMatcher<Person>
	{
		@Override
		public boolean matches (Person person)
		{
			return person.getName ().equals ("dany");
		}
	}

	private static class IsPersonWillDeleted implements ArgumentMatcher<Person>
	{
		@Override
		public boolean matches (Person person)
		{
			return person.isDeleted();
		}
	}
}