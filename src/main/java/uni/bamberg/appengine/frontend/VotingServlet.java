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
import uni.bamberg.appengine.model.Voting;

/**
 * Servlet implementation class CandidateServlet
 */
public class VotingServlet extends HttpServlet {

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
		Voting voting = new Voting();
		String candidatefirstname = (String) request.getParameter("candidateradio");
		
		System.out.println(candidatefirstname);

		String studenttoken = (String) request.getParameter("studenttoken");
		
		System.out.println(studenttoken);

		voting = new Voting(candidatefirstname, studenttoken);
		EVotingSystemBackend eVotingSystemBackend = new EVotingSystemBackend();
		eVotingSystemBackend.saveVoting(voting);

		response.sendRedirect("/thanksgiving.jsp");

	}

}
