package com.hnserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class SecurityLogin {

	
	@RequestMapping("/userpage")
	public String getUserPage() 
	{
		return "useraccess";
	}

	@RequestMapping("/adminpage")
	public String getAdminPage() 
	{
		return "adminaccess";
	}
	
	//***Reference*** - assistance using the security element obtained from here
	//http://www.beingjavaguys.com/2014/05/spring-security-authentication-and.html?m=1
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
			return new ModelAndView("loginaccess", "response", responseToAccess);
		}
	}
	
	
	
	
	@RequestMapping("errorloginpage")
	public String ge403denied() 
	{
		return "redirect:loginpage?accessdenied";
	}
}
