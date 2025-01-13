package ch.pf.pibs.person.web;

import ch.pf.pibs.person.entity.Person;
import ch.pf.pibs.person.exception.PersonNotFoundException;
import ch.pf.pibs.person.service.PersonService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService peopleService) {
        this.personService = peopleService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Person registerPerson(@RequestBody @Valid Person person) throws IllegalArgumentException {
        return personService.registerPerson(person);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Person findPerson(@PathVariable @Positive Long id) throws PersonNotFoundException {
        return personService.findPerson(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Person> findAllPersons() {
        return personService.findAllPersons();
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Person updatePerson(@PathVariable @Positive Long id, @RequestBody @Valid Person person) throws IllegalArgumentException, PersonNotFoundException {
        if (!id.equals(person.getId())) {
            throw new IllegalArgumentException("id and person.id not matching");
        }
        return personService.updatePerson(person);
    }

}
