package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.UserLogin;

public class UserLoginDAO {
	
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public UserLoginDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
	
	public boolean checkLoginDetails(UserLogin login) throws SQLException{
		boolean success = false;
		connect();
		String sql = "SELECT * FROM USER_LOGIN WHERE username=?";
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
	
	public ArrayList<UserLogin> listAllUsers() throws SQLException{
		ArrayList<UserLogin> listUsers = new ArrayList<>();

		String sql = "SELECT * FROM USER_LOGIN";

		connect();

		Statement st = jdbcConnection.createStatement();

		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			String username = rs.getString("username");
			String name = rs.getString("name");
			String password = rs.getString("password");

			listUsers.add(new UserLogin(username, name, password));
		}

		rs.close();
		st.close();

		disconnect();

		return listUsers;
	}
	
	public boolean addUser(UserLogin userLogin) throws SQLException{
		boolean success = false;
		connect();
		
		String sql = "INSERT INTO USER_LOGIN VALUES(?,?,?)";
		
		PreparedStatement ps = jdbcConnection.prepareStatement(sql);
		
		ps.setString(1, userLogin.getUsername());
		ps.setString(2, userLogin.getName());
		ps.setString(3, userLogin.getPassword());
		
		success = ps.executeUpdate()>0;
		disconnect();
		return success;
	}
	
	public boolean deleteUser(UserLogin userLogin) throws SQLException{
		boolean success = false;
		connect();
		
		String sql = "DELETE FROM USER_LOGIN WHERE username=?";
		
		PreparedStatement ps = jdbcConnection.prepareStatement(sql);
		
		ps.setString(1, userLogin.getUsername());
		
		success = ps.executeUpdate()>0;
		
		disconnect();
		return success;
	}
	
	
	public boolean updateUser(UserLogin userLogin) throws SQLException {
		boolean success = false;

		String sql = "UPDATE USER_LOGIN SET name=?,password=? WHERE username=?";
		connect();

		PreparedStatement ps = jdbcConnection.prepareStatement(sql);

		ps.setString(1, userLogin.getName());
		ps.setString(2, userLogin.getPassword());
		ps.setString(3, userLogin.getUsername());

		success = ps.executeUpdate() > 0;

		ps.close();
		disconnect();

		return success;
	}
	
	public UserLogin showByusername(String username) throws SQLException{
		
		connect();
		
		String sql = "SELECT * FROM USER_LOGIN WHERE username=?";
		
		PreparedStatement ps = jdbcConnection.prepareStatement(sql);
		
		ps.setString(1, username);
		
		ResultSet rs = ps.executeQuery();
		
		UserLogin result = null;
		while(rs.next()){
			result = new UserLogin(rs.getString("username"), rs.getString("name"), rs.getString("password"));
		}
		
		disconnect();
		return result;
		
	}

}
