package uni.bamberg.appengine.frontend;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uni.bamberg.appengine.backend.EVotingSystemBackend;
import uni.bamberg.appengine.model.Candidate;

/**
 * Servlet implementation class CandidateServlet
 */
public class CandidateServlet extends HttpServlet {

	/**
	 * @see HttpServlet#HttpServlet()
	 */

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
		Candidate candidate = new Candidate();
		String firstname = (String) request.getParameter("firstname");
		String surname = (String) request.getParameter("surname");
		String faculty = (String) request.getParameter("faculty");
		candidate = new Candidate(firstname, surname, faculty);
		EVotingSystemBackend eVotingSystemBackend = new EVotingSystemBackend();
		eVotingSystemBackend.saveCandidates(candidate);

		response.sendRedirect("/candidate.jsp");

	}

}
