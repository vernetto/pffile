package org.primefaces.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
public class FileDownloadView {

	private StreamedContent file;

	@Inject
	FileDownloadBean fileDownloadBean;

	public FileDownloadView() throws FileNotFoundException {
		System.out.println("entering FileDownloadView");

		String filetodownload = FileUploadView.DOWNLOAD_FOLDER + File.separator + File.separator
				+ "primefaces_user_guide_6_2-6519923613760594571.pdf";
		File initialFile = new File(filetodownload);
		System.out.println("filetodownload " + filetodownload);
		//InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream(filetodownload);
		InputStream stream =  new FileInputStream(initialFile);
		System.out.println("stream " + stream);
		String contentType = FacesContext.getCurrentInstance().getExternalContext().getMimeType(filetodownload);
		System.out.println("contentType " + contentType);
		file = new DefaultStreamedContent(stream, contentType, filetodownload);
	}

	public StreamedContent getFile() {
		return file;
	}
}