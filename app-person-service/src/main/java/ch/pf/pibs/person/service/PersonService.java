package ch.pf.pibs.person.service;


import ch.pf.pibs.person.entity.Person;
import ch.pf.pibs.person.exception.PersonNotFoundException;
import ch.pf.pibs.person.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private static final Logger log = LoggerFactory.getLogger(PersonService.class);

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public Person registerPerson(Person person) throws IllegalArgumentException {
        if (person.getId() != null) {
            throw new IllegalArgumentException("identifier=id must be null");
        }
        Person registeredPerson = personRepository.save(person);
        log.info("successfully registered person with id={}", registeredPerson.getId());
        return registeredPerson;
    }

    public Person findPerson(long id) throws PersonNotFoundException {
        return personRepository
                .findById(id)
                .orElseThrow(() -> new PersonNotFoundException("person with id=%s not found".formatted(id)));
    }

    public List<Person> findAllPersons(){
        return personRepository.findAll();
    }

    @Transactional
    public Person updatePerson(Person person) throws IllegalArgumentException, PersonNotFoundException {
        if (person.getId() != null) {
            Optional<Person> personByIdOptional = personRepository.findById(person.getId());
            if (personByIdOptional.isPresent()) {
                return personRepository.save(person);
            }
            throw new PersonNotFoundException("person with id=%s not found".formatted(person.getId()));
        }
        throw new IllegalArgumentException("person.id must not be null");
    }

}
