package UserPackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import com.mysql.jdbc.PreparedStatement;


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
		jdbcTempObject.update(sqlState, null, user.getUserName(), "ROLE_USER");		
		System.out.println("***Debug*** Insert User " + user.getUserName() + " into User_Roles + user status = " + "ROLE_USER");			 
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

}
