package com.hnserver.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import fileManager.FileManager;
import fileManager.SecurityChecker;
import UserPackage.User;
import UserPackage.UserDataObject;
import UserPackage.UserFileControl;

//reference https://spring.io/guides/gs/uploading-files/
// & http://www.mkyong.com/spring-mvc/spring-mvc-file-upload-example/
// & http://crunchify.com/spring-mvc-tutorial-how-to-upload-multiple-files-to-specific-location/
// & http://viralpatel.net/blogs/spring-mvc-multiple-file-upload-example/
@Controller
@SpringBootApplication
public class FileController {

@Autowired
UserDataObject dataObject;


/**
 * Returns the user's File Manager Page
 * @param User.userName
 * @param Model model 
 * @return filemanager.jsp
 */
@RequestMapping(value = "/userpage/{userName}/filemanager")
public String returnUserFileManagerPage(@PathVariable String userName, Map<String, Object> model)
{
	 if(!SecurityChecker.isCorrectUser(userName))
	 { 
		model.put("response", "You cannot view pages that are not in your name");
  		  return "redirect:/j_spring_security_logout";
		
	 }
	 User temp = dataObject.getuserByName(userName);
 	 System.out.println("***DEBUG*** found user filemanager page for - " + temp.getUserName());
 	 model.put("user", temp);
 	 return "filemanager"; 
}


/**
 * Returns the user folder page - private or public depending on the button selected.
 * Passes through the necessary folder type and user to ensure the right page is displayed
 * @param User.userName
 * @param String folderType - public or private
 * @param Model model
 * @return userfolderpage.jsp
 */
@RequestMapping(value = "/userpage/{userName}/{folderType}")
public String returnUserfolderPage(@PathVariable ("userName") String userName, @PathVariable ("folderType") String folderType, Map<String, Object> model)
{
	 if(!SecurityChecker.isCorrectUser(userName))
	 { 
			model.put("response", "You cannot view pages that are not in your name");
  		  return "redirect:/j_spring_security_logout";
	 }
	
    if(SecurityChecker.isCorrectFolder(folderType))
 	{
	 User temp = dataObject.getuserByName(userName);
 	 System.out.println("***DEBUG*** found " + folderType + " page for - " + temp.getUserName());
 	 model.put("user", temp);	 
   	List<String> filesFound = UserFileControl.getAllFilesFromDirectory(folderType, userName);
 	model.put("filesFound", filesFound);
	 	
	 	if(folderType.equals("public"))
	 	{
	 		 model.put("warningPublicPageMessage", "This page and its contents are not secured, but files can be shared with others.");	
	
	 	}
 	 return "userfolderpage"; 
 	}
 	else //redirect to security login page
 	{
 		return "redirect:/userpage/" + userName;
 	}
}


/**
 * Returns the users correct upload page for the stated folder type - public or private. The correct information is
 * passed through to the generic userfileupload.jsp
 * @param User.userName
 * @param String folderType - public private
 * @param Model model
 * @return userfileupload.jsp
 */
@RequestMapping(value = "/userpage/{userName}/{folderType}/upload")
public String returnUserfolderUploadPage(@PathVariable ("userName") String userName, @PathVariable ("folderType") String folderType, Map<String, Object> model)
{
	 if(!SecurityChecker.isCorrectUser(userName))
	 { 
			
		  model.put("response", "You cannot view pages that are not in your name");
  		  return "redirect:/j_spring_security_logout";
	 }
	 
	if(SecurityChecker.isCorrectFolder(folderType))
 	{	
	 User temp = dataObject.getuserByName(userName);
 	 System.out.println("***DEBUG*** found " + folderType + " upload page for - " + temp.getUserName());
 	 model.put("user", temp);
	 model.put("folderType", folderType); 	
 	 return "userfileupload"; 
 	}
 	else
 	{
 		return "redirect:/userpage/" + userName;
 	}
}




