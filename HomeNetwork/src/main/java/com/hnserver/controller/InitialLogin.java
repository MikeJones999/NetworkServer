package com.hnserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String welcome(Model model) {
	System.out.println("**********************Calling home page ***************");
	
	model.addAttribute("message1", "Michael Jones MSc Computer Science Project");
	model.addAttribute("message2", "Home Network Server");
	
	return "startpage";
	}
	
	
}
