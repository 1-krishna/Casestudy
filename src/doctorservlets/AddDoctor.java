package doctorservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Doctor;
import dao.DoctorDAO;

/**
 * Servlet implementation class AddDoctor
 */
@WebServlet("/AddDoctor")
public class AddDoctor extends HttpServlet {
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
	
    public AddDoctor() {
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
		out.println("<title>Add Doctor</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		request.getRequestDispatcher("navadmin.html").include(request, response);
		out.println("<div class='container'>");
		
		String doc_name=request.getParameter("name");
		String doc_mobile=request.getParameter("mobile");
		String doc_specialization=request.getParameter("specialization");
		
		
		Doctor bean=new Doctor(doc_name, doc_mobile, doc_specialization);
		
		try {
			if(doctorDAO.addDoctor(bean)){
				out.print("<h4>Doctor<b> "+doc_name+" </b>added successfully</h4>");
			}else{
				out.print("<h4>Some Error</h4>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			out.println("<h4>Doctor already registered</h4>");
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("adddoctorform.html").include(request, response);				
		out.println("</div>");
		//request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

}
