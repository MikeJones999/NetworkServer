package com.hnserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import UserPackage.User;





@Controller
public class AdminController {

	@RequestMapping(value = "/adminpage/addUsers", method = RequestMethod.POST)
	public String addUser(ModelMap model, @ModelAttribute("users") User user, BindingResult res) 
	{	
	System.out.println("**********************Calling adduser page ***************");

		return "adduser";
	
	}

	@RequestMapping("/adminRedirect")
	public String redirectToAdmin() {	
	System.out.println("**********************Calling Admin redirect page ***************");

	return "redirect:adminpage";
	}
}
