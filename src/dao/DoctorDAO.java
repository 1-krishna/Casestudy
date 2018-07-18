package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Doctor;


public class DoctorDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public DoctorDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
	
	public ArrayList<Doctor> listAllDoctors() throws SQLException{
		ArrayList<Doctor> listDoctor = new ArrayList<>();

		String sql = "SELECT * FROM DOCTOR";

		connect();

		Statement st = jdbcConnection.createStatement();

		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			int doc_id = rs.getInt("doc_id");
			String doc_name = rs.getString("doc_name");
			String doc_mobile = rs.getString("doc_mobile");
			String doc_specialization = rs.getString("doc_specialization");

			listDoctor.add(new Doctor(doc_id, doc_name, doc_mobile, doc_specialization));
		}

		rs.close();
		st.close();

		disconnect();

		return listDoctor;
	}
	
	
	public boolean addDoctor(Doctor doctor) throws SQLException{
		boolean success = false;
		connect();
		
		String sql = "INSERT INTO DOCTOR (doc_name,doc_mobile,doc_specialization) VALUES (?,?,?)";
		
		PreparedStatement ps = jdbcConnection.prepareStatement(sql);
		
		ps.setString(1, doctor.getDoc_name());
		ps.setString(2, doctor.getDoc_mobile());
		ps.setString(3, doctor.getDoc_specialization());
		
		success = ps.executeUpdate()>0;
		
		disconnect();
		return success;
	}
	
	public boolean deleteDoctor(Doctor doctor) throws SQLException{
		boolean success = false;
		connect();
		
		String sql = "DELETE FROM DOCTOR WHERE doc_id=?";
		
		PreparedStatement ps = jdbcConnection.prepareStatement(sql);
		
		ps.setInt(1, doctor.getDoc_id());
		
		success = ps.executeUpdate()>0;
		
		disconnect();
		return success;
	}
	
	
	public boolean updateDoctor(Doctor doctor) throws SQLException {
		boolean success = false;

		String sql = "UPDATE DOCTOR SET doc_name=?,doc_mobile=?,doc_specialization=? WHERE doc_id=?";
		connect();

		PreparedStatement ps = jdbcConnection.prepareStatement(sql);

		ps.setString(1, doctor.getDoc_name());
		ps.setString(2, doctor.getDoc_mobile());
		ps.setString(3, doctor.getDoc_specialization());
		ps.setInt(4, doctor.getDoc_id());

		success = ps.executeUpdate() > 0;

		ps.close();
		disconnect();

		return success;
	}
	
	public Doctor getDoctor(int doc_id) throws SQLException {
		Doctor doctor = null;
		String sql = "SELECT * FROM DOCTOR WHERE doc_id = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, doc_id);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			String doc_name = resultSet.getString("doc_name");
			String doc_mobile = resultSet.getString("doc_mobile");
			String doc_specialization = resultSet.getString("doc_specialization");

			doctor = new Doctor(doc_id, doc_name, doc_mobile, doc_specialization);
		}

		resultSet.close();
		statement.close();

		return doctor;
	}
}
