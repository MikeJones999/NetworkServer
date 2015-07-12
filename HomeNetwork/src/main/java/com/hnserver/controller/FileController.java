package com.hnserver.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public String returnUserPage(@PathVariable String userName, Map<String, Object> model)
{
	 User temp = dataObject.getuserByName(userName);
 	 System.out.println("***DEBUG*** found userfileupload page for - " + temp.getUserName());
 	 model.put("user", temp);
 	 return "userfileupload"; 
}

	/**
	 * Handle the uploaded files and save into directory
	 * @param FileManager fileManager (of type Multipart)
	 * @param userName
	 * @param MAP<String Object> model
	 * @param Model mod
	 * @param BindingResults res
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "/userpage/{userName}/fileupload", method= RequestMethod.POST)
	public String fileUpload(@ModelAttribute("fileManager") FileManager fileManager, @PathVariable String userName, 
			Map<String, Object> model, Model mod, BindingResult res) throws IllegalStateException, IOException
	{
		//set the directory for where the file is to be saved
		String saveLocation = "C:\\Users\\mikieJ\\Documents\\MSc_UserFolder\\" +  userName + "\\public\\";
		System.out.println("***DEBUG*** point 1 -Saving to " + saveLocation);
		
		//get the populated list of files - these have been populated by beans via the xml userfileupload.jsp (autowired by name)
		 List<MultipartFile> manageFiles = fileManager.getFiles();
		 
		 //create a list of STrings to hold the names to display as a return of what has been saved
		 List<String> filesNames = new ArrayList<String>();
	
		 //Check to see if the List of files is empty - if so show error
		   if (manageFiles.isEmpty() ) {
		        //res.reject("noFile", "Please select file to upload");
			   System.out.println("***DEBUG*** error manageFiles is empty");
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
	            
	            
	            //multipartFile.getInputStream() ***** review
	            
	            
	            
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

	       }//for
	        	System.out.println("***DEBUG*** Completed upload");
	         	mod.addAttribute("files", filesNames);
			    return "fileuploaded";
	    }	       
		
	    System.out.println("***DEBUG*** manageFiles is null or greater thean 0");
	    return "userfileupload";	
	}
	

}
