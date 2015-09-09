package com.hnserver.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



import fileManager.SecurityChecker;
import UserPackage.JdbcUserControl;
import UserPackage.User;
import UserPackage.UserDataObject;




@Controller
public class AdminController {
	
@Autowired
private UserDataObject dataObject;


	/**
	 * Add user page. Once user has been entered into the view, 
	 * this method checks the validity of the user details
	 * @param model
	 * @param user
	 * @param res
	 * @return String view - returns same page (adduser.jsp) with feedback
	 */
	@SuppressWarnings("resource")
	@RequestMapping(value = "/adminpage/usersAdded", method = RequestMethod.POST)
	public String addUser(ModelMap model, @ModelAttribute("user") User user, BindingResult res) {
		
		//Added the security element of BCryptPasswordEncoder for password protection
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodeduserPassword = passwordEncoder.encode(user.getPassword());
		
		
		String returnedUserName = user.getUserName();
		String returnedUserPassword = user.getPassword();
		String returnedUserRole = user.getUserRole();
		user.setPassword(encodeduserPassword);
		
		if (returnedUserName.equals(""))
		{
			System.out.println("Invalid username - add a User name");
			model.addAttribute("message", "Invalid username - add a User name");
			return "adduser";
		}
		else if (returnedUserPassword.equals("")|| returnedUserPassword.length() < 5) 
		{
			// setup password standards - check with spring
			System.out.println("***DEBUG*** No password chosen or too small");

			model.addAttribute("message", "No password chosen or too small");
			return "adduser";
		}
		else 
		{		
			String name = user.getUserName();	
				
		
			ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
			

			//need to ensure there are not two users with same name 
			boolean result = dataObject.checkUserExists(name);
			//user does not exist
			if(!result)
			{				
			
				JdbcUserControl userObject = (JdbcUserControl) context.getBean("userObject");
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
	
					model.addAttribute("message", "UserName Already Exists - please try again");
					return "adduser";
				}
		}

	}


	
	/**
	 * Used to populate the list option for Role
	 * @return List<String> possible roles
	 */
	ModelAndView mav = null;
	@ModelAttribute("roleOptions")
	public List<String> getRoles()
	{
		List<String> roleOptions = new ArrayList<String>();
		
		//need to his these string literals
		roleOptions.add("ROLE_USER");
		roleOptions.add("ROLE_ADMIN");
	
		return roleOptions;
	}
	
	/**
	 * Move to the show Add User page
	 * @param model
	 * @return String view adduser.jsp
	 */
	@RequestMapping("/adminpage/addUsers")
	public String showAddUserPage(Map<String, User> model) {

		User user = new User();
		model.put("user", user);
		// needs command - apparently according to spring docs requires command
		return "adduser";
	}


	
	
	/**
	 * Redirection to admin page
	 * @return String redirects to adminpage controller
	 */
	@RequestMapping("/adminRedirect")
	public String redirectToAdmin() 
	{
		return "redirect:adminpage";
	}
	
	
	/**
	 * Display page with all users
	 * @param model
	 * @return String view allusers.jsp
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
	 * @return String redirects to allusers controller
	 */
    @RequestMapping(value = "/adminpage/deleteuserconfirm/{userName}")
	public String deletUserConfirmation(@PathVariable String userName, Model model)
	{
		System.out.println("***DEBUG*** deletion of " + userName);
	      User temp = dataObject.getuserByName(userName);
	      
			String name = temp.getUserName();
			String userRole = temp.getUserRole();
			
			
			System.out.println("****DEBUG**** userName before deletion = " + name);	
			System.out.println("****DEBUG**** userRole before deletion = " + userRole);	
	      
	      dataObject.delete(temp);
	      
		return "redirect:/adminpage/allusers";
	}


	
	/**
	 * Display page of the user that has been selected for deletion
	 * @param userName
	 * @param model
	 * @return String view unknownuser.jsp
	 */
	@RequestMapping(value = "/adminpage/deleteUser/{userName}")
	public String deleteUser(@PathVariable String userName, Model model)
	{
		 ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");     
	     JdbcUserControl userObject = (JdbcUserControl)context.getBean("userObject");
		
	     User user = userObject.getuserByName(userName);
	     String role = userObject.getUserRole(userName);
	     
	     if (user != null & role != "unknown")
	     {

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
			
	
			//need to have this to close the connection in principle
			((ClassPathXmlApplicationContext)context).close();
			return "deleteuser";
	     }
	     else
	     {
	    	 
	       	 model.addAttribute("warnimgMessage", userName);
	       	 
	     	//need to have this to close the connection in principle
			((ClassPathXmlApplicationContext)context).close();
			
	    	 return "unknownuser";
	     }
	}
	
	/**
	 * Resets Users password
	 * @param userName
	 * @param model
	 * @return String View alluser.jsp
	 */
	@RequestMapping(value ="/adminpage/passwordReset/{userName}", method = RequestMethod.GET)
	public String resetUserPassword(@PathVariable ("userName") String userName, Model model)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String adminName = auth.getName(); 		
		User temp = dataObject.getuserByName(adminName);
		
		
		if(temp != null || SecurityChecker.isAdmin(temp))
		{	
			User user = dataObject.getuserByName(userName);
			if (user != null)
			{
				String standardPassword = userName + "@Server123";
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String encodeduserPassword = passwordEncoder.encode(standardPassword);
				user.setPassword(encodeduserPassword);
				dataObject.update(user);
				dataObject.resetSecurityCount(user); 
				model.addAttribute("passwordResetConfirmation", "password Reset to:- " + standardPassword);
				model.addAttribute("passwordWarning", "Ensure the user is provided with this new password asap and that they change it themselves asap!");
				return "adminresetuserpass";
			}
		}
		
		
		model.addAttribute("passwordResetConfirmation", "password Reset to " + userName + "@Server123");
		return "allusers";
		
		
	}

	
}
