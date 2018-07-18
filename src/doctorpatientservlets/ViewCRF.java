package doctorpatientservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Doctor;
import beans.DoctorPatient;
import beans.Patient;
import dao.DoctorDAO;
import dao.DoctorPatientDAO;
import dao.PatientDAO;

/**
 * Servlet implementation class ViewCRF
 */
@WebServlet("/ViewCRF")
public class ViewCRF extends HttpServlet {
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

	public ViewCRF() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		int record_no = Integer.parseInt(request.getParameter("id"));

		DoctorPatient docPatBean = null;
		Doctor docBean = null;
		Patient pBean = null;
		try {
			docPatBean = doctorPatientDAO.getDoctorPatientByRecordNo(record_no);
			docBean = doctorDAO.getDoctor(docPatBean.getDoc_id());
			pBean = patientDAO.getPatient(docPatBean.getP_id());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("p_name", pBean.getP_name());
		request.setAttribute("description", docPatBean.getDescription());
		request.setAttribute("doc_name", docBean.getDoc_name());
		request.setAttribute("p_age", pBean.getP_age());
		request.setAttribute("p_gender", pBean.getP_gender());
		request.setAttribute("p_mobile", pBean.getP_mobile());
		request.setAttribute("dt", docPatBean.getDt());
		request.setAttribute("doc_specialization", docBean.getDoc_specialization());
		request.setAttribute("p_address", pBean.getP_address());
		
		
		out.println("<!DOCTYPE html><html><head><style>table {    border-collapse: collapse;} table,th,td { border: 1px solid black; } td[rowspan] { vertical-align: top;  text-align: left; } @media print { #printPageButton { display: none;   }}</style><title>Patient Report Form</title><link rel='stylesheet' href='bootstrap.min.css'/></head><body>");
		//out.println("<div id='printPageButton'>");
		request.getRequestDispatcher("navuser.html").include(request, response);
		//out.println("</div>");
		out.println("<div class='container'>");
		request.getRequestDispatcher("CRF.jsp").include(request, response);
		out.println("</div>");
		out.close();
	}

}
