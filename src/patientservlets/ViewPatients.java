package patientservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import authguard.Authenticate;
import beans.Patient;
import dao.PatientDAO;

/**
 * Servlet implementation class ViewPatients
 */
@WebServlet("/ViewPatients")
public class ViewPatients extends HttpServlet {
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
	
    public ViewPatients() {
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
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>View Patients</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		request.getRequestDispatcher("navuser.html").include(request, response);
		out.println("<div class='container'>");
		
		List<Patient> list = null;
		try {
			list = patientDAO.listAllPatients();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("<table class='table table-bordered table-striped'>");
		out.println("<label><h3>All Patients</h3></label>");
		out.println("<tr><th>Id</th><th>Name</th><th>Mobile</th><th>View</th><th>Edit</th><th>Delete</th></tr>");
		for(Patient bean:list){
			out.println("<tr><td>"+bean.getP_id()+"</td><td>"+bean.getP_name()+"</td><td>"+bean.getP_mobile()+"</td><td><a href='ViewPatient?id="+bean.getP_id()+"'>View</a></td><td><a href='EditPatientForm?id="+bean.getP_id()+"'>Edit</a></td><td><a href='DeletePatient?id="+bean.getP_id()+"'>Delete</a></td></tr>");
		}
		out.println("</table>");
		
		out.println("</div>");
		//request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

}
