package uni.bamberg.appengine.frontend;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uni.bamberg.appengine.backend.EVotingSystemBackend;
import uni.bamberg.appengine.model.Candidate;
import uni.bamberg.appengine.model.Student;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		EVotingSystemBackend eVotingSystemBackend = new EVotingSystemBackend();
		List<String>  mails = eVotingSystemBackend.importStudentEMailsFromBlob(request);

		eVotingSystemBackend.importStudents(mails);
		/*List<Student> Students = Collections.<Student>emptyList();
		Students = eVotingSystemBackend.getAllStudents();
		request.setAttribute("StudentsList", Students);
		System.out.println( "inside get");

		// redirect to JSP presentation
		request.getRequestDispatcher("/viewstudent").forward(request, response);*/
		response.sendRedirect("/viewstudent");
	}

}