	/**
	 * Debug method only - to be removed
	 * Helped establish issues with uploading of files
	 * @param manageFiles
	 */
	public void debugListMultiPartFile(List<MultipartFile> manageFiles)
	{
		 for (MultipartFile file: manageFiles)
		 {
			 String fileName = file.getOriginalFilename();
			System.out.println("***DEBUG*** File found: " + fileName); 
		 }		
	}

	/**
	 * Returns the correct file that has or has not been uploaded -
	 * calls another method to carry out the file upload process (mainUploadFile).
	 * The method called returns the correct page to display then this methods passes it to the view.	 * 
	 * @param fileManager
	 * @param userName
	 * @param folderType
	 * @param model
	 * @param mod
	 * @param res
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "/userpage/{userName}/{folderType}/fileupload", method= RequestMethod.POST)
	public String genericFileUpload(@ModelAttribute("fileManager") FileManager fileManager, @PathVariable ("userName") String userName, @PathVariable ("folderType") String folderType, 
			Map<String, Object> model, Model mod, BindingResult res) throws IllegalStateException, IOException
	{
		 if(!SecurityChecker.isCorrectUser(userName))
		 { 
				model.put("response", "You cannot view pages that are not in your name");
	    		return "redirect:/j_spring_security_logout";
		 }
		
		if(!SecurityChecker.isCorrectFolder(folderType))
	 	{		 	
			return "redirect:/userpage/" + userName;			
	 	}	
		
			String response = mainUploadFile(folderType, userName, fileManager, mod);	
			return response;
	 
	}
	
	
	/**
	 * Performs the Uploading of files to provided folderType
	 * @param fileType - directory name for file(s) to be uploaded
	 * @param userName - user Name logged into system
	 * @param fileManager - FileManager
	 * @param Model
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public String mainUploadFile(String fileType, String userName, FileManager fileManager,  Model mod) throws IllegalStateException, IOException
	{
		User temp = dataObject.getuserByName(userName);
		mod.addAttribute("user",temp);
		mod.addAttribute("folderType", fileType);
		//set the directory for where the file is to be saved
		String saveLocation = "C:\\Users\\mikieJ\\Documents\\MSc_UserFolder\\" +  userName + "\\" + fileType + "\\";
		
		System.out.println("***DEBUG*** point 1 -Saving to " + saveLocation);
		
		//get the populated list of files - these have been populated by beans via the xml userfileupload.jsp (autowired by name)
		 List<MultipartFile> manageFiles = fileManager.getFiles();
		 debugListMultiPartFile(manageFiles);
		 
		 //create a list of STrings to hold the names to display as a return of what has been saved
		 List<String> filesNames = new ArrayList<String>();
		 List<String> existingfiles = new ArrayList<String>();
		 
		 //Check to see if the List of files is empty - if so show error
		   if (manageFiles.isEmpty() ) {
		        //res.reject("noFile", "Please select file to upload");
			    mod.addAttribute("messageWarning", "No file has been selected");
		        return "userfileupload";
		    }
		 
		 //check to see if its populated
	    if (null != manageFiles && manageFiles.size() > 0) 
	    {
	    	System.out.println("***DEBUG*** point 2");
	    	//iterate through the populated list to add the files
	        for (MultipartFile multipartFile : manageFiles)
	        {	
	        	System.out.println("***DEBUG*** point 3");
	            String fileName = multipartFile.getOriginalFilename();	            
	            //need to check to see if file already exists if so overwrite it       
	            
	     
	         Boolean FileExists = UserFileControl.fileAlreadyexist(saveLocation, fileName);
	         System.out.println("***DEBUG*** File exists: " + FileExists);
	             
	         if(!FileExists)
	         {
		         if(!multipartFile.isEmpty())
		         {
		            if (!"".equalsIgnoreCase(fileName)) 
			        { 		            	
		            	//this is the clever part that does the transferring to the specified location
			            multipartFile.transferTo(new File(saveLocation + fileName));
			            filesNames.add(fileName); 
			        	System.out.println("***DEBUG*** file transfered/uploaded");
		            }
		            //ignore the file
		            else
		            {
		            	System.out.println("***DEBUG*** ignored file");
		            }
		         }
		         else
		         {
		        	 System.out.println("***DEBUG*** file is empty - ignore");
		          }
	        
	         }//fileExists
	         else
	         {
	        	 existingfiles.add(fileName);
	         }

	       }//for	        	        
	        
	          
	        	if(!filesNames.isEmpty() || !existingfiles.isEmpty())
	        	{	      
	        	    	       		
		        	System.out.println("***DEBUG*** Completed upload");
		        	mod.addAttribute("message", "File upload Confirmation Page");
		        	
		        	if(!filesNames.isEmpty())
		        	{
		        	 mod.addAttribute("messageSuccessful", "Following files have been uploaded successfully");
		        	 mod.addAttribute("filesUploaded", filesNames);
		        	}
		         	
		         	if(!existingfiles.isEmpty())
		         	{
		         		mod.addAttribute("message1", "The following files already exist.");
		         	  	mod.addAttribute("message2", "As such these files have not been uploaded. "
		         	  			+ "Please delete these manually or use the File Manager Page to assist");
		         		mod.addAttribute("filesNotUploaded", existingfiles);
		         	}
		        	mod.addAttribute("folder", fileType);
		         	return "fileuploaded";	      
	        	}
	        	else
	        	{ 	
	        		
	        		System.out.println("***DEBUG*** No file uploaded");
	             	mod.addAttribute("messageWarning", "No file(s) have been selected");
	             	return "userfileupload";
	        	}			  
	    }   
		
	    System.out.println("***DEBUG*** manageFiles is null or greater thean 0");
	    return "userfileupload";	
	}
	
	
	
	

	/**
	 * Delete a file from a private folder
	 * @param User.userName
	 * @param fileName  {fileName:.+} required to stop string cutting anything off after . - type can now be found
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/userpage/{userName}/private/delete/{fileName:.+}",  method = RequestMethod.GET)
	public String deletePrivateFile(@PathVariable ("userName") String userName, @PathVariable ("fileName") String fileName, Map<String, Object> model) throws IOException
	{
		 if(!SecurityChecker.isCorrectUser(userName))
		 { 
				model.put("response", "You cannot view pages that are not in your name");
	    		return "redirect:/j_spring_security_logout";
		 }
		
		String fileType = fileName ;
		//String temp = fileName + ".pub";
		System.out.println("***DEBUG*** looking for file: " + fileName);
		String location = "C:\\Users\\mikieJ\\Documents\\MSc_UserFolder\\" +  userName + "\\" + "private" + "\\";
		model.put("folderType", "private");
		model.put("userName", userName);
		//if files exists then delete
		if (UserFileControl.fileAlreadyexist(location, fileType))
		{
			model.put("message", "found and deleted file: " + fileName);
			UserFileControl.folderExistsThenDelete("C:\\Users\\mikieJ\\Documents\\MSc_UserFolder\\" +  userName + "\\private\\" + fileName);
			return "filedeleted";			
		}
		else
		{
			model.put("message", fileName + " was not found in the directory, thus it cannot be deleted");
			return "filedeleted";
		}
		
	}
	

	/**
	 * Delete a file from a public folder
	 * @param User.userName
	 * @param fileName  {fileName:.+} required to stop string cutting anything off after . - type can now be found
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/userpage/{userName}/public/delete/{fileName:.+}",  method = RequestMethod.GET)
	public String deletePublicFile(@PathVariable ("userName") String userName, @PathVariable ("fileName") String fileName, Map<String, Object> model) throws IOException
	{
		 if(!SecurityChecker.isCorrectUser(userName))
		 { 
				model.put("response", "You cannot view pages that are not in your name");
	    		  return "redirect:/j_spring_security_logout";
		 }
		
		
		String fileType = fileName ;
		//String temp = fileName + ".pub";
		System.out.println("***DEBUG*** looking for file: " + fileName);
		String location = "C:\\Users\\mikieJ\\Documents\\MSc_UserFolder\\" +  userName + "\\" + "public" + "\\";
		model.put("folderType", "public");
		//if files exists then delete
		if (UserFileControl.fileAlreadyexist(location, fileType))
		{
			
			UserFileControl.folderExistsThenDelete("C:\\Users\\mikieJ\\Documents\\MSc_UserFolder\\" +  userName + "\\public\\" + fileName);			
			//return "filedeleted";
		 	User temp = dataObject.getuserByName(userName);
		 	model.put("user", temp);	 
		   	List<String> filesFound = UserFileControl.getAllFilesFromDirectory("public", userName);
		 	model.put("filesFound", filesFound);
			model.put("message", fileName + " was deleted from you public folder");

	 	 return "userfolderpage"; 
		}
		else
		{
			model.put("message", fileName + " was not found in the directory, thus it cannot be deleted");
			return "userfolderpage";
		}
		
	}
	
	
	//references http://docs.spring.io/spring/docs/1.2.x/api/org/springframework/util/FileCopyUtils.html 
	//&& http://owlsayswoot.therandomist.com/2011/12/12/how-to-download-files-with-springmvc/ 15/07/2015)
	
	/**
	 * The controller assist with the downloading of the passed file - 
	 * It checks to see if it exists in its stated folder - if so it is downloaded. 
	 * @param response
	 * @param folderType
	 * @param userName
	 * @param fileName
	 * @param model
	 * @throws IOException
	 */
	@RequestMapping(value = "/userpage/{userName}/{folderType}/download/{fileName:.+}",  method = RequestMethod.GET)
	public void downloadFile(HttpServletResponse servletResponse,  @PathVariable ("folderType") String folderType,  @PathVariable ("userName") String userName, @PathVariable ("fileName") String fileName, Map<String, Object> model) throws IOException 
	{
	if(!folderType.equals("public"))
	{
		 System.out.println("***DEBUG*** found download controller");
		 if(!SecurityChecker.isCorrectUser(userName))
		 { 
			 return;
		 }
		 
		 if(!SecurityChecker.isCorrectFolder(folderType))
		 {
			 return;
		 }
	}
		 
		System.out.println("***DEBUG*** looking for file: " + fileName);
		
		String location = "C:\\Users\\mikieJ\\Documents\\MSc_UserFolder\\" +  userName + "\\" + folderType + "\\";
		    Boolean fileExists = UserFileControl.fileAlreadyexist(location, fileName);
	       if(fileExists)
	       {
	    	   String fullFilePath = location + fileName;
			    File fileToDownload = new File(fullFilePath);
			    
				if(fileToDownload.exists())
				{			
					String fileType = FilenameUtils.getExtension(fullFilePath);
					
					System.out.println("***DEBUG*** filetype found : " + fileType);
					servletResponse.setContentType("fileType"); 
				    servletResponse.setContentLength(new Long(fileToDownload.length()).intValue());
				    servletResponse.setHeader("Content-Disposition","attachment; filename=" + fileName);			 
			    try {
			            FileCopyUtils.copy(new FileInputStream(fileToDownload), servletResponse.getOutputStream());
			        }
			    catch (IOException e) 
			    	{
			            e.printStackTrace();
			        }
			        return;	
				}
     
	       }

	}

