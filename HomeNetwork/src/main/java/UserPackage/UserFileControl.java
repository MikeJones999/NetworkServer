package UserPackage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;

//reference page - http://www.mkyong.com/java/how-to-delete-directory-in-java/  05/07/2015
//must turn off windows thumbnails in order to remove thumbs.db from being created - otherwise folder does not get removed.

public class UserFileControl {

	/**
	 * Create a folder in the specified directory
	 * 
	 * @param File
	 * @return String - complete or not
	 */
	public static String createFolders(String dir) {
		File folderDir = new File(dir);
		if (!folderDir.exists()) {
			// returns true if directories were created - false otherwise
			// make a username\\public and a username\\private
			if (folderDir.mkdirs()) {
				return "Folder created in : " + dir;
			} else {
				return "Folder Was NOT created ";
			}
		}
		return dir + " already exists";
	}

	/**
	 * recursively deletes files and folders
	 * 
	 * @param File
	 * @throws IOException
	 */
	public static void deleteFolder(File dir) throws IOException {

		// checks to see if path is actually a directory
		if (dir.isDirectory()) {

			// checks to see if directory is empty or not
			// if empty straight forward delete - if files inside,
			// list them and then delete via iteration
			if (dir.list().length == 0) {
				// Path input =
				// FileSystems.getDefault().getPath(dir.getAbsolutePath());
				// Path input = dir.toPath();
				// Files.delete(input);
				dir.delete();
				System.out.println("Folder Deleted : " + dir);
			} else {
				// String list to hold all the files folders found in said
				// directory
				String filesFound[] = dir.list();

				for (String tempFile : filesFound) {
					// need to create the structure first in order to delete it
					File targetFileToRemove = new File(dir.getPath(), tempFile);
					System.out.println("File found: " + targetFileToRemove);

					// recursively call the check and delete of a directory/file
					deleteFolder(targetFileToRemove);
					System.out.println("Recursive call");
				}

				if (dir.list().length == 0) {

					// Path input =
					// FileSystems.getDefault().getPath(dir.getAbsolutePath());
					// Path input = dir.toPath();
					// Files.delete(input);
					dir.delete();
					System.out.println("2nd call - Folder Deleted : " + dir);
				}

			}
		} else // file is not a directory it is a file
		{
			// Path input =
			// FileSystems.getDefault().getPath(dir.getAbsolutePath());
			// Path input = dir.toPath();
			// Files.delete(input);
			dir.delete();
			System.out
					.println("Folder/file Deleted : " + dir.getAbsolutePath());

			// do a check here for thumbs.db - and change name of directory
		}
	}

	/**
	 * Checks initial folder exists - if so calls the delete method
	 * 
	 * @param File
	 * @throws IOException
	 */
	// possibly make this return boolean for check safety
	public static void folderExistsThenDelete(String dir) throws IOException {
		// create the temp folder
		File tempDir = new File(dir);

		// check if it exists
		if (!tempDir.exists()) {
			System.out.println(dir + " does not exists - cannot delete");
		} else {
			deleteFolder(tempDir);
		}
	}

	/**
	 * Finds all files in a directory specified - returns a string array of the
	 * file names 
	 * @param String fileType
	 * @param String userName
	 * @return List<String>
	 */
	public static List<String> getAllFileNamesFromDirectory(String folderType, String userName)
	{
		List<String> filesFound = new ArrayList<String>();
		String location = "C:\\Users\\mikieJ\\Documents\\MSc_UserFolder\\" + userName + "\\" + folderType + "\\";

		File directory = new File(location);
		if (directory.exists())
		{
		// get all files in list
			String[] foundFiles = directory.list();

			// may need to check for empty or null
			for (int i = 0; i < foundFiles.length; i++) {
				filesFound.add(foundFiles[i]);
			}
		}

		return filesFound;
	}

	/**
	 * Check to see if file to be uploaded or uploaded already - exists
	 * 
	 * @param location
	 * @param fileToBeChecked
	 * @return boolean
	 */
	public static boolean fileAlreadyexist(String location,
			String fileToBeChecked) {
		boolean response = false;
		File directory = new File(location);
		if (directory.exists()) { // get all files in list
			String[] foundFiles = directory.list();
			for (int i = 0; i < foundFiles.length; i++) {
				if (foundFiles[i].equalsIgnoreCase(fileToBeChecked)) {
					response = true;
				}
			}
		}
		return response;
	}

