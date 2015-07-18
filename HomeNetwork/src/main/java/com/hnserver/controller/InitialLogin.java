package com.hnserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Initial login page - where the url is directed to the welcome/start page (startpage.jsp)
 * Here the user can be directed to the Administrator page or the User page. 
 * 
 * @author mikieJ
 *
 */


@Controller
public class InitialLogin {
	
	@RequestMapping("/")
	public String welcome(
			@RequestParam(required = false) String logout, Model model) 
	{
		System.out.println("**********************Calling home page ***************");
		
//		model.addAttribute("message1", "Michael Jones");
//		model.addAttribute("message2", "MSc Computer Science Project");
//		model.addAttribute("message3", "Home Network Server");
		
		//used for logout action - returns here indicating to user that they have been logged out
		String responseToAccess = "";
		if (logout != null) 
				{
					responseToAccess = "You have been logged out";
					model.addAttribute ("response", responseToAccess);
				} 
		
		
		return "startpage";
	}
	
	
}
