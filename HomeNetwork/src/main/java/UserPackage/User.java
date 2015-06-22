package UserPackage;

public class User {

	private String userName;
	//private String email;
	private String password;
	
	//only used for user_roles table - when deleting or changing role status
	private int id;
	
	
	public String getUserName() 
	{
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

	
	
	
}
