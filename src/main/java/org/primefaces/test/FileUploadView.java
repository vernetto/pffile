package org.primefaces.test;

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
     
    public static final String DOWNLOAD_FOLDER = "d:\\temp";
	private UploadedFile file;
	

    
    //private String filename;
 
//    public String getFilename() {
//		return filename;
//	}
//
//	public void setFilename(String filename) {
//		this.filename = filename;
//	}

	public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
     
    public void upload() throws IOException {
    	String fileName = file.getFileName();
    	String contentType = file.getContentType();
    	System.out.println("fileName " + fileName + " contentType " + contentType);
    	//Path folder = FileSystems.getDefault().getPath(".");
    	Path folder = Paths.get(DOWNLOAD_FOLDER);
    	String filename = FilenameUtils.getBaseName(file.getFileName()); 
    	String extension = FilenameUtils.getExtension(file.getFileName());
    	Path fileNew = Files.createTempFile(folder, filename + "-", "." + extension);
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