	/**
	 * Returns a String which is the link and direct path to downloading files. This is not a hidden path 
	 * @param User.userName
	 * @param String fileName
	 * @param Model model
	 * @return filepubliclink.jsp page
	 * @throws IOException
	 */
	@RequestMapping(value = "/userpage/{userName}/public/copyFileLink/{fileName:.+}",  method = RequestMethod.GET)
	public String getPublicFileLink(@PathVariable ("userName") String userName, @PathVariable ("fileName") String fileName, Map<String, Object> model) throws IOException 
	{
		
		 if(!SecurityChecker.isCorrectUser(userName))
		 { 
				model.put("response", "You cannot view pages that are not in your name");
	    		return "redirect:/j_spring_security_logout";
		 }
		
		//get IP
		String ip = getIpOfHostMachine();
		String port = "8080";
		
		String linkToFile = ip + ":" + port + "/HomeNetwork/userpage/" + userName + "/public/download/" + fileName; 
		//model.put("message", "Please copy this link - paste it in an email or instant chat to allow others to download this file");
		model.put("folderType", "public");
//		model.put("fileLink", linkToFile);
//		return "filepubliclink";
		
		 User temp = dataObject.getuserByName(userName);
	 	 System.out.println("***DEBUG*** found " + "private" + " file copying page for - " + temp.getUserName() + " File:- " + fileName);
	 	 model.put("user", temp);
		 model.put("file", fileName);
		
		 model.put("messageCopied", "Copy this url:- " + linkToFile);
		 return "filehandlingpage";
		
	}
	
