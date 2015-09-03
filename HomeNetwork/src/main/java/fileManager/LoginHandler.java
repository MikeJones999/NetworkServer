package fileManager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import UserPackage.User;
import UserPackage.UserDataObject;

public class LoginHandler  implements AuthenticationFailureHandler
{
	
	@Autowired
	private UserDataObject dataObject;		
	
	/**
	 * This takes in the url request with the posted data from the dispatcher servlet (passed by the login form)
	 * Identified the username provided and then once established as legitimate - increments their security count by 1
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException ae) throws IOException, ServletException 
	{
	            UsernamePasswordAuthenticationToken user =(UsernamePasswordAuthenticationToken)ae.getAuthentication();
	 
	         // user contains required data
	            String name = user.getName();
	          System.out.println("***DEBUG*** failed name = " + user.getName());
	          
	          //handle authorised user incorrect password attempts
	          boolean exists = dataObject.checkUserExists(name);
	          if (exists)
	          {
	        	  User temp = dataObject.getuserByName(name);
	        	  int count = dataObject.getUserSecurityCount(name);
	        	  dataObject.incrementSecurityCount(temp, count);
	          }
	          
	          //redirects back to the loginpage controller to handle the view
	          response.sendRedirect("loginpage?accessfailed");
	      
	}
	
	
}
