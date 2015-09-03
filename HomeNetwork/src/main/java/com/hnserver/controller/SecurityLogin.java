package com.hnserver.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import UserPackage.User;
import UserPackage.UserDataObject;



@Controller
public class SecurityLogin {

	@Autowired
	private UserDataObject dataObject;	
	
	/**
	 * take user to user page - this will need to have be the specific user to secure all files
	 * @return
	 */
	@RequestMapping("/userpage")
	public String getUserPage() 
	{
		//Obtains the username - name in spring security's case - redirect using name to userpage/{username}		
		//http://www.mkyong.com/spring-security/get-current-logged-in-username-in-spring-security/
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String name = auth.getName(); //get logged in username
		
		System.out.println("calling profile page for user");
		return "redirect:/userpage/" + name;
	}

	/**
	 * takes user to admin page - this is a universal page for all admins with access
	 * @return
	 */
	@RequestMapping("/adminpage")
	public String getAdminPage() 
	{
		return "adminaccess";
	}
	
	//***Reference*** - assistance using the security element obtained from here
	//http://www.beingjavaguys.com/2014/05/spring-security-authentication-and.html?m=1 &
	//http://www.mkyong.com/spring-security/spring-security-form-login-example/
	@RequestMapping("/loginpage")
	public ModelAndView getLoginPage(
	@RequestParam(required = false) String accessfailed, String logout, String accessdenied, String locked) 
	{

			String responseToAccess = "";
			if (accessfailed != null) 
			{
				responseToAccess = "Username or Password incorrectly entered, please try again !";
				
				
				
				//here could add attempts login process		
				System.out.println("***DEBUG*** Failed attempt at login");
				
				
			} 
				else if (logout != null) 
				{
					responseToAccess = "You have been logged out";
				} 
					else if (accessdenied != null) 
					{
						responseToAccess = "Access denied";
					}
			
						else if (locked != null) 
						{
							responseToAccess = "Account is locked - contact Admin for assistance";
						}
			return new ModelAndView("loginaccessCSS", "response", responseToAccess);

	}
	
	
	/**
	 * this page takes user back to the login page (above) but with the added warning about the login error
	 * @return
	 */
	@RequestMapping("errorloginpage")
	public String ge403denied() 
	{

		
		return "redirect:loginpage?accessdenied";
	}
	
	
	/**
	 * This is the default gateway that decides on role what site gateway you use - user or admin
	 * This will therefore reset the number of incorrect logins
	 * @return
	 */
	@RequestMapping("/startpage")
	public String sendClientToCorrectGateway() 
	{
		
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String name = auth.getName();
	      String resultPage = "redirect:/userpage/" + name;
	      
	      User user = dataObject.getuserByName(name);
	      int securityCount = dataObject.getUserSecurityCount(user.getUserName());
	      
	      if (securityCount < 5)
	      {
	    	  //successful log in reset security count
	    	  if (securityCount != 0)
	    	  {
	    		  dataObject.resetSecurityCount(user); 
	    	  }
	    	  
		      if (user.getUserRole().equals("ROLE_ADMIN"))
		      {
		    	  System.out.println("***DEBUG*** Admin has logged in - default gateway");
		    	  resultPage = "adminaccess";
		      }
		      
		      System.out.println("***DEBUG*** User has logged in - default gateway");
			 return resultPage;
	      }
	      else
	      {
	    	 
	    	  //Account is locked - provide feedback to the JSP
	    	  System.out.println("***DEBUG*** ACCOUNT IS LOCKED - seek assistance from Admin");
	    	  return "redirect:loginpage?locked"; 
	      }
		
	}


}
