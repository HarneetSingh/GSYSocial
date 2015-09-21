package com.motionapps.GSYSocial.dao.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class FileVO implements Serializable{
	
	
	private String fileId;
	private String fileName;
	private String fileType;
	private String fileUrl;
	private int fileSize;
	private byte[] fileContent;
	
	public FileVO(){}
	


	
	
	public FileVO(String fileId, String fileName, String fileType,
			String fileUrl, int fileSize, byte[] fileContent) {
		super();
		this.fileId = fileId;
		this.fileName = fileName;
		this.fileType = fileType;
		this.fileUrl = fileUrl;
		this.fileSize = fileSize;
		this.fileContent = fileContent;
	}





	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int length) {
		this.fileSize = length;
	}
	public byte[] getFileContent() {
		return fileContent;
	}
	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}
	
	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

}