	/**
	 * Obtains network IP of the server/Host - Created to assist with constructing the link address for file downloading 
	 * @return String ip
	 * @throws UnknownHostException
	 */
	public String getIpOfHostMachine() throws UnknownHostException
	{		
		InetAddress address = InetAddress.getLocalHost();
		String ip = address.getHostAddress();
		String name = address.getHostName();
		System.out.println("***DEBUG*** Host Name : " + name +  " IP: " + ip);		
		return ip;
		
	}
	
	@RequestMapping(value ="/userpage/{userName}/{folderType}/handlefile/{fileName:.+}",  method = RequestMethod.GET)
	public String handleIndividualFile(@PathVariable ("userName") String userName, @PathVariable ("folderType") String folderType, @PathVariable ("fileName") String fileName, Map<String, Object> model) throws IOException 
	{
		
		 if(!SecurityChecker.isCorrectUser(userName))
		 { 
				model.put("response", "You cannot view pages that are not in your name");
	    		return "redirect:/j_spring_security_logout";
		 }
			if(!SecurityChecker.isCorrectFolder(folderType))
		 	{		 	
				return "redirect:/userpage/" + userName;			
		 	}	
         
			 User temp = dataObject.getuserByName(userName);
		 	 System.out.println("***DEBUG*** found " + folderType + " handle page for - " + temp.getUserName() + " File:- " + fileName);
		 	 model.put("user", temp);
			 model.put("folderType", folderType); 
			 model.put("file", fileName);
			 return "filehandlingpage";
	}
	
	
	@RequestMapping(value ="/userpage/{userName}/private/copyFiletoPublic/{fileName:.+}")
	public String copyFileToPublciFolder(@PathVariable ("userName") String userName, @PathVariable ("fileName") String fileName, Map<String, Object> model) throws IOException 
	{
		 if(!SecurityChecker.isCorrectUser(userName))
		 { 
				model.put("response", "You cannot view pages that are not in your name");
	    		return "redirect:/j_spring_security_logout";
		 }
		 
		 
		 User temp = dataObject.getuserByName(userName);
	 	 System.out.println("***DEBUG*** found " + "private" + " file copying page for - " + temp.getUserName() + " File:- " + fileName);
	 	 model.put("user", temp);
		 model.put("folderType", "private"); 
		 model.put("file", fileName);
		 
		 String oldLocation = "C:\\Users\\mikieJ\\Documents\\MSc_UserFolder\\" +  userName + "\\" + "private" + "\\";
		 String newLocation = "C:\\Users\\mikieJ\\Documents\\MSc_UserFolder\\" +  userName + "\\" + "public" + "\\";
		 
		 if(UserFileControl.fileAlreadyexist(newLocation, fileName)) 
		 {
			 model.put("messageCopied", "File: " + fileName + " has NOT been copied to your public folder. It already exists in your public folder");
			 return "filehandlingpage";
		 }
		 
		 if (UserFileControl.copyFiletoFolder(oldLocation, newLocation, fileName))
		 {		
			 model.put("messageCopied", "File: " + fileName + " has been copied to your public folder");
			 return "filehandlingpage";
		 }
		 
		 model.put("messageCopied", "Error - File: " + fileName + " has NOT been copied to your public folder. Please Try again");
		 return "filehandlingpage";
	}	
		
		
}
