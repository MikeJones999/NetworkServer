package com.hnserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import UserPackage.User;





@Controller
public class AdminController {

	@RequestMapping(value = "/adminpage/usersAdded", method = RequestMethod.POST)
	public String addUser(ModelMap model, @ModelAttribute("users") User user, BindingResult res) 
	{	
	System.out.println("**********************Calling adduser page ***************");
	String returnedUserName = user.getUserName();
	String returnedUserPassword = user.getPassword();
	String message = "";
		if (returnedUserName.equals(""))
		{
			System.out.println("Invalid username - add a User name");
			model.addAttribute("message","Invalid username - add a User name");
			  return "addusers";
		}
		else if (returnedUserPassword.equals("") || returnedUserPassword.length() < 5)
		{
			//setup password standards - check with spring
			System.out.println("No password chosen or too small");
			
			model.addAttribute("message", "No password chosen or too small");
			  return "addusers";
		}
		else
		{
			System.out.println("User added");
				System.out.println("username: " + user.getUserName() + ", password: " + user.getPassword());
			
		}
		
	   return "redirect:addUsers";
	}

	   @RequestMapping("/adminpage/addUsers")
	    public ModelAndView showAddUserPage() {
	         
	    	//needs command - 
	        return new ModelAndView("adduser", "command", new User());
	    }
	
	
	
	@RequestMapping("/adminRedirect")
	public String redirectToAdmin() {	
	System.out.println("**********************Calling Admin redirect page ***************");

	return "redirect:adminpage";
	}
}
