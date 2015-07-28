package com.hnserver.controller;





import hello.Greeting;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;




















import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import UserPackage.User;
import UserPackage.UserDataObject;


/*
@RestController
public class InitialTest {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting (counter.incrementAndGet(),
                            String.format(template, name));
    }*/



@RestController
public class InitialTest {

//    private static final String template = "Hello, %s!";
//    private final AtomicLong counter = new AtomicLong();
    
    
    @Autowired
    private UserDataObject dataObject;	

    //old method of logging in - now use the spring security 
    /*
    @RequestMapping("/restfulGateway")
    public User userObject(@RequestParam(value="name", defaultValue="mj") String name, @RequestParam(value="password") String password) //, @RequestParam(value="password", defaultValue="mj@123") String password) 
    {
    	System.out.println("***DEBUG*** Name provided: " + name + ", Password Provided: " + password);
    	User returnedUser = dataObject.getuserByName(name);
    
 
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
  
		//this uses the encryption method matches - to check the unhashed password provided by the app and the one stored (hashed) on the DB
		if (encoder.matches(password, returnedUser.getPassword())) 
		{
			System.out.println("***DEBUG*** password and name check out");
            return returnedUser;
		}
		else
		{
			System.out.println("***DEBUG*** password and/or name check not correct");
		    return null;
		}
    }
    */
    
    
//    @RequestMapping(value= "/restfulGateway/mj/upload", method = RequestMethod.POST)
//    public void userObject(@RequestParam(value="p0") String p0 , @RequestParam(value= "receivedFile") MultipartFile receivedFile) 
//    {
//        System.out.println("***DEBUG*** connected");
//    	String result = "";
//        if (receivedFile != null)
//        {
//            System.out.println("***DEBUG*** file has been sent");
//            result = "sent";
//        }
//        else 
//        {
//            System.out.println("***DEBUG*** file is missing");
//            result = "not Sent";
//        }
//        
//       // return result;
//     
//    }
    
    @RequestMapping(value = "/restfulGateway/mj/upload/{id}", method = RequestMethod.POST)
    @ResponseBody
    public void uploadPhoto(@RequestParam("file") MultipartFile file, @PathVariable("id") Integer id) throws IllegalStateException, IOException {

///  do something
        System.out.println("***DEBUG*** connected");
     	String result = "";
         if (file != null)
         {
             System.out.println("***DEBUG*** file has been sent");
             result = "sent";
             
             String fileName = file.getOriginalFilename();
             String saveLocation = "C:\\Users\\mikieJ\\Documents\\MSc_UserFolder\\" +  "mj" + "\\" + "public" + "\\";
             file.transferTo(new File(saveLocation + fileName));
         }
         else 
         {
             System.out.println("***DEBUG*** file is missing");
             result = "not Sent";
         }
        //return RestUtil.getJsonSHttptatus(HttpStatus.NOT_ACCEPTABLE);
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
	        	 
	             System.out.println("***DEBUG*** file has been sent");

	         }
	         else 
	         {
	             System.out.println("***DEBUG*** file is missing");
	           
	         }
		 
		 return userToReturn;
	}
    

}