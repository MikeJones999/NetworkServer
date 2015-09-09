package UserPackage;


import java.util.List;

/**
 * Interface for database communication
 * @author mikieJ
 *
 */
public interface UserDataObject {

	
	public void insert(User user);
	public void update(User user);
	public void delete(User user);
	public User getuserByName(String user);
	public int getUserId(String user);
	public List<User> getAllUsers();
	public  List<User> getAllAdmins();
	public List<User> getAllNonAdmins();
	public String getUserRole(String user); 
	public boolean checkUserExists(String userName);
	List<User> getAllAdminsSQL();
	List<User> getAllNonAdminsSQL();
	public int getUserSecurityCount(String user);
	public void resetSecurityCount(User user); 
	public void incrementSecurityCount(User user, int current); 
	
}
