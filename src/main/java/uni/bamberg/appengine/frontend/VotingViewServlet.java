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
public class VotingViewServlet extends HttpServlet {

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
		List<Candidate> candidates = Collections.<Candidate>emptyList();
		EVotingSystemBackend eVotingSystemBackend = new EVotingSystemBackend();
		candidates = eVotingSystemBackend.getAllCandidates();
		request.setAttribute("candidatesList", candidates);
		request.getSession().getServletContext().setAttribute("candidates", candidates);

		// redirect to JSP presentation
		request.getRequestDispatcher("/voting.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

	}

}
