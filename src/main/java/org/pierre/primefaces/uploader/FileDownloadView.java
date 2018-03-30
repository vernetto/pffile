package org.pierre.primefaces.uploader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;

import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@SessionScoped
public class FileDownloadView {
	private String filename;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		System.out.println("setting filename to " + filename);
		this.filename = filename;
	}

	private StreamedContent file;

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public FileDownloadView() throws FileNotFoundException {

	}

	public void download() throws FileNotFoundException {
		System.out.println("entering FileDownloadView");
		String filetodownload = FileUploadView.getTempFolder() + File.separator + File.separator + getFilename();
		File initialFile = new File(filetodownload);
		System.out.println("filetodownload " + filetodownload);
		// InputStream stream =
		// FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream(filetodownload);
		InputStream stream = new FileInputStream(initialFile);
		System.out.println("stream " + stream);
		String contentType = FacesContext.getCurrentInstance().getExternalContext().getMimeType(filetodownload);
		System.out.println("contentType " + contentType);
		file = new DefaultStreamedContent(stream, contentType, getFilename());
	}

	public StreamedContent getFile() {
		return file;
	}
}