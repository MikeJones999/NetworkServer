
package com.hnserver.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import UserPackage.User;
import UserPackage.UserDataObject;

@Controller
public class UserController {

	
	
@Autowired
private UserDataObject dataObject;

	
	 @RequestMapping(value = "/userpage/{userName}")
		public String returnUserPage(@PathVariable String userName, Map<String, Object> model)
		{
		 
		 //obtain the user that logged into the system via the spring security login screen
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	     String name = auth.getName(); 
		 
		 User temp = dataObject.getuserByName(userName);
			 
		    if (temp != null )
		    { 
		    	
		    	//ensure username provided is the same as the user who logged in
		    	if (temp.getUserName().equals(name))
		    	{	
				      String role = temp.getUserRole();
				      if (role != "ROLE_ADMIN")
				      {
				    	  System.out.println("***DEBUG*** found user " + userName);
				    	  model.put("user", temp);
				    	  return "userprofilepage";
				      }
				      else
				      {
				    	  System.out.println("***DEBUG*** User " + userName + "is not a user");
				    	  return "redirect:/";
				      }
		    	}
		    	else
		    	{
		    		//trying to access someone else's account once legitimately logged in as user.
		    		  System.out.println("***DEBUG*** Illegal access attemp by " + userName + ". They tried to access account " + name);
		    		  
		    		  //need to log them out somehow using spring security logout
		    		  return "redirect:/";
		    	}
		    }
		    else
		    {
		    	  System.out.println("***DEBUG*** Did not find user " + userName);
		    	  return "redirect:/";
		    }
						
		}

	 
	 @RequestMapping(value = "/userpage/redirect")
		public String redirectToUserPage(@PathVariable String userName, Map<String, Object> model)
		{
			 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		     String name = auth.getName(); 
		     User temp = dataObject.getuserByName(userName);
		     model.put("user", temp);
	   	  	 return "userprofilepage";
		}
	 
	/*
	 @RequestMapping(value = " /userpage/{userName}/editprofile")
		public String editUserPage(@PathVariable String userName, Map<String, Object> model)
		{
		 	 User temp = dataObject.getuserByName(userName);
	   	  	 return "useredit";
		}
	 */
	 
	 //get method to get the user details to populate form
	 @RequestMapping(value = "/userpage/{userName}/editprofile", method = RequestMethod.GET)
		public String editUserPage(@PathVariable("userName") String userName, Map<String, Object> model)
		{
		 	 User temp = dataObject.getuserByName(userName);
		 	 System.out.println("***DEBUG*** found editprofile - " + temp.getUserName());
		 	 model.put("user", temp);
		 	 
	   	  	 return "useredit";
		}
	 
	 @RequestMapping(value = "/userpage/{userName}", method = RequestMethod.POST)
		public String updateUserPage(@ModelAttribute("user") User user, BindingResult res)
		{
		 	if(res.hasErrors())
		 	{
		 		System.out.println("***DEBUG*** errors found in updating user profile");
		 		return "useredit";
		 	}
		 	//dataObject.update(user);
		 	System.out.println("Update user " + user.getUserName());
		  	return "redirect:/userpage/" + user.getUserName();
		}
	 
	 
	 
}