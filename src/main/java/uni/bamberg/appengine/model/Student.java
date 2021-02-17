package uni.bamberg.appengine.model;

import java.io.Serializable;

public final class Student implements Serializable {

    private  String email;

    private  String token;

    private  String isvalid;
    
    public Student() {
		super();
	}

    public Student(String firstname, String token, String isvalid) {
	this.email = firstname;
	this.token = token;
	this.isvalid = isvalid;
    }

    public String getEmail() {
	return this.email;
    }

    public String getToken() {
	return this.token;
    }

    public String getIsvalid() {
	return this.isvalid;
    }

}
