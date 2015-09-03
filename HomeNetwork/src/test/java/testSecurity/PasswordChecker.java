package testSecurity;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordChecker {

	
	String[] pwArray = new String[10];
	
	@Before
	public void before()
	{
		
		for (int i = 0; i < 10; i++)
		{
			String unhashedPassword = "jeff@123";
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String newHashedPW = encoder.encode(unhashedPassword);
	 	    System.out.println("Password Encoded:- " + newHashedPW);
	 	    pwArray[i] = newHashedPW;			
		}
		
	}
	
	
	@Test
	public void passwordTest() 
	{
		
		String unhashedPassword = "jeff@123";
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String newHashedPW = encoder.encode(unhashedPassword);
		
		assertFalse(pwArray[0].equals(newHashedPW));
		
	}

}
