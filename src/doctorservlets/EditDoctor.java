package doctorservlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Doctor;
import dao.DoctorDAO;

/**
 * Servlet implementation class EditDoctor
 */
@WebServlet("/EditDoctor")
public class EditDoctor extends HttpServlet {
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
	
    public EditDoctor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int doc_id=Integer.parseInt(request.getParameter("doc_id"));
		String doc_name=request.getParameter("doc_name");
		String doc_mobile=request.getParameter("doc_mobile");
		String doc_specialization = request.getParameter("doc_specialization");
		
		Doctor bean = new Doctor(doc_id, doc_name, doc_mobile, doc_specialization);
		
		try {
			doctorDAO.updateDoctor(bean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("ViewDoctors");
	}

}
