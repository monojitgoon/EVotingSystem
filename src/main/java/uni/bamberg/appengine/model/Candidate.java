package uni.bamberg.appengine.model;

import java.io.Serializable;

public final class Candidate implements Serializable {

    private  String firstname;

    private  String surname;

    private  String faculty;
    
    public Candidate() {
		super();
	}

    public Candidate(String firstname, String surname, String faculty) {
	this.firstname = firstname;
	this.surname = surname;
	this.faculty = faculty;
    }

    public String getFirstname() {
	return this.firstname;
    }

    public String getSurname() {
	return this.surname;
    }

    public String getFacultyString() {
	return this.faculty;
    }

}
