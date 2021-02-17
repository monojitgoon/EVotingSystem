package uni.bamberg.appengine.frontend;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uni.bamberg.appengine.backend.EVotingSystemBackend;
import uni.bamberg.appengine.model.Candidate;
import uni.bamberg.appengine.model.Timing;

/**
 * Servlet implementation class TimingServlet
 */
@WebServlet("/TimingServlet")
public class TimingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Timing timing=new Timing();
		String fromdate = (String) request.getParameter("fromdate");
    	String todate = (String) request.getParameter("todate");
    	timing= new Timing( fromdate, todate);
    	EVotingSystemBackend eVotingSystemBackend = new EVotingSystemBackend();
    	eVotingSystemBackend.saveTiming(timing);
          
    
    	response.sendRedirect("/timing.jsp");
	}

}
