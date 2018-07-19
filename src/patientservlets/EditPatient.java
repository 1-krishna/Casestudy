package patientservlets;

import java.io.IOException;
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
 * Servlet implementation class EditPatient
 */
@WebServlet("/EditPatient")
public class EditPatient extends HttpServlet {
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
    public EditPatient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!Authenticate.isUser(request)){
			response.sendRedirect("sessiontimeout.html");
		}
		
		System.out.println("Patient Edited");
		
		int p_id = Integer.parseInt(request.getParameter("p_id"));
		String p_name = request.getParameter("p_name");
		String p_mobile = request.getParameter("p_mobile");
		int p_age = Integer.parseInt(request.getParameter("p_age"));
		String p_address = request.getParameter("p_address");
		String p_gender = request.getParameter("p_gender");
		
		Patient patient = new Patient(p_id, p_age, p_name, p_address, p_gender, p_mobile);
		
		try {
			patientDAO.updatePatient(patient);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("ViewPatients");
		
	}

}
