package com.hnserver.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fileManager.SecurityChecker;
import UserPackage.User;
import UserPackage.UserDataObject;
import UserPackage.UserFileControl;

@RestController
public class RestfulController {

	
	  @Autowired
	    private UserDataObject dataObject;		
	

	    @RequestMapping(value = "/restfulGateway/{userName}/upload/{id}", method = RequestMethod.POST)
	    @ResponseBody
	    public String uploadPhoto(@RequestParam("file") MultipartFile file, @PathVariable("userName") String userName, @PathVariable("id") Integer id) throws IllegalStateException, IOException
	    {   
	    	

	     	String result = "false";
	         if (file != null)
	         {
	        	 System.out.println("***DEBUG*** connected - " + file.getOriginalFilename() + " found");
	             System.out.println("***DEBUG*** " + file.getOriginalFilename() + " has been sent");
	           
	             
	             String fileName = file.getOriginalFilename();
	             String saveLocation = "C:\\Users\\mikieJ\\Documents\\MSc_UserFolder\\" +  userName + "\\" + "private" + "\\";
	             file.transferTo(new File(saveLocation + fileName));
	             
	             if(UserFileControl.fileAlreadyexist(saveLocation, fileName))
	             {
	            	 //this does not include same file that already existed
	            	  result = "true";
	            	  System.out.println("***DEBUG*** file has been found in directory - uploaded correctly");
	             }
	             
	         }
	         else 
	         {
	             System.out.println("***DEBUG*** file is missing");
	         }
	    
	         return result;
	    }
	    
	    
		@RequestMapping(value = "restfulGateway/login", method = RequestMethod.GET, produces = "application/json")
		public @ResponseBody User findUser()
		{
			User userToReturn = null;
			 System.out.println("***DEBUG*** connected");
			 
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				String name = auth.getName();
				
				 System.out.println("***DEBUG*** user connected: " + name);
				 

		         if (name != null)
		         {
		        	 
		        	 userToReturn = dataObject.getuserByName(name);
		        	 if(userToReturn != null)
		        	 {
		        		 System.out.println("***DEBUG*** user " + name  + " is now logged in");
		        	 }
		        	 else
		        	 {
		        		 System.out.println("***DEBUG*** that user does not exist");
		        	 }

		         }
		         else 
		         {
		             System.out.println("***DEBUG*** no user found");
		           
		         }
			 
			 return userToReturn;
		}
	    
	
}
