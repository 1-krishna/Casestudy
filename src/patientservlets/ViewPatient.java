package patientservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import authguard.Authenticate;
import beans.Patient;
import dao.PatientDAO;

/**
 * Servlet implementation class ViewPatient
 */
@WebServlet("/ViewPatient")
public class ViewPatient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	
	private PatientDAO patientDAO;
	
	@Override
	public void init() throws ServletException {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		
        patientDAO = new PatientDAO(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
    public ViewPatient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!Authenticate.isUser(request)){
			response.sendRedirect("sessiontimeout.html");
		}
		
		System.out.println("View Patient Called");
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>View Patient</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		request.getRequestDispatcher("navuser.html").include(request, response);
		out.println("<div class='container'>");
		
		int p_id = Integer.parseInt(request.getParameter("id"));
		
		Patient patient = null;
		
		try {
			patient = patientDAO.getPatient(p_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("forview", "true");
		request.setAttribute("patient", patient);
		request.getRequestDispatcher("addpatientform.jsp").include(request, response);
		
		out.println("</div>");				
		//request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

}
