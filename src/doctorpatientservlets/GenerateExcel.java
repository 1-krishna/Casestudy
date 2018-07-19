package doctorpatientservlets;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.sun.corba.se.spi.activation.Repository;

import authguard.Authenticate;
import beans.Doctor;
import beans.DoctorPatient;
import beans.Patient;
import dao.AdminLoginDAO;
import dao.DoctorDAO;
import dao.DoctorPatientDAO;
import dao.PatientDAO;

import org.apache.poi.hssf.usermodel.HSSFRow;

/**
 * Servlet implementation class GenerateExcel
 */
@WebServlet("/GenerateExcel")
public class GenerateExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private DoctorDAO doctorDAO;
	private PatientDAO patientDAO;
	private DoctorPatientDAO doctorPatientDAO;
	private AdminLoginDAO adminLoginDAO;

	@Override
	public void init() throws ServletException {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		doctorDAO = new DoctorDAO(jdbcURL, jdbcUsername, jdbcPassword);
		patientDAO = new PatientDAO(jdbcURL, jdbcUsername, jdbcPassword);
		doctorPatientDAO = new DoctorPatientDAO(jdbcURL, jdbcUsername, jdbcPassword);
		adminLoginDAO = new AdminLoginDAO(jdbcURL, jdbcUsername, jdbcPassword);
	}

	public ArrayList<Details> generateAll() throws SQLException {
		ArrayList<Details> output = new ArrayList<>();

		DoctorPatient docPatBean = null;
		Doctor docBean = null;
		Patient pBean = null;
		List<DoctorPatient> allDoctPat = doctorPatientDAO.listAllDoctorPatient();

		for (DoctorPatient docPat : allDoctPat) {
			try {
				docPatBean = doctorPatientDAO.getDoctorPatientByRecordNo(docPat.getRecord_no());
				docBean = doctorDAO.getDoctor(docPatBean.getDoc_id());
				pBean = patientDAO.getPatient(docPatBean.getP_id());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String p_name = pBean.getP_name();
			String description = docPatBean.getDescription();
			String doc_name = docBean.getDoc_name();
			int p_age = pBean.getP_age();
			String p_gender = pBean.getP_gender();
			String p_mobile = pBean.getP_mobile();
			Date dt = docPatBean.getDt();
			String doc_specialization = docBean.getDoc_specialization();
			String p_address = pBean.getP_address();

			output.add(new Details(p_name, description, doc_name, p_age, p_gender, p_mobile, dt, doc_specialization,
					p_address));

		}

		return output;
	}

	public GenerateExcel() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if(!Authenticate.isAdmin(request)){
			response.sendRedirect("sessiontimeout.html");
		}

		try {
			String filename = "E:/java_programs/Casestudy/GeneratedDocs/CRFData.xls";
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("FirstSheet");

			HSSFRow rowhead = sheet.createRow(0);
			rowhead.createCell(0).setCellValue("No.");
			rowhead.createCell(1).setCellValue("Patient Name");
			rowhead.createCell(2).setCellValue("Description");
			rowhead.createCell(3).setCellValue("Doctor Name");
			rowhead.createCell(4).setCellValue("Patient Age");
			rowhead.createCell(5).setCellValue("Patient Gender");
			rowhead.createCell(6).setCellValue("Patient Mobile");
			rowhead.createCell(7).setCellValue("Date");
			rowhead.createCell(8).setCellValue("Doctor Specialization");
			rowhead.createCell(9).setCellValue("Patient Address");

			ArrayList<Details> details = generateAll();
			int count = 0;
			for (Details detail : details) {
				count++;
				HSSFRow row = sheet.createRow(count);
				row.createCell(0).setCellValue(count);
				row.createCell(1).setCellValue(detail.p_name);
				row.createCell(2).setCellValue(detail.description);
				row.createCell(3).setCellValue(detail.doc_name);
				row.createCell(4).setCellValue(detail.p_age);
				row.createCell(5).setCellValue(detail.p_gender);
				row.createCell(6).setCellValue(detail.p_mobile);
				row.createCell(7).setCellValue(detail.dt.toString());
				row.createCell(8).setCellValue(detail.doc_specialization);
				row.createCell(9).setCellValue(detail.p_address);
				
			}
			FileOutputStream fileOut = new FileOutputStream(filename);
			workbook.write(fileOut);
			fileOut.close();
			// workbook.close()
			System.out.println("Excel Sheet Generated of CRF Data");

		} catch (Exception ex) {
			System.out.println(ex);
		}
		
		
		generateHtml(request, response);

	}

	private void generateHtml(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		String filename = "CRFData.xls";   
		String filepath = "E:/java_programs/Casestudy/GeneratedDocs/";   
		response.setContentType("APPLICATION/OCTET-STREAM");   
		response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");   
		  
		FileInputStream fileInputStream = new FileInputStream(filepath + filename);  
		            
		int i;   
		while ((i=fileInputStream.read()) != -1) {  
		out.write(i);   
		}   
		fileInputStream.close();   
		out.close(); 
		
	}

}
