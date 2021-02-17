package uni.bamberg.appengine.management;

import java.util.List;

import uni.bamberg.appengine.model.Candidate;
import uni.bamberg.appengine.model.Student;
import uni.bamberg.appengine.model.Timing;
import uni.bamberg.appengine.model.Voting;

public interface EVotingSystemManagement {

	void saveTiming(Timing timing);

	void saveCandidates(Candidate candidate);

	List<Candidate> getAllCandidates();

	List<Student> getAllStudents();

	void importStudents(List<String> emails);

	List<Timing> getAllTimings();
	
	void saveVoting(Voting voting);

}
