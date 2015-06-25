package testDatabase;


import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import UserPackage.JdbcUserControl;
import UserPackage.User;
import UserPackage.UserCollection;
/**
 * Test package to connect to mysql database
 * @author mikieJ
 *
 */


public class Tester {

	public static void main(String[] args) {
		      ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		      //ApplicationContext context = new ClassPathXmlApplicationContext("/dispatcherservlet-servlet.xml");
		      
		      JdbcUserControl userObject = (JdbcUserControl)context.getBean("userObject");
		      
		      
		      //Add new user to database
	      
		      System.out.println("------Records Creation--------" );
		      User user1 = new User();
		      user1.setUserName("dave");
		      user1.setPassword("dave@123");		      
		      userObject.insert(user1);
		      
		      
		      //delete user from database
		      
//		      System.out.println("------Records Deletion--------" );
//		      User temp = userObject.getuserByName("dave");
//		      userObject.delete(temp);
		      
				
		
		      
		   }
		}