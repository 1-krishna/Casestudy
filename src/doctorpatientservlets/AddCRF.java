package doctorpatientservlets;

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
import beans.Doctor;
import beans.DoctorPatient;
import beans.Patient;
import dao.DoctorDAO;
import dao.DoctorPatientDAO;
import dao.PatientDAO;

/**
 * Servlet implementation class AddCRF
 */
@WebServlet("/AddCRF")
public class AddCRF extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private DoctorDAO doctorDAO;
	private PatientDAO patientDAO;
	private DoctorPatientDAO doctorPatientDAO;
	
	@Override
	public void init() throws ServletException {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		
        doctorDAO = new DoctorDAO(jdbcURL, jdbcUsername, jdbcPassword);
        patientDAO = new PatientDAO(jdbcURL, jdbcUsername, jdbcPassword);
        doctorPatientDAO = new DoctorPatientDAO(jdbcURL, jdbcUsername, jdbcPassword);
	}
    public AddCRF() {
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
		out.println("<title>Add CRF Form</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		request.getRequestDispatcher("navuser.html").include(request, response);
		out.println("<div class='container'>");
		
		if(request.getAttribute("afterfirst") != null){
			out.println("<h3>Successfully Submitted</h3>");
		}
		
		List<Doctor> docList = null;
		List<Patient> pList = null;
		try {
			docList = doctorDAO.listAllDoctors();
			pList = patientDAO.listAllPatients();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("<form action='AddCRF' method='post' style='width: 300px'><div class='form-group'><label for='docsel'>"
				+"Select Doctor</label><select name='doc_id' class='form-control' id='docsel' >");
		for(Doctor doc : docList){
			out.println("<option value='"+doc.getDoc_id()+"'>" + doc.getDoc_id() + " | "+ doc.getDoc_name() + " ("+ doc.getDoc_specialization() +")" +"</option>");
		}
		out.println("</select></div>");
		out.println("<div class='form-group'><label for='psel'>"
				+"Select Patient</label><select name='p_id' class='form-control' id='psel'>");
		for(Patient p : pList){
			out.println("<option value='"+p.getP_id()+"'>" + p.getP_id() + " | "+ p.getP_name() + " ("+ p.getP_age() +" years)" + "</option>");
		}
		out.println("</select></div>");
		
		out.println("<div class='form-group'>"+
				"<label for='desc'>Description</label>"+
				"<textarea class='form-control' required='required' rows='5'"+
				"name='description' id='desc' placeholder='About Disease'></textarea></div>");
		out.println("<button type='submit' class='btn btn-primary'>Submit</button>");
		out.println("</form>");
		out.println("</div>");				
		//request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!Authenticate.isUser(request)){
			response.sendRedirect("sessiontimeout.html");
		}
		
		int doc_id = Integer.parseInt(request.getParameter("doc_id"));
		int p_id = Integer.parseInt(request.getParameter("p_id"));
		String description = request.getParameter("description");
		
		try {
			doctorPatientDAO.insertDoctorPatient(new DoctorPatient(doc_id, p_id, description));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("afterfirst", "true");
		doGet(request, response);
	}

}
