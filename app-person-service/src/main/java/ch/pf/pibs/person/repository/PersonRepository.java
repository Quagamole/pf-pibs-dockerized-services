package ch.pf.pibs.person.repository;

import ch.pf.pibs.person.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