	/**
	 * Copies a single file to a folder specified
	 * @param String oldLocation
	 * @param String newLocation
	 * @param String file
	 * @return boolean whether complete or not
	 */
	public static boolean copyFiletoFolder(String oldLocation, String newLocation, String file)
	{
		boolean complete = false;
		File newDirectory = new File(newLocation);
		File oldDirectory = new File(oldLocation);
		if(fileAlreadyexist(oldLocation, file) && newDirectory.exists())
		{
			if(oldDirectory.exists())
			{			//get all files in list
				String[] foundFiles = oldDirectory.list();
				for(int i = 0; i < foundFiles.length; i++)
				{
					if (foundFiles[i].equalsIgnoreCase(file))
					{
						System.out.println("***DEBUG*** file found");
						  String fullFilePath = oldLocation + foundFiles[i];
					     File fileToCopy = new File(fullFilePath);	

							try {
							
								    FileUtils.copyFileToDirectory(fileToCopy, newDirectory);
								    complete = true;
								    System.out.println("***DEBUG*** file copied");
								}
							catch (IOException e) 
								{
								    e.printStackTrace();
								}
					}
				}
			}
		}
		
		return complete;		
	}
	
	/**
	 * Returns the file type attached to the string filename.
	 * @param String fileName
	 * @param String fileLocation
	 * @return String file type
	 */
	public static String getFileType(String fileName, String fileLocation)
	{
		if(fileAlreadyexist(fileLocation, fileName))
		{			
			String fileType = "";
			int count = 0;
			for (int i = fileName.length() -1; i > 0; i--)
			{
				if(fileName.charAt(i) == '.')
				{
					count = i;
					break;
				}
			}
			
			 fileType = fileName.substring(count);
	         System.out.println("***DEBUG*** file type: " + fileType);	
	         return fileType;
		}
		
	    return "";
	}
	
	/**
	 * Crude way of checking file type of string rather than of file
	 * @param String fileType
	 * @return boolean
	 */
	public static boolean isImageFile(String fileType)
	{
		boolean result = false;
		
		List<String> imageCollection = new ArrayList<String>();
		imageCollection.add(".jpg");
		imageCollection.add(".png");
		imageCollection.add(".gif");
		imageCollection.add(".jpeg");
		imageCollection.add(".jpg");
		imageCollection.add(".bmp");
		imageCollection.add(".tiff");
		
		for (String s: imageCollection)
		{
			if (fileType.equalsIgnoreCase(s))
			{
				result = true;
				System.out.println("***DEBUG*** found image file returning true");
			}					
		}		
		return result;		
	}
	
	/**
	 * Returns the number of files in a folder
	 * @param String folder
	 * @param String userName
	 * @return int - number of files found
	 */
	public static int getNumberOfFilesInFolder(String folder, String userName)
	{
		int result = 0;
		List<String> fileList = getAllFileNamesFromDirectory(folder, userName);
		if (!fileList.isEmpty())
		{
			result = fileList.size();
		}
		
		return result;
	}
	
	
	public static String[] getFileTimeAndDate(String folderType, String userName, String fileName) throws IOException
	{
	
		String location = "C:\\Users\\mikieJ\\Documents\\MSc_UserFolder\\" + userName + "\\" + folderType + "\\";
	
	
		String file = location + fileName;
		System.out.println("Does File exist: " + UserFileControl.fileAlreadyexist(location, fileName));
		
		File nFile = new File(fileName);
		
	    Path path = Paths.get(file);	
		String dets = Files.readAttributes(path, BasicFileAttributes.class).lastModifiedTime().toString();
		
		String[] formated = dets.split("T");
		System.out.println(formated.toString());
		String date = "Date: " + formated[0];
		String time = "Time: " + formated[1].substring(0,8);
		formated[0] = date;
		formated[1] = time;
		return formated;
	}


	
}
