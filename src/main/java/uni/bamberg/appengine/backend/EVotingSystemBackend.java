package uni.bamberg.appengine.backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreInputStream;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

import uni.bamberg.appengine.management.EVotingSystemManagement;
import uni.bamberg.appengine.model.Candidate;
import uni.bamberg.appengine.model.Student;
import uni.bamberg.appengine.model.Timing;
import uni.bamberg.appengine.model.Voting;;

public class EVotingSystemBackend implements EVotingSystemManagement {
	private static final Logger logger = Logger.getLogger(EVotingSystemBackend.class.getName());

	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

	@Override
	public void saveTiming(Timing timing) {
		// TODO: Auto-generated method stub
		deleteOldTiming();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity candidateEntity = new Entity("Timing");
		candidateEntity.setProperty("fromdate", timing.getFromdate());
		candidateEntity.setProperty("todate", timing.getTodate());
		datastore.put(candidateEntity);
	}

	private void deleteOldTiming() {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("Timing");
		Iterable<Entity> entities = datastore.prepare(query).asIterable();
		entities.forEach(entity -> {
			datastore.delete(entity.getKey());
		});
	}

	@Override
	public void saveCandidates(Candidate candidate) {
		// TODO: Auto-generated method stub
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity candidateEntity = new Entity("Candidate");
		candidateEntity.setProperty("firstname", candidate.getFirstname());
		candidateEntity.setProperty("surname", candidate.getSurname());
		candidateEntity.setProperty("faculty", candidate.getFacultyString());
		datastore.put(candidateEntity);
	}

	@Override
	public List<Candidate> getAllCandidates() {
		// TODO Auto-generated method stub
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("Candidate").addSort("firstname", Query.SortDirection.DESCENDING);
		List<Entity> candidates = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(10));
		return convert(candidates);
	}

	private List<Candidate> convert(List<Entity> cndt) {
		List<Candidate> ret = new ArrayList<Candidate>();

		if (cndt != null) {
			for (Entity r : cndt) {
				ret.add(ConvertCandidateEntitytoModel(r));
			}
		}

		return ret;
	}

	public Candidate ConvertCandidateEntitytoModel(Entity entity) {
		System.out.println((String) entity.getProperty("firstname") + "inside convertion");

		Candidate Candidate = new Candidate((String) entity.getProperty("firstname"),
				(String) entity.getProperty("surname"), (String) entity.getProperty("faculty"));
		return Candidate;

	}

	public static String generateString() {
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}

	@Override
	public void importStudents(List<String> emails) {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		emails.stream().forEach(email -> {
			Transaction transaction = datastore.beginTransaction();
			Entity StudentEntity = new Entity("Student");
			StudentEntity.setProperty("email", email);
			StudentEntity.setProperty("token", generateString());
			StudentEntity.setProperty("isvalid", "true");
			datastore.put(transaction, StudentEntity);
			transaction.commit();
		});
	}
	
	public static List<String> importStudentEMailsFromBlob(HttpServletRequest req) {
		List<String> emails = new ArrayList<>();

		BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
		Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
		List<BlobKey> blobKeys = blobs.get("inputfile");

		try (BlobstoreInputStream is = new BlobstoreInputStream(blobKeys.get(0))) {
		    try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			String line;
			while ((line = br.readLine()) != null) {
			    emails.add(line);
			}
		    }
		} catch (IOException ex) {
		   
		}

		return emails;
	    }
	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("Student");
		List<Entity> students = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(10));
		return convertstudent(students);
	}

	private List<Student> convertstudent(List<Entity> cndt) {
		List<Student> ret = new ArrayList<Student>();

		if (cndt != null) {
			for (Entity r : cndt) {
				ret.add(ConvertStudentEentitytoModel(r));
			}
		}

		return ret;
	}

	public Student ConvertStudentEentitytoModel(Entity entity) {

		Student student = new Student((String) entity.getProperty("email"),
				(String) entity.getProperty("token"), (String) entity.getProperty("isvalid"));
		return student;

	}
	@Override
	public List<Timing> getAllTimings() {
		// TODO Auto-generated method stub
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("Timing");
		List<Entity> timings = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(10));
		return convertTiming(timings);
	}
	
	private List<Timing> convertTiming(List<Entity> timing) {
		List<Timing> ret = new ArrayList<Timing>();

		if (timing != null) {
			for (Entity r : timing) {
				ret.add(ConvertTimingEntitytoModel(r));
			}
		}

		return ret;
	}
	public Timing ConvertTimingEntitytoModel(Entity entity) {
		System.out.println((String) entity.getProperty("fromdate") + "inside convertion");

		Timing Timing = new Timing((String) entity.getProperty("fromdate"),
				(String) entity.getProperty("todate"));
		return Timing;

	}
	@Override
	public void saveVoting(Voting voting) {
		// TODO: Auto-generated method stub
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity votingEntity = new Entity("Voting");
		votingEntity.setProperty("candidatefirstname", voting.getCandidatefirstname());
		votingEntity.setProperty("studenttoken", voting.getStudenttoken());
		datastore.put(votingEntity);
	}
}
