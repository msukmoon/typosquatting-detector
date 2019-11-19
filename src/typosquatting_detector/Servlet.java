package typosquatting_detector;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet extends HttpServlet {
		
	private static final long serialVersionUID = 6248427955682198986L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if(request.getParameter("input") != null && request.getParameter("input") != "" && request.getParameter("input").indexOf(' ') == -1) {
			try {
				Server.main(new String[] {request.getParameter("input")});
				Client.main(new String[] {});
//				request.setAttribute("output", Server.getTypos());
			}
			catch (Exception e) {
				e.printStackTrace();
			}
        }
        else {
            request.setAttribute("output", "No input received!");
        }
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
    
}
