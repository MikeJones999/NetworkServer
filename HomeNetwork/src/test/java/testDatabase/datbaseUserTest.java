package testDatabase;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import UserPackage.JdbcUserControl;
import UserPackage.User;
import UserPackage.UserDataObject;

public class datbaseUserTest {

	@Autowired
	UserDataObject dataObject;
	
	JdbcUserControl userObject;
	User user;
	
	
	@Before
	public void before()
	{
		//create User
		user = new User();
		user.setId(100);
		user.setUserName("Tester");
		user.setPassword("examplePassword");
		user.setUserRole("ROLE_USER");
		
		
		   ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
     
		   userObject = (JdbcUserControl)context.getBean("userObject");
	}
	
	//basic user implementation
	@Test
	public void UserNameTest() 
	{	
		boolean result = user.getUserName().equals("John");	
		assertTrue(result);		
	}	
	
	@Test
	public void UserPasswordTest() 
	{	
		boolean result = user.getPassword().equals("examplePassword");	
		assertTrue(result);		
	}
	
	@Test
	public void UserRoleTest() 
	{	
		boolean result = user.getUserRole().equals("ROLE_USER");	
		assertTrue(result);		
	}
	
	@Test
	public void UserIdTest() 
	{	
		boolean result = user.getId() == 100;	
		assertTrue(result);		
	}
	
	@Test
	public void userTest()
	{
		assertNotNull(user);
	}
	
	//changing details of user
	@Test
	public void UserNameChangeTest() 
	{			
		user.setUserName("changed");
		boolean result = user.getUserName().equals("Tester");	
		assertFalse(result);		
	}	
	
	@Test
	public void UserPasswordChangeTest() 
	{			
		user.setPassword("passwordchanged");
		boolean result = user.getPassword().equals("examplePassword");	
		assertFalse(result);		
	}	
	
	@Test
	public void UserRoleChangeTest() 
	{			
		user.setUserRole("ROLE_ADMIN");
		boolean result = user.getUserRole().equals("ROLE_USER");	
		assertFalse(result);		
	}
	
	//testing database connection and user creation
	@Test
	public void getAdminFromDatabaseReturnRole() 
	{			
		//get mikiej admin role from server
		user = userObject.getuserByName("mikiej");		
		boolean result = user.getUserRole().equals("ROLE_ADMIN");	
		assertTrue(result);		
	}
	
	@Test
	public void getAdminFromDatabaseReturnName() 
	{			
		//get mikiej admin name from server
		user = userObject.getuserByName("mikiej");		
		boolean result = user.getUserName().equals("mikiej");	
		assertTrue(result);		
	}
	
	@Test
	public void getAdminFromDatabaseReturnPassword() 
	{			
		String newHashedPW = null;
		for (int i = 0; i < 10; i++)
		{
			String unhashedPassword = "abertawe";
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			newHashedPW = encoder.encode(unhashedPassword);
	 		System.out.println("Password Encoded:- " + newHashedPW);			
		}
		
		//get mikiej admin passsword from server
		user = userObject.getuserByName("mikiej");		
		boolean result = user.getPassword().equals(newHashedPW);	
		assertTrue(result);		
	}
	

	@Test
	public void sendUserToDatabaseReturnName() 
	{			
		//create a new user to send to database
		user = new User();
		user.setId(100);
		user.setUserName("TestCase");
		user.setPassword(encryptPassword("TestPassword"));
		user.setUserRole("ROLE_USER");
		userObject.insert(user);	
		
		User temp = userObject.getuserByName("TestCase");	
		boolean result = temp.getUserName().equals("TestCase");	
		assertTrue(result);		
	}
	
	@Test
	public void checkUserRoleFromDatabase() 
	{	
		User temp = userObject.getuserByName("TestCase");	
		boolean result = temp.getUserRole().equals("ROLE_USER");	
		assertTrue(result);		
	}

	@Test
	public void deleteUserfromDatabaseReturnName() 
	{			
		//get mikiej admin name from server
		user = 	userObject.getuserByName("TestCase");			
		userObject.delete(user);	
		assertNull(userObject.getuserByName("TestCase"));			
	}
		
	
	public String encryptPassword(String password)
	{
		
		String unhashedPassword = password;
		String newHashedPW = null;
		for (int i = 0; i < 10; i++)
		{			
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			newHashedPW = encoder.encode(unhashedPassword);
	 		System.out.println("Password Encoded:- " + newHashedPW);			
		}
		
		return newHashedPW;
	}
	
	
	
}
