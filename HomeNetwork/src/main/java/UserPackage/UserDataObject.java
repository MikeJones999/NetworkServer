package UserPackage;


import java.util.List;

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
	
	
}
