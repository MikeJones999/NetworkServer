package com.hnserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import UserPackage.JdbcUserControl;
import UserPackage.User;
import UserPackage.UserDataObject;




@Controller
public class AdminController {
	
@Autowired
private UserDataObject dataObject;


	@RequestMapping(value = "/adminpage/usersAdded", method = RequestMethod.POST)
	public String addUser(ModelMap model, @ModelAttribute("users") User user,
			BindingResult res) {
		System.out
				.println("**********************Calling adduser page ***************");
		String returnedUserName = user.getUserName();
		String returnedUserPassword = user.getPassword();
		String message = "";
		if (returnedUserName.equals(""))
		{
			System.out.println("Invalid username - add a User name");
			model.addAttribute("message", "Invalid username - add a User name");
			return "addusers";
		}
		else if (returnedUserPassword.equals("")|| returnedUserPassword.length() < 5) 
		{
			// setup password standards - check with spring
			System.out.println("No password chosen or too small");

			model.addAttribute("message", "No password chosen or too small");
			return "addusers";
		}
		else 
		{
			// correctly entered name - add user
			System.out.println("User added");
			System.out.println("username: " + user.getUserName()
					+ ", password: " + user.getPassword());

			// this needs to be in a object of its own - move laters once tested
			ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
			//and this need to be closed!!!!!!!!!
			
			
			JdbcUserControl userObject = (JdbcUserControl) context.getBean("userObject");
			//User user1 = new User();
			//user.setUserName(returnedUserName);
			//user.setPassword(returnedUserPassword);
			userObject.insert(user);
		}

		return "redirect:addUsers";
	}

	@RequestMapping("/adminpage/addUsers")
	public ModelAndView showAddUserPage() {

		// needs command - apparently according to spring docs requires command
		return new ModelAndView("adduser", "command", new User());
	}

	@RequestMapping("/adminRedirect")
	public String redirectToAdmin() {
		System.out.println("**********************Calling Admin redirect page ***************");

		return "redirect:adminpage";
	}
	
	
	
	@RequestMapping("/adminpage/allusers")
	public String list(Model model) 
	{
		
		model.addAttribute("allusers", dataObject.getAllUsers());
	
		return "allusers";
		
	}
	
	
	
	
	@RequestMapping(value = "/adminpage/deleteUser/{userName}")
	public String deleteUser(@PathVariable String userName, Model model)
	{
		 ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
	      //ApplicationContext context = new ClassPathXmlApplicationContext("/dispatcherservlet-servlet.xml");
	      
	     JdbcUserControl userObject = (JdbcUserControl)context.getBean("userObject");
		
	     User user = userObject.getuserByName(userName);
	     
	     
	     if (user != null)
	     {
	    	 System.out.println("found user: " + user.getUserName());
			System.out.println("hurrah");
			System.out.println("returned user to delete: " + userName);
			model.addAttribute("message", userName);
			//could find user here and send him through instead of new user();
		//	return new ModelAndView("deleteuser", "command", user.getUserName());
			
			//model.addAttribute("allusers", dataObject.getAllUsers());
			return "deleteuser";
	     }
	     else
	     {
	    	 
	    	 System.out.println("*******Error: " +  userName + " does not exist**************");
	       	 model.addAttribute("warnimgMessage", userName);
	    	 return "unknownuser";
	     }
	}
	
	
	

	
}
