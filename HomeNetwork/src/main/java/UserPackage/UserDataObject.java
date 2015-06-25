package UserPackage;

import java.util.List;

public interface UserDataObject {

	
	public void insert(User user);
	public void update(User user);
	public void delete(User user);
	public User getuserByName(String user);
	public int getUserId(String user);
	public List<User> getAllUsers();
}
