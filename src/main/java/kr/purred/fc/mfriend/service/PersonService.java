package kr.purred.fc.mfriend.service;

import kr.purred.fc.mfriend.domain.Person;
import kr.purred.fc.mfriend.dto.PersonDto;
import kr.purred.fc.mfriend.exception.PersonNotFoundException;
import kr.purred.fc.mfriend.exception.RenameNotPermittedException;
import kr.purred.fc.mfriend.repository.BlockRepository;
import kr.purred.fc.mfriend.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class PersonService
{
    @Autowired
    private PersonRepository personRepository;

    @Transactional(readOnly = true)
    public Person getPerson (Long id)
    {
        Person person = personRepository.findById (id).orElse (null);

        log.info ("{}", person);

        return person;
    }

    public List<Person> getPeopleByName (String name)
    {
//		List<Person> people = personRepository.findAll ();
//
//		return people.stream ().filter (person -> person.getName ().equals (name)).collect(Collectors.toList());

        return personRepository.findByName (name);
    }

    @Transactional
    public void addPerson (PersonDto personDto)
    {
        Person person = new Person ();

        person.set (personDto);
        person.setName (personDto.getName ());

        personRepository.save (person);
    }

    @Transactional
    public void modifyPerson (Long id, PersonDto personDto)
    {
        Person person = personRepository.findById (id).orElseThrow (PersonNotFoundException::new);

        if (!person.getName ().equals (personDto.getName ()))
            throw new RenameNotPermittedException ();

        person.set (personDto);

        personRepository.save (person);
    }

    @Transactional
    public void modifyPerson (Long id, String name)
    {
        Person person = personRepository.findById (id).orElseThrow (PersonNotFoundException::new);

        person.setName (name);

        personRepository.save (person);
    }

    @Transactional
    public void deletePerson (Long id)
    {
        // personRepository.deleteById (id);

        Person person = personRepository.findById (id).orElseThrow (PersonNotFoundException::new);

        person.setDeleted (true);

        personRepository.save (person);
    }

    public Page<Person> getAll (Pageable pageable)
    {
        return personRepository.findAll (pageable);
    }
}
