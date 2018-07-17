package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.AdminLogin;

public class AdminLoginDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public AdminLoginDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
	
	public boolean checkLoginDetails(AdminLogin login) throws SQLException{
		boolean success = false;
		connect();
		String sql = "SELECT * FROM ADMIN_LOGIN WHERE username=?";
		PreparedStatement ps = jdbcConnection.prepareStatement(sql);
		ps.setString(1, login.getUsername());
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.isBeforeFirst()){
			rs.next();
			if(rs.getString("password").equals(login.getPassword())){
				success = true;
			}
		}
		
		disconnect();
		return success;
	}
	
	public AdminLogin showByusername(String username) throws SQLException{
		connect();
		String sql = "SELECT * FROM ADMIN_LOGIN WHERE USERNAME=?";
		PreparedStatement ps = jdbcConnection.prepareStatement(sql);
		
		ps.setString(1, username);
		
		ResultSet rs = ps.executeQuery();
		
		AdminLogin adminLogin = null;
		while(rs.next()){
			adminLogin = new AdminLogin(rs.getString("username"), rs.getString("name"), rs.getString("password"));
		}
		
		
		disconnect();
		return adminLogin;
	}
}
