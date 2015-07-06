package UserPackage;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
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
	public static void deleteFolder(File dir) throws IOException
	{
	
		//checks to see if path is actually a directory
		if(dir.isDirectory())
		{

			//checks to see if directory is emoty or not
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
