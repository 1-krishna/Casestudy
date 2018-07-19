package doctorservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import authguard.Authenticate;
import beans.Doctor;
import dao.DoctorDAO;

/**
 * Servlet implementation class EditDoctorForm
 */
@WebServlet("/EditDoctorForm")
public class EditDoctorForm extends HttpServlet {
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
	
    public EditDoctorForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!Authenticate.isAdmin(request)){
			response.sendRedirect("sessiontimeout.html");
		}
		
		System.out.println("Edit Doctor Form Called");
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Edit Librarian Form</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		request.getRequestDispatcher("navadmin.html").include(request, response);
		out.println("<div class='container'>");
		
		String doc_id=request.getParameter("id");
		
		Doctor bean = null;
		try {
			bean = doctorDAO.getDoctor(Integer.parseInt(doc_id));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.print("<form action='EditDoctor' method='post' style='width:300px'>");
		out.print("<div class='form-group'>");
		out.print("<input type='hidden' name='doc_id' value='"+bean.getDoc_id()+"'/>");
		out.print("<label for='name1'>Name</label>");
		out.print("<input type='text' required='required' class='form-control' value='"+bean.getDoc_name()+"' name='doc_name' id='name1' placeholder='Name'/>");
		out.print("</div>");
		out.print("<div class='form-group'>");
		out.print("<label for='password1'>Mobile</label>");
		out.print("<input type='number' required='required' class='form-control' value='"+bean.getDoc_mobile()+"'  name='doc_mobile' id='password1' placeholder='Mobile'/>");
		out.print("</div> ");
		out.print("<div class='form-group'>");
		out.print("<label for='specialization1'>Specialization</label>");
		out.print("<input type='text' required='required' class='form-control' value='"+bean.getDoc_specialization()+"'  name='doc_specialization' id='specialization1' placeholder='Specialization'/>");
		out.print("</div>  ");
		out.print("<button type='submit' class='btn btn-primary'>Update</button>");
		out.print("</form>");
		
		out.println("</div>");
		//request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

}
