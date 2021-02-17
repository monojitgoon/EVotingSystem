package uni.bamberg.appengine.auth;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ApplicationFilter implements Filter {

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		UserService userService = UserServiceFactory.getUserService();

		if (!userService.isUserLoggedIn()) {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} else if (!userService.isUserAdmin()) {
			request.getRequestDispatcher("/votingview").forward(request, response);
		} else if (userService.isUserAdmin()) {
			request.getRequestDispatcher("/admin.jsp").forward(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
	}

}
