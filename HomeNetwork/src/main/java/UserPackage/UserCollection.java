package UserPackage;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


//An example used to assist with the creation of this http://www.tutorialspoint.com/spring/spring_jdbc_framework.htm
public class UserCollection implements RowMapper<User> {

	public User mapRow(ResultSet row, int num) throws SQLException {
		
		User user = new User();
	    user.setUserName(row.getString("username"));
	    user.setPassword(row.getString("password"));
	    user.setUserRole(row.getString("ROLE"));
	    
		return user;
	}

}
