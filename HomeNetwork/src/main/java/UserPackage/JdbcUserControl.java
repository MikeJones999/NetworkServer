package UserPackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.PreparedStatement;

@Repository
public class JdbcUserControl implements UserDataObject {

	
	private DataSource ds;
	private JdbcTemplate jdbcTempObject;
	
	
	//datasource obtains database url, username, and password without specifying details
	public void setDataSource(DataSource ds)
	{
		this.ds = ds;	
		this.jdbcTempObject = new JdbcTemplate(ds) ;		
	}
		
	/**
	 * Insert User into users database
	 */
	public void insert(User user) 
	{
		//define Mysql statement - 
		String sqlState = "INSERT INTO users (username, password, enabled) VALUES (?, ?, ?)";
		
		//pass information into the tempObject 
		int enabled = 1;
		jdbcTempObject.update(sqlState, user.getUserName(), user.getPassword(), enabled);
		
		System.out.println("***Debug*** Insert User = " + user.getUserName() + " user password = " + user.getPassword());		
		
		//need to add insertion into user_roles database
		sqlState = "INSERT INTO user_roles (user_role_id, username, ROLE) VALUES (?, ?, ?)";
		
		//pass information into the tempObject 
		//Null is used as there is an auto increment in the database - therefore this does it automatically	
		if (user.getUserRole().equals("ROLE_ADMIN"))
		{
			jdbcTempObject.update(sqlState, null, user.getUserName(), "ROLE_ADMIN");	
			System.out.println("***Debug*** Insert User " + user.getUserName() + " into User_Roles + user status = " + "ROLE_ADMIN");
		}else
		{		
			jdbcTempObject.update(sqlState, null, user.getUserName(), "ROLE_USER");		
			System.out.println("***Debug*** Insert User " + user.getUserName() + " into User_Roles + user status = " + "ROLE_USER");
		}
	}

	/**
	 * Update User in user database
	 */
	public void update(User user) 
	{
		String username = user.getUserName();
	    String sqlState = "update users set password = ? where username = ?";
	      jdbcTempObject.update(sqlState, user.getPassword(), username);
	      System.out.println("Updated Record with password for user = " + username );
	}

	/**
	 * Delete User from user table and user_roles table
	 * due to foreign key constraints - have to delete from user_roles first
	 */
	public void delete(User user)
	{	
		String username = user.getUserName();
			
		String sqlState = "delete from user_roles where user_role_id = ?";
	    int id = getUserId(username);
	    jdbcTempObject.update(sqlState, id);
	    System.out.println("***DEBUG*** Delete Record with username = " + username + " and Id = " + id + " from user_roles table");
	
	    sqlState = "delete from users where username = ?";
	    jdbcTempObject.update(sqlState, username);
	  	System.out.println("***DEBUG*** Delete Record with username = " + username + " from user table");
  
	}


	/**
	 * Find a user in user database by their name
	 */
	public User getuserByName(String user) 
	{
		 String sqlState = "SELECT * from users where username = ?";		 
		 Connection connection = null;
		 try
		 {
			 connection = ds.getConnection();
			 PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sqlState);
			 ps.setString(1, user);
			 User returnedUser = null;
			 
			 ResultSet results = ps.executeQuery();
			 if (results.next())
			 {
				 returnedUser = new User();
				 returnedUser.setUserName(results.getString("username"));
				 returnedUser.setPassword(results.getString("password"));
			 }
			 results.close();
			 ps.close();
			 return returnedUser;
			 }
		 catch (SQLException e)
		 {
			throw new RuntimeException(e); 
		 }
		 finally
		 //shut down connection to database if not already closed
		 {
			 if (connection != null)
			 {
				 try
				 {
					 connection.close();
				 } catch (SQLException e) { }
			 }
		 }
	}

	/**
	 * Find a id of user in user_roles database by their name
	 * this will be replaced with the new rowMapper method - demonstrated below
	 */
	public int getUserId(String user) 	{
		 String sqlState = "SELECT * from user_roles where username = ?";		 
		 Connection connection = null;
		 try
		 {
			 connection = ds.getConnection();
			 PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sqlState);
			 ps.setString(1, user);
			 User returnedUser = null;
			 
			 ResultSet results = ps.executeQuery();
			 if (results.next())
			 {
				 returnedUser = new User();
				 returnedUser.setUserName(results.getString("username"));
				 returnedUser.setId(results.getInt("user_role_id"));
			 }
			 results.close();
			 ps.close();
			 return returnedUser.getId();
			 }
		 catch (SQLException e)
		 {
			throw new RuntimeException(e); 
		 }
		 finally
		 //shut down connection to database if not already closed
		 {
			 if (connection != null)
			 {
				 try
				 {
					 connection.close();
				 } catch (SQLException e) {}
			 }
		 }
	}
	
	public String getUserRole(String user) 
	{		
		List<User> users = getAllUsers();
		User temp = null;
		for (User newUser: users)
		{
			if(newUser.getUserName().equals(user))
			{
				temp = newUser;
			}
		}
		
		if (temp == null)
		{
			return "Unknown";
		}
		return temp.getUserRole();
	}
		
	
	/**
	 * Checks whether User already exists in database or not
	 * @param String userName
	 * @return boolean
	 */
	public boolean checkUserExists(String userName)
	{
		List<User> users = getAllUsers();
		
		boolean result = false;
		for (User newUser: users)
		{
			if(newUser.getUserName().equals(userName))
			{
				result = true;
			}
		}		
		return result;
	}
	
	
	
	

	@Override
	public List<User> getAllUsers() 
	{		
		//String sqlState = "SELECT * from users ";
		String sqlState = "SELECT DISTINCT * "
				+ "		  from users, user_roles"
				+ "		  where users.username = user_roles.username ";	
		List<User> allUsers = jdbcTempObject.query(sqlState, new UserCollection());
		return allUsers;
	}

	

	@Override
	public List<User> getAllAdminsSQL() 
	{		
		//String sqlState = "SELECT * from users ";
		String sqlState = "SELECT DISTINCT * "
				+ "		  from users, user_roles"
				+ "		  where users.username = user_roles.username "
				+ "		  and user_roles.ROLE = 'ROLE_ADMIN' ";	
		List<User> allUsers = jdbcTempObject.query(sqlState, new UserCollection());
		return allUsers;
	}
	
	@Override
	public List<User> getAllNonAdminsSQL() 
	{		
		//String sqlState = "SELECT * from users ";
		String sqlState = "SELECT DISTINCT * "
				+ "		  from users, user_roles"
				+ "		  where users.username = user_roles.username "
				+ "		  and user_roles.ROLE = 'ROLE_USER' "
				+ "		  and users.username not in (select u.username"
				+ "								from user_roles u"
				+ "								where u.ROLE = 'ROLE_ADMIN'"
				+ "							    )	";	
		List<User> allUsers = jdbcTempObject.query(sqlState, new UserCollection());
		return allUsers;
	}
	
	/**
	 * Return a list of all admins on the system - at least one will always be called
	 * 
	 * @return List<User>
	 */
	public List<User> getAllAdmins()
	{
		
		List<User> admins = getAllAdminsSQL();		
		return admins;
	}

	/**
	 * Return a list of all non-admins on the system - at least one will always be called,
	 *  as when you create an admin a user account will also be created - to be implemented 
	 * 
	 * @return List<User>
	 */
	public List<User> getAllNonAdmins()
	{
		List<User> nonAdmins = getAllNonAdminsSQL();		
		return nonAdmins;
	}

}
