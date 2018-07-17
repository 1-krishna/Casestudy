package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UserLogin;
import dao.UserLoginDAO;

/**
 * Servlet implementation class AddUser
 */
@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	
	private UserLoginDAO userLoginDAO;
	
	@Override
	public void init() throws ServletException {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		
        userLoginDAO = new UserLoginDAO(jdbcURL, jdbcUsername, jdbcPassword);
	}
	
    public AddUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>Add User</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
		request.getRequestDispatcher("navadmin.html").include(request, response);
		out.println("<div class='container'>");
		
		String username=request.getParameter("username");
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		
		
		UserLogin bean=new UserLogin(username, name, password);
		
		try {
			if(userLoginDAO.addUser(bean)){
				out.print("<h4>User<b> "+name+" </b>added successfully</h4>");
			}else{
				out.print("<h4>Some Error</h4>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			out.println("<h4>Username already registered</h4>");
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("adduserform.html").include(request, response);				
		out.println("</div>");
		//request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

}
