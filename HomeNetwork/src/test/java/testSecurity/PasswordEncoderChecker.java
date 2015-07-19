package testSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



public class PasswordEncoderChecker {
 
  public static void main(String[] args) {
 
	int i = 0;
	while (i < 10) {
		String unhashedPassword = "abertawe";
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String newHashedPW = encoder.encode(unhashedPassword);
 
		System.out.println(newHashedPW);
		i++;
	}
 
  }
}