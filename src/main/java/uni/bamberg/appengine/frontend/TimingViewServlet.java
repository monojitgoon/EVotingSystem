package uni.bamberg.appengine.frontend;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uni.bamberg.appengine.backend.EVotingSystemBackend;
import uni.bamberg.appengine.model.Timing;

/**
 * Servlet implementation class CandidateServlet
 */
public class TimingViewServlet extends HttpServlet {

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
		List<Timing> timings = Collections.<Timing>emptyList();
		EVotingSystemBackend eVotingSystemBackend = new EVotingSystemBackend();
		timings = eVotingSystemBackend.getAllTimings();
		request.setAttribute("timingsList", timings);
		// redirect to JSP presentation
		request.getRequestDispatcher("/viewtiming.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

	}

}
