package com.hnserver.controller;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.View;

import UserPackage.JdbcUserControl;
import UserPackage.User;
import UserPackage.UserDataObject;




@Controller
public class AdminController {
	
@Autowired
private UserDataObject dataObject;


	@SuppressWarnings("resource")
	@RequestMapping(value = "/adminpage/usersAdded", method = RequestMethod.POST)
	public String addUser(ModelMap model, @ModelAttribute("user") User user, BindingResult res) {
		System.out
				.println("**********************Calling adduser page ***************");
		String returnedUserName = user.getUserName();
		String returnedUserPassword = user.getPassword();
		String returnedUserRole = user.getUserRole();
		System.out.println("found use role: " + returnedUserRole);
		
		//String message = "";
		
		if (returnedUserName.equals(""))
		{
			System.out.println("Invalid username - add a User name");
			model.addAttribute("message", "Invalid username - add a User name");
			return "adduser";
		}
		else if (returnedUserPassword.equals("")|| returnedUserPassword.length() < 5) 
		{
			// setup password standards - check with spring
			System.out.println("No password chosen or too small");

			model.addAttribute("message", "No password chosen or too small");
			return "adduser";
		}
		else 
		{		
			String name = user.getUserName();	
			/* DEBUG 
		
			// correctly entered name - add user
			System.out.println("User added");
			System.out.println("username: " + name
					+ ", password: " + user.getPassword());

			*/
			
			// this needs to be in a object of its own - move later once tested
			ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
			

			//need to ensure there are not two users with same name 
			boolean result = dataObject.checkUserExists(name);
			//user does not exist
			if(!result)
			{				
			
				JdbcUserControl userObject = (JdbcUserControl) context.getBean("userObject");
				//User user1 = new User();
				//user.setUserName(returnedUserName);
				//user.setPassword(returnedUserPassword);
				userObject.insert(user);
				
				
				//this message is not showing up
				String str = " " + name.toUpperCase() + " has successfully been added to the user group";
				model.addAttribute("useradded", str);
				
				//need to have this to close the connection in principle
				((ClassPathXmlApplicationContext)context).close();
				//return "redirect:addUsers";
				return "adduser";
			}
			else
				{
					System.out.println("UserName Already Exists - please try again");
	
					model.addAttribute("message", "UserName Already Exists - please try again");
					return "adduser";
				}
		}

	}

//	@RequestMapping("/adminpage/addUsers")
//	public ModelAndView showAddUserPage() {
//
//		// needs command - apparently according to spring docs requires command
//		return new ModelAndView("adduser", "command", new User());
//	}
	
	/**
	 * Used to populate the list option for Role
	 * 
	 */
	ModelAndView mav = null;
	@ModelAttribute("roleOptions")
	public List<String> getRoles()
	{
		List<String> roleOptions = new ArrayList<String>();
		
		//need to his these string literals
		roleOptions.add("ROLE_ADMIN");
		roleOptions.add("ROLE_USER");
		return roleOptions;
	}
	
	
	@RequestMapping("/adminpage/addUsers")
	public String showAddUserPage(Map<String, User> model) {

		User user = new User();
		model.put("user", user);
		// needs command - apparently according to spring docs requires command
		return "adduser";
	}


	
	
	
	@RequestMapping("/adminRedirect")
	public String redirectToAdmin() {
		System.out.println("**********************Calling Admin redirect page ***************");

		return "redirect:adminpage";
	}
	
	
	/**
	 * display page with all users
	 * @param model
	 * @return
	 */
	@RequestMapping("/adminpage/allusers")
	public String listAllUsers(Model model) 
	{
		List<User> allAdminsList = dataObject.getAllAdmins();		
		List<User> allNonAdminsList = dataObject.getAllNonAdmins();	
		
		model.addAttribute("allAdmins", allAdminsList);
		model.addAttribute("allusers", allNonAdminsList);
		return "allusers";		
	}
	

	/**
	 * Deletes the user and redirects to alluser page - deleted user should now no longer be visible
	 * @param userName
	 * @param model
	 * @return
	 */
    @RequestMapping(value = "/adminpage/deleteuserconfirm/{userName}")
	public String deletUserConfirmation(@PathVariable String userName, Model model)
	{
		System.out.println("***DEBUG*** deletion of " + userName);
	      User temp = dataObject.getuserByName(userName);
	      dataObject.delete(temp);
	      
		return "redirect:/adminpage/allusers";
	}


	
	/**
	 * Display page of user that has been selected for deletion
	 * @param userName
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/adminpage/deleteUser/{userName}")
	public String deleteUser(@PathVariable String userName, Model model)
	{
		 ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
	      //ApplicationContext context = new ClassPathXmlApplicationContext("/dispatcherservlet-servlet.xml");
	      
	     JdbcUserControl userObject = (JdbcUserControl)context.getBean("userObject");
		
	     User user = userObject.getuserByName(userName);
	     String role = userObject.getUserRole(userName);
	     
	     if (user != null & role != "unknown")
	     {
	    	System.out.println("found user: " + user.getUserName());
			System.out.println("hurrah");
			System.out.println("returned user to delete: " + userName);		
			model.addAttribute("message", userName);
			
			if (role.equals("ROLE_ADMIN"))
			{
				if(dataObject.getAllAdminsSQL().size() > 1)
				{
					model.addAttribute("message1", "Warning - This user has the Role of Administrator");
					((ClassPathXmlApplicationContext)context).close();
					return "deleteuser";
				}
				else
				{
					model.addAttribute("message", "Warning - There must be at least one Administrator on the system"
							+ " - therefore Cannot delete " + userName);
					((ClassPathXmlApplicationContext)context).close();
					return "errordeleteadminuser";
				}
			}
			
			//could find user here and send him through instead of new user();
		//return new ModelAndView("deleteuser", "command", user.getUserName());
			
			//model.addAttribute("allusers", dataObject.getAllUsers());
			//need to have this to close the connection in principle
			((ClassPathXmlApplicationContext)context).close();
			return "deleteuser";
	     }
	     else
	     {
	    	 
	    	 System.out.println("*******Error: " +  userName + " does not exist**************");
	       	 model.addAttribute("warnimgMessage", userName);
	       	 
	     	//need to have this to close the connection in principle
			((ClassPathXmlApplicationContext)context).close();
			
	    	 return "unknownuser";
	     }
	}
	
	
	

	
}
