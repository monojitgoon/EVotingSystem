package uni.bamberg.appengine.model;

import java.io.Serializable;

public final class Voting implements Serializable {

	private String candidatefirstname;

	private String studenttoken;

	public Voting() {
		super();
	}

	public Voting(String candidatefirstname, String studenttoken) {
		this.candidatefirstname = candidatefirstname;
		this.studenttoken = studenttoken;
	}

	public String getCandidatefirstname() {
		return this.candidatefirstname;
	}

	public String getStudenttoken() {
		return this.studenttoken;
	}

}
