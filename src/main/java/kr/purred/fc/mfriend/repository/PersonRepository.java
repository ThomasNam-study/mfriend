package kr.purred.fc.mfriend.repository;

import kr.purred.fc.mfriend.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long>
{
	List<Person> findByName (String name);

	List<Person> findByBlockIsNull ();

	List<Person> findByBloodType (String bloodType);

	// List<Person> findByBirthdayBetween (LocalDate startDate, LocalDate endDate);
	@Query(value = "select person from Person person where person.birthday.month = ?1")
	List<Person> findByMonthOfBirthday (int month);

	@Query(value = "select person from Person person where person.birthday.month = :month and person.birthday.day = :day")
	List<Person> findByMonthOfBirthdayAndDay (@Param("month") int month, @Param("day") int day);
}
