package authguard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Authenticate {
	
	public static boolean isAdmin(HttpServletRequest request){
		HttpSession session=request.getSession();
    	
    	String isAdmin = (String)session.getAttribute("admin");
    	
    	if(isAdmin != null && isAdmin.equals("true")){
    		return true;
    	}
    	
    	return false;
	}
	
	public static boolean isUser(HttpServletRequest request){
		HttpSession session=request.getSession();
    	
    	String isAdmin = (String)session.getAttribute("admin");
    	
    	if(isAdmin != null && isAdmin.equals("false")){
    		return true;
    	}
    	
    	return false;
	}
	
}
