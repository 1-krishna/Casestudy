package doctorservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Doctor;
import dao.DoctorDAO;

/**
 * Servlet implementation class ViewDoctors
 */
@WebServlet("/ViewDoctors")
public class ViewDoctors extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private DoctorDAO doctorDAO;
	
	@Override
	public void init() throws ServletException {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		
        doctorDAO = new DoctorDAO(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
    public ViewDoctors() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>View Doctors</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		request.getRequestDispatcher("navadmin.html").include(request, response);
		out.println("<div class='container'>");
		
		List<Doctor> list = null;
		try {
			list = doctorDAO.listAllDoctors();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("<table class='table table-bordered table-striped'>");
		out.println("<label><h3>All Doctors</h3></label>");
		out.println("<tr><th>Id</th><th>Name</th><th>Mobile</th><th>Specialization</th><th>Edit</th><th>Delete</th></tr>");
		for(Doctor bean:list){
			out.println("<tr><td>"+bean.getDoc_id()+"</td><td>"+bean.getDoc_name()+"</td><td>"+bean.getDoc_mobile()+"</td><td>"+bean.getDoc_specialization()+"</td><td><a href='EditDoctorForm?id="+bean.getDoc_id()+"'>Edit</a></td><td><a href='DeleteDoctor?id="+bean.getDoc_id()+"'>Delete</a></td></tr>");
		}
		out.println("</table>");
		
		out.println("</div>");
		//request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

}
