package testDirectory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import UserPackage.UserFileControl;

public class TestGetFileDetails {
	
	public static void main(String[] args) throws IOException
	{
		String folderType = "public";
		String userName = "mj";
		List<String> files = getAllFileNamesFromDirectory(folderType, userName);
		
		String location = "C:\\Users\\mikieJ\\Documents\\MSc_UserFolder\\" + userName + "\\" + folderType + "\\";

//		List<File> fileList = files.stream().map(s -> new File(location + s)).collect(Collectors.toList());
//		
//		List<Path> detailsOfFiles = new ArrayList<>();
//		for (File f: fileList)
//		{
//			Path path = Paths.get(location + f.getName());
//			detailsOfFiles.add(path);
//			//detailsOfFiles.add(Files.readAttributes(path, BasicFileAttributes.class).lastModifiedTime().toString());
//		}
//
//		detailsOfFiles.forEach(s -> System.out.println("File details: " + s));
//		
//		
//		Path path = Paths.get(fileList.get(0).getName());
//		System.out.println(path.getName(0));
		
		String fileName = "2014-07-17 16.40.36.jpg";
		String file = location + fileName;
		System.out.println("Does File exist: " + UserFileControl.fileAlreadyexist(location, fileName));
		
		File nFile = new File(file);
		
	    Path path = Paths.get(nFile.getAbsolutePath());
	    
		
		String dets = Files.readAttributes(path, BasicFileAttributes.class).lastModifiedTime().toString();
		System.out.println("complete to here: " + dets);
		
		String[] formated = dets.split("T");
		System.out.println(formated.toString());
		String date = formated[0];
		String time = formated[1].substring(0,8);
		
		System.out.println("Date: " + date + ", Time: " + time);
	}
	

	
	
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
	
}
