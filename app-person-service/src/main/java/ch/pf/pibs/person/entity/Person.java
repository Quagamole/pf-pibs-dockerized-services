package ch.pf.pibs.person.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "PERSON_SEQ")
    @SequenceGenerator(name = "PERSON_SEQ", sequenceName = "PERSON_SEQ")
    @Column(name = "id", nullable = false)
    @Positive
    private Long id;

    @Column(name = "first_name", nullable = false)
    @NotBlank
    @Size(min = 1)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank
    @Size(min = 1)
    private String lastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
