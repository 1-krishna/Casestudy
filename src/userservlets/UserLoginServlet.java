package userservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import beans.UserLogin;
import dao.UserLoginDAO;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
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
	
    public UserLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.println("<head>");
		out.println("<title>User Section</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		
    	
    	HttpSession session=request.getSession();
    	
    	String username = (String)session.getAttribute("username");
    	request.getRequestDispatcher("navuser.html").include(request, response);
		
		try {
			out.println("<h2 style='text-align:center'>Welcome<b> Mr. "+userLoginDAO.showByusername(username).getName()+"</b></h2>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println("</body></html>");
		out.close();
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
		out.println("<title>User Section</title>");
		out.println("<link rel='stylesheet' href='bootstrap.min.css'/>");
		out.println("</head>");
		out.println("<body>");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		try {
			if(userLoginDAO.checkLoginDetails(new UserLogin(username, password))){
				HttpSession session=request.getSession();
				session.setAttribute("admin","false");
				session.setAttribute("username", username);
				
				request.getRequestDispatcher("navuser.html").include(request, response);
				
				out.println("<h2 style='text-align:center'>Welcome<b> Mr. "+userLoginDAO.showByusername(username).getName()+"</b></h2>");
				
			}else{
				request.getRequestDispatcher("navindex.html").include(request, response);
				out.println("<div class='container'>");
				out.println("<h2>Wrong Credentials</h2>");
				request.getRequestDispatcher("userloginform.html").include(request, response);
				out.println("</div>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//request.getRequestDispatcher("footer.html").include(request, response);
		out.println("</body></html>");
		out.close();
	}

}
