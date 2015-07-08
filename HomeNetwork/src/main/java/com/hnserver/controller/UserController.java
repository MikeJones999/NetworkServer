
package com.hnserver.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import UserPackage.User;
import UserPackage.UserDataObject;

@Controller
public class UserController {

	
	
@Autowired
private UserDataObject dataObject;

	
	 @RequestMapping(value = "/userpage/{userName}")
		public String returnUserPage(@PathVariable String userName, Map<String, Object> model)
		{
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	     String name = auth.getName(); 
		 
		 User temp = dataObject.getuserByName(userName);
			 
		    if (temp != null )
		    { 
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


	 
	 
	
}
