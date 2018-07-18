package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Patient;


public class PatientDAO {
	
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public PatientDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
	
	public ArrayList<Patient> listAllPatients() throws SQLException{
		ArrayList<Patient> listPatient = new ArrayList<>();

		String sql = "SELECT * FROM PATIENT";

		connect();

		Statement st = jdbcConnection.createStatement();

		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			int p_id = rs.getInt("p_id");
			String p_name = rs.getString("p_name");
			String p_mobile = rs.getString("p_mobile");
			int p_age = rs.getInt("p_age");
			String p_address = rs.getString("p_age");
			String p_gender = rs.getString("p_gender");

			listPatient.add(new Patient(p_id, p_age, p_name, p_address, p_gender, p_mobile));
		}

		rs.close();
		st.close();

		disconnect();

		return listPatient;
	}
	
	public boolean addPatient(Patient patient) throws SQLException{
		boolean success = false;
		connect();
		
		String sql = "INSERT INTO PATIENT (p_name,p_mobile,p_age,p_address,p_gender) VALUES (?,?,?,?,?)";
		
		PreparedStatement ps = jdbcConnection.prepareStatement(sql);
		
		ps.setString(1, patient.getP_name());
		ps.setString(2, patient.getP_mobile());
		ps.setInt(3, patient.getP_age());
		ps.setString(4, patient.getP_address());
		ps.setString(5, patient.getP_gender());
		
		
		success = ps.executeUpdate()>0;
		
		disconnect();
		return success;
	}
	
	public boolean deletePatient(Patient patient) throws SQLException{
		boolean success = false;
		connect();
		
		String sql = "DELETE FROM PATIENT WHERE p_id=?";
		
		PreparedStatement ps = jdbcConnection.prepareStatement(sql);
		
		ps.setInt(1, patient.getP_id());
		
		success = ps.executeUpdate()>0;
		
		disconnect();
		return success;
	}
	
	
	public boolean updatePatient(Patient patient) throws SQLException {
		boolean success = false;

		String sql = "UPDATE PATIENT SET p_name=?,p_mobile=?,p_age=?,p_address=?,p_gender=? WHERE p_id=?";
		connect();

		PreparedStatement ps = jdbcConnection.prepareStatement(sql);

		ps.setString(1, patient.getP_name());
		ps.setString(2, patient.getP_mobile());
		ps.setInt(3, patient.getP_age());
		ps.setString(4, patient.getP_address());
		ps.setString(5, patient.getP_gender());
		ps.setInt(6, patient.getP_id());

		success = ps.executeUpdate() > 0;

		ps.close();
		disconnect();

		return success;
	}
	
	public Patient getPatient(int p_id) throws SQLException {
		Patient patient = null;
		String sql = "SELECT * FROM PATIENT WHERE p_id = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, p_id);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			String p_name = resultSet.getString("p_name");
			String p_mobile = resultSet.getString("p_mobile");
			int p_age = resultSet.getInt("p_age");
			String p_address = resultSet.getString("p_address");
			String p_gender = resultSet.getString("p_gender");
			
			patient = new Patient(p_id, p_age, p_name, p_address, p_gender, p_mobile);
		}

		resultSet.close();
		statement.close();

		return patient;
	}

}
