package testSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * This test method outputs passwords in their new encoded format 
 * useful to convert those passwords already stored as strings
 * @author MikieJ Study
 *
 */
public class PasswordEncoderChecker {
 
  public static void main(String[] args) {
 
	for (int i = 0; i < 10; i++)
	{
		String unhashedPassword = "jeff@123";
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String newHashedPW = encoder.encode(unhashedPassword);
 		System.out.println("Password Encoded:- " + newHashedPW);
		
	}
 
  }
}