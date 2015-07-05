package UserPackage;

import java.io.File;
import java.io.IOException;
import java.util.List;

//reference page - http://www.mkyong.com/java/how-to-delete-directory-in-java/  05/07/2015

public class UserFileControl {


	public static String createFolders(String dir)
	{
		File folderDir = new File(dir);
		if(!folderDir.exists())
		{
			//returns true if directories were created - false otherwise
			//make a username\\public and a username\\private
			if(folderDir.mkdirs())
			{
				return "Folder created in : " + dir;
			}
			else
			{
				return "Folder Was NOT created ";
			}
		}
		
		return dir + " already exists";
	}
		
	/*
	 * recursively deletes files and folders
	 */
	public static void deleteFolder(File dir)
	{
	
		//checks to see if path is actually a directory
		if(!dir.isDirectory())
		{

			//checks to see if directory is emoty or not
			//if empty straight forward delete - if files inside,
			//list them and then delete via iteration
			if(dir.length() == 0)
			{
				dir.delete();
				System.out.println("Folder Deleted : " + dir);
			}
			else
			{
				
				String filesFound[]  = dir.list();
				
				for(String tempFile: filesFound)
				{
					//need to create the structure first in order to delete it
					File targetFileToRemove = new File(dir, tempFile);
					System.out.println("File found: " + targetFileToRemove);
					
					//recursively call the check and delete of a directory/file
					deleteFolder(targetFileToRemove);
					System.out.println("Recursive call");
				}
				
				
			}
		}
		else  //file is not a directory it is a file
		{
			dir.delete();
			System.out.println("Folder/file Deleted : " + dir.getAbsolutePath());
		}
		
		
	}
	
	
	public static void folderExistsThenDelete(String dir) throws IOException
	{
		//create the temp folder
		File tempDir = new File(dir);
		
		//check if it exists
		if(!tempDir.exists())
		{
			System.out.println(dir + " does not exists - cannot delete");
		}
		else
		{
			deleteFolder(tempDir);			
		}
	}

	
	
}
