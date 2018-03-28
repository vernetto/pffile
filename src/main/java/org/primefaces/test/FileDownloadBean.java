package org.primefaces.test;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class FileDownloadBean {
    private String filename;
    
    public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		System.out.println("setting filename to " + filename);
		this.filename = filename;
	}
}
