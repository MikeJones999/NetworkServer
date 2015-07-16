package UserPackage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//reference page - http://www.mkyong.com/java/how-to-delete-directory-in-java/  05/07/2015
//must turn off windows thumbnails in order to remove thumbs.db from being created - otherwise folder does not get removed.

public class UserFileControl {

	/**
	 * Create a folder in the specified directory 
	 * @param File
	 * @return String - complete or not
	 */
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
		

	  
	 
	  /**
	   * recursively deletes files and folders
	   * @param File
	   * @throws IOException
	   */
	public static void deleteFolder(File dir) throws IOException
	{
	
		//checks to see if path is actually a directory
		if(dir.isDirectory())
		{

			//checks to see if directory is empty or not
			//if empty straight forward delete - if files inside,
			//list them and then delete via iteration
			if(dir.list().length == 0)
			{
				//Path input = FileSystems.getDefault().getPath(dir.getAbsolutePath());
				//Path input = dir.toPath();
				// Files.delete(input);
				dir.delete();
				System.out.println("Folder Deleted : " + dir);
			}
			else
			{		
				//String list to hold all the files folders found in said directory
				String filesFound[]  = dir.list();
				
				for(String tempFile: filesFound)
				{
					//need to create the structure first in order to delete it
					File targetFileToRemove = new File(dir.getPath(), tempFile);
					System.out.println("File found: " + targetFileToRemove);
					
					//recursively call the check and delete of a directory/file
					deleteFolder(targetFileToRemove);
					System.out.println("Recursive call");
				}
				
				if(dir.list().length == 0)
				{
					
					//Path input = FileSystems.getDefault().getPath(dir.getAbsolutePath());
					//Path input = dir.toPath();
					// Files.delete(input);
					dir.delete();
					System.out.println("2nd call - Folder Deleted : " + dir);
				}
				
				
			}
		}
		else  //file is not a directory it is a file
		{
			//Path input = FileSystems.getDefault().getPath(dir.getAbsolutePath());
			//Path input = dir.toPath();
			// Files.delete(input);
			dir.delete();
			System.out.println("Folder/file Deleted : " + dir.getAbsolutePath());
		
			//do a check here for thumbs.db - and change name of directory
		}		
	}
	
	/**
	 * Checks initial folder exists - if so calls the delete method
	 * @param File
	 * @throws IOException
	 */
	//possibly make this return boolean for check safety
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

	/**
	 * Finds all files in a directory specified - returns a string array of the file names
	 * @param fileType
	 * @param userName
	 * @return
	 */
	public static List<String> getAllFilesFromDirectory(String fileType, String userName)
	{
		List<String> filesFound = new ArrayList<String>();
		String location = "C:\\Users\\mikieJ\\Documents\\MSc_UserFolder\\" +  userName + "\\" + fileType + "\\";
		
		
		File directory = new File(location);
		if(directory.exists())
		{			//get all files in list
			String[] foundFiles = directory.list();
			
			//may need to check for empty or null
			for(int i = 0; i < foundFiles.length; i++)
			{			
					filesFound.add(foundFiles[i]);
			}
		}
		
		return filesFound;
	}
	
	
	/**
	 * Check to see if file to be uploaded already exists
	 * @param location
	 * @param fileToBeChecked
	 * @return
	*/ 
	public static boolean fileAlreadyexist(String location, String fileToBeChecked)
	{
		boolean response = false;		
		File directory = new File(location);
		if(directory.exists())
		{			//get all files in list
			String[] foundFiles = directory.list();
			for(int i = 0; i < foundFiles.length; i++)
			{
				if (foundFiles[i].equalsIgnoreCase(fileToBeChecked))
				{
					response = true;
				}
			}
		}
		return response; 
	}
	
}
