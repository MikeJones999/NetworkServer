package com.hnserver.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

@RequestMapping(value = "/userpage/{userName}/filemanager")
public String returnUserFileManagerPage(@PathVariable String userName, Map<String, Object> model)
{
	 User temp = dataObject.getuserByName(userName);
 	 System.out.println("***DEBUG*** found userfileupload page for - " + temp.getUserName());
 	 model.put("user", temp);
 	 
 	 
 	 
 	 return "filemanager"; 
}



@RequestMapping(value = "/userpage/{userName}/{folderType}")
public String returnUserfolderPage(@PathVariable ("userName") String userName, @PathVariable ("folderType") String folderType, Map<String, Object> model)
{
	 User temp = dataObject.getuserByName(userName);
 	 System.out.println("***DEBUG*** found " + folderType + " page for - " + temp.getUserName());
 	 model.put("user", temp);	 
   	List<String> filesFound = UserFileControl.getAllFilesFromDirectory(folderType, userName);
 	model.put("filesFound", filesFound);
 	
 	if(folderType.equals("public"))
 	{
 		 model.put("warningPublicPageMessage", "This page and its contents are not secured and open for	sharing/downloading");	

 	}
 
 	 return "userfolderpage"; 
}


@RequestMapping(value = "/userpage/{userName}/{folderType}/upload")
public String returnUserfolderUploadPage(@PathVariable ("userName") String userName, @PathVariable ("folderType") String folderType, Map<String, Object> model)
{
	 User temp = dataObject.getuserByName(userName);
 	 System.out.println("***DEBUG*** found " + folderType + " page for - " + temp.getUserName());
 	 model.put("user", temp);
	 model.put("folderType", folderType); 	
 	 return "userfileupload"; 
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
	
	@RequestMapping(value = "/userpage/{userName}/public/fileupload", method= RequestMethod.POST)
	public String publicfileUpload(@ModelAttribute("fileManager") FileManager fileManager, @PathVariable String userName, 
			Map<String, Object> model, Model mod, BindingResult res) throws IllegalStateException, IOException
	{
		
		String fileType = "public";		
		String response = mainUploadFile(fileType, userName, fileManager, mod);
	
		return response;
	}
	
	
	@RequestMapping(value = "/userpage/{userName}/private/fileupload", method= RequestMethod.POST)
	public String privatefileUpload(@ModelAttribute("fileManager") FileManager fileManager, @PathVariable String userName, 
			Map<String, Object> model, Model mod, BindingResult res) throws IllegalStateException, IOException
	{
		
		String fileType = "private";		
		String response = mainUploadFile(fileType, userName, fileManager, mod);
	
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
		         		mod.addAttribute("message1", "Following files already exist");
		         	  	mod.addAttribute("message2", "As such these files have not been uploaded. "
		         	  			+ "Please delete these manually or via the File Manager Page");
		         		mod.addAttribute("filesNotUploaded", existingfiles);
		         	}
		        	mod.addAttribute("folder", fileType);
		         	return "fileuploaded";	      
	        	}
	        	else
	        	{ 	
	        		
	        		System.out.println("***DEBUG*** No file uploaded");
	             	mod.addAttribute("messageWarning", "No file has been selected");
	             	
	             		
	             	 
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
		String fileType = fileName ;
		//String temp = fileName + ".pub";
		System.out.println("***DEBUG*** looking for file: " + fileName);
		String location = "C:\\Users\\mikieJ\\Documents\\MSc_UserFolder\\" +  userName + "\\" + "public" + "\\";
		model.put("folderType", "public");
		model.put("userName", userName);
		//if files exists then delete
		if (UserFileControl.fileAlreadyexist(location, fileType))
		{
			model.put("message", "found and deleted file: " + fileName);
			
			UserFileControl.folderExistsThenDelete("C:\\Users\\mikieJ\\Documents\\MSc_UserFolder\\" +  userName + "\\public\\" + fileName);			
			return "filedeleted";			
		}
		else
		{
			model.put("message", fileName + " was not found in the directory, thus it cannot be deleted");
			return "filedeleted";
		}
		
	}
	
	
	//references http://docs.spring.io/spring/docs/1.2.x/api/org/springframework/util/FileCopyUtils.html 
	//&& http://owlsayswoot.therandomist.com/2011/12/12/how-to-download-files-with-springmvc/ 15/07/2015)
	@RequestMapping(value = "/userpage/{userName}/{folderType}/download/{fileName:.+}",  method = RequestMethod.GET)
	public void downloadFile(HttpServletResponse response,  @PathVariable ("folderType") String folderType,  @PathVariable ("userName") String userName, @PathVariable ("fileName") String fileName, Map<String, Object> model) throws IOException 
	{
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
				    response.setContentType("fileType"); //in my example this was an xls file
			        response.setContentLength(new Long(fileToDownload.length()).intValue());
			        response.setHeader("Content-Disposition","attachment; filename=" + fileName);			 
			    try {
			            FileCopyUtils.copy(new FileInputStream(fileToDownload), response.getOutputStream());
			        }
			    catch (IOException e) 
			    	{
			            e.printStackTrace();
			        }
			        return;	
				}
		 
		            
	       }
	    }
	
	
	
	
	
}
