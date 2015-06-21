package com.hnserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class InitialTest {
	
	@RequestMapping("/")
	public String welcome(Model model) {
	System.out.println("**********************Calling home page ***************");
	
	model.addAttribute("message1", "Mike Jones Project");
	model.addAttribute("message2", "Home Network Server");
	return "startpage";
	}
	

	
}