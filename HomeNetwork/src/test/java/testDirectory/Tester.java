package testDirectory;

import java.io.IOException;

import UserPackage.UserFileControl;


/**
 * Test create and deletion of director for public and private 
 * userName will be obtained from user.getUserName();
 * @author mikieJ
 *
 */
public class Tester {

	public static void main(String[] args) throws IOException {
		
		
		String userName = "Tester";
		
		//Creation
		String publicFolderCreated = UserFileControl.createFolders("C:\\Users\\mikieJ\\Documents\\MSc_UserFolder\\" +  userName + "\\public");
		System.out.println(publicFolderCreated);			
		String privateFolderCreated = UserFileControl.createFolders("C:\\Users\\mikieJ\\Documents\\MSc_UserFolder\\" + userName + "\\private");
		System.out.println(privateFolderCreated);
		
		//Deletion  //requires throw declaration
		UserFileControl.folderExistsThenDelete("C:\\Users\\mikieJ\\Documents\\MSc_UserFolder\\" +  userName + "\\public");
		UserFileControl.folderExistsThenDelete("C:\\Users\\mikieJ\\Documents\\MSc_UserFolder\\" +  userName + "\\private");
		UserFileControl.folderExistsThenDelete("C:\\Users\\mikieJ\\Documents\\MSc_UserFolder\\" +  userName);

	}

}
