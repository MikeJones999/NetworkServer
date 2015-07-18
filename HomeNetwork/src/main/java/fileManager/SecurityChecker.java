package fileManager;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import UserPackage.User;

public class SecurityChecker {

	

	  /**
	   * Check to ensure that the folder name is public or private
	   * 
	   * @param String folderType
	   * @return boolean
	   */
	  public static boolean isCorrectFolder(String folderType)
	  {
		  boolean result = false;
		  
		  //can conduct a search for the folder name if more folders used	  
		  
		  if(folderType.equals("public") || folderType.equals("private") )
		  {
			  result = true;
		  }
		  
		  return result;
		  
	  }
	
	  public static boolean isCorrectUser(String userName)
	  {
		boolean result = false;		  
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); 
		
		if (userName.equals(name))
        {
			System.out.println("***DEBUG** Security name check passed for user: "  + name);
			result = true;
		}
		else
		{
			System.out.println("***DEBUG** Security name check failed for user: "  + userName);
		}
		  
		  return result;		  
	  }

	
}
