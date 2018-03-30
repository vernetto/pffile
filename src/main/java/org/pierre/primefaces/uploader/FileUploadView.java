package org.pierre.primefaces.uploader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
 
@ManagedBean
public class FileUploadView {
     
    public static final String DOWNLOAD_FOLDER_WIN = "d:\\temp";
    public static final String DOWNLOAD_FOLDER_LINUX = "/var/tmp";
    public static final String os = System.getProperty("os.name");
	private UploadedFile file;
	
	public static String getTempFolder() {
		if (os.contains("Windows")) {
			return DOWNLOAD_FOLDER_WIN;
		}
		else {
			return DOWNLOAD_FOLDER_LINUX;
		}
	}
	

	public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
     
    public void upload() throws IOException {
    	
    	System.out.println("OS=" + os);
    	String fileName = file.getFileName();
    	String contentType = file.getContentType();
    	System.out.println("fileName " + fileName + " contentType " + contentType);
    	String filename = FilenameUtils.getBaseName(file.getFileName()); 
    	String extension = FilenameUtils.getExtension(file.getFileName());
    	Path fileNew = Paths.get(getTempFolder(), file.getFileName());
    	try (InputStream input = file.getInputstream()) {
    	    Files.copy(input, fileNew, StandardCopyOption.REPLACE_EXISTING);
    	}
    	System.out.println("Uploaded file successfully saved in " + fileNew);
        if(file != null) {
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void handleFileUpload(FileUploadEvent event) {
    	System.out.println("handleFileUpload " + event.getFile());
    	
    }
}