package com.hnserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	@RequestParam(required = false) String accessfailed, String logout, String accessdenied) 
	{
		{
			String responseToAccess = "";
			if (accessfailed != null) 
			{
				responseToAccess = "Username or Password incorrectly entered, please try again !";
			} 
				else if (logout != null) 
				{
					responseToAccess = "You have been logged out";
				} 
					else if (accessdenied != null) 
					{
						responseToAccess = "Access denied";
					}
			//return new ModelAndView("loginaccess", "response", responseToAccess);
			return new ModelAndView("loginaccessCSS", "response", responseToAccess);
		}
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
}
