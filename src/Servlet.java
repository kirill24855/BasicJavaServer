import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
		String page = req.getParameter("page");

		response.getWriter().println("<html>");
		response.getWriter().println("<head>");
		response.getWriter().println("<title>");
		response.getWriter().println("title");
		response.getWriter().println("</title>");
		response.getWriter().println("</head>");
		response.getWriter().println("<body>");
		response.getWriter().println("You requested: " + page);
		response.getWriter().println("</body>");
		response.getWriter().println("</html>");
	}
}
