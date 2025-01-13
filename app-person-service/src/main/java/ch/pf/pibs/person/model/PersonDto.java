package ch.pf.pibs.person.model;

public class PersonDto {

    private final long id;

    private final String firstName;

    private final String lastName;

    public PersonDto(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
