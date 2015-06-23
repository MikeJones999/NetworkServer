package com.hnserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class SecurityLogin {

	
	@RequestMapping("userpage")
	public String getUserPage() 
	{
		return "useraccess";
	}

	@RequestMapping("adminpage")
	public String getAdminPage() 
	{
		return "adminaccess";
	}
	
	
	@RequestMapping("loginpage")
	public String getLoginPage() 
	{
		return "loginaccess";
	}
}
