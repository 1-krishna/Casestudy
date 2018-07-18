package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.DoctorPatient;


public class DoctorPatientDAO {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public DoctorPatientDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		super();
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
	}

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}
	
	public ArrayList<DoctorPatient> listAllDoctorPatient() throws SQLException{
		ArrayList<DoctorPatient> listDoctorPatient = new ArrayList<>();

		String sql = "SELECT * FROM DOCTOR_PATIENT";

		connect();

		Statement st = jdbcConnection.createStatement();

		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			int record_no = rs.getInt("record_no");
			int doc_id = rs.getInt("doc_id");
			int p_id = rs.getInt("p_id");
			String description = rs.getString("description");
			Date dt = rs.getDate("dt");
			listDoctorPatient.add(new DoctorPatient(record_no, doc_id, p_id, description, dt));
		}

		rs.close();
		st.close();

		disconnect();

		return listDoctorPatient;
	}
	
	
	public boolean deleteDoctorPatient(DoctorPatient doctorPatient) throws SQLException{
		boolean success = false;
		connect();
		
		String sql = "DELETE FROM DOCTOR_PATIENT WHERE record_no=?";
		
		PreparedStatement ps = jdbcConnection.prepareStatement(sql);
		
		ps.setInt(1, doctorPatient.getRecord_no());
		
		success = ps.executeUpdate()>0;
		
		disconnect();
		return success;
	}
	
	
	public boolean updateDoctorPatient(DoctorPatient doctorPatient) throws SQLException {
		boolean success = false;

		String sql = "UPDATE DOCTOR_PATIENT SET doc_id=?,p_id=?,description=? WHERE record_no=?";
		connect();

		PreparedStatement ps = jdbcConnection.prepareStatement(sql);

		ps.setInt(1, doctorPatient.getDoc_id());
		ps.setInt(2, doctorPatient.getP_id());
		ps.setString(3, doctorPatient.getDescription());
		ps.setInt(4, doctorPatient.getRecord_no());

		success = ps.executeUpdate() > 0;

		ps.close();
		disconnect();

		return success;
	}
	
	public boolean insertDoctorPatient(DoctorPatient doctorPatient) throws SQLException {
		boolean success = false;

		String sql = "INSERT INTO DOCTOR_PATIENT(doc_id,p_id,description) VALUES(?,?,?)";
		connect();

		PreparedStatement ps = jdbcConnection.prepareStatement(sql);

		ps.setInt(1, doctorPatient.getDoc_id());
		ps.setInt(2, doctorPatient.getP_id());
		ps.setString(3, doctorPatient.getDescription());

		success = ps.executeUpdate() > 0;

		ps.close();
		disconnect();

		return success;
	}
	
	
}
