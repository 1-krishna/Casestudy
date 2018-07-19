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
import beans.DoctorPatient;
import dao.DoctorDAO;
import dao.DoctorPatientDAO;
import dao.PatientDAO;

/**
 * Servlet implementation class ViewCRFs
 */
@WebServlet("/ViewCRFs")
public class ViewCRFs extends HttpServlet {
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
	
    public ViewCRFs() {
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
		
		System.out.println("View CRFs Called");
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>View CRFs</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		request.getRequestDispatcher("navuser.html").include(request, response);
		out.println("<div class='container'>");
		
		List<DoctorPatient> list = null;
		try {
			list = doctorPatientDAO.listAllDoctorPatient();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("<table class='table table-bordered table-striped'>");
		out.println("<label><h3>All Patients</h3></label>");
		out.println("<tr><th>Record No</th><th>Patient Name</th><th>Doctor Name</th><th>Specialization</th><th>Date</th><th>View</th><th>Delete</th></tr>");
		for(DoctorPatient bean : list){
			String doc_name = null;
			String p_name = null;
			String specialization = null;
			try {
				p_name = patientDAO.getPatient(bean.getP_id()).getP_name();
				doc_name = doctorDAO.getDoctor(bean.getDoc_id()).getDoc_name();
				specialization = doctorDAO.getDoctor(bean.getDoc_id()).getDoc_specialization();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			out.println("<tr><td>"+bean.getRecord_no()+"</td><td>"+p_name+"</td><td>"+doc_name+"</td><td>"+specialization+"</td><td>"+bean.getDt()+"</td><td><a href='ViewCRF?id="+bean.getRecord_no()+"'>View</a></td><td><a href='DeleteCRF?id="+bean.getRecord_no()+"'>Delete</a></td></tr>");
		}
		out.println("</table>");
		
		out.println("</div>");
		//request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

}
