package com.hnserver.controller;





import hello.Greeting;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;










import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    

}