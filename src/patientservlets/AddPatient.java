package patientservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Patient;
import dao.PatientDAO;

/**
 * Servlet implementation class AddPatient
 */
@WebServlet("/AddPatient")
public class AddPatient extends HttpServlet {
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
    public AddPatient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Add Patient</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		request.getRequestDispatcher("navuser.html").include(request, response);
		out.println("<div class='container'>");
		
		String p_name = request.getParameter("p_name");
		String p_mobile = request.getParameter("p_mobile");
		int p_age = Integer.parseInt(request.getParameter("p_age"));
		String p_address = request.getParameter("p_address");
		String p_gender = request.getParameter("p_gender");
	
		
		
		Patient bean = new Patient(p_name, p_mobile, p_age, p_address, p_gender);
		
		try {
			if(patientDAO.addPatient(bean)){
				if(p_gender.equals("male")){
					out.print("<h4><b>Mr. "+p_name+" </b>registered successfully</h4>");
				}else{
					out.print("<h4><b>Ms/Mrs. "+p_name+" </b>registered successfully</h4>");
				}
			}else{
				out.print("<h4>Some Error</h4>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			out.println("<h4>Patient already registered</h4>");
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("addpatientform.jsp").include(request, response);				
		out.println("</div>");
		//request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
		
	}

}
