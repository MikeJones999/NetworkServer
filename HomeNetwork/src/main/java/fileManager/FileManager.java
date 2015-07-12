package fileManager;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FileManager {

	private List<MultipartFile> files;

	public List<MultipartFile> getFiles() {
		return files;
	}

	/**
	 * Uses @Beans to autowried these in - by name - therefore you need the correct format for the set method e.g files is variable
	 * setFiles is the method name
	 * @param file
	 */
	public void setFiles(List<MultipartFile> file) {
		this.files = file;
	}


	
}
