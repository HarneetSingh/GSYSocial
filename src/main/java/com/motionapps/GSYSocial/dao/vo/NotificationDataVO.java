package com.motionapps.GSYSocial.dao.vo;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NotificationDataVO {
	
	


	private int notificationType;
	
	private String userIdTo;
	
	private Boolean isRequest;

	public Boolean getIsRequest() {
		return isRequest;
	}

	public void setIsRequest(Boolean isRequest) {
		this.isRequest = isRequest;
	}

	public String getUserIdTo() {
		return userIdTo;
	}

	public void setUserIdTo(String userIdTo) {
		this.userIdTo = userIdTo;
	}

	private Object dataObject;

	public int getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(int notificationType) {
		this.notificationType = notificationType;
	}

	public Object getDataObject() {
		return dataObject;
	}

	public void setDataObject(Object dataObject) {
		this.dataObject = dataObject;
	}
	public NotificationDataVO() {
		// TODO Auto-generated constructor stub
	}
	public NotificationDataVO(int notificationType, String userIdTo, Boolean isRequest, Object dataObject) {
		super();
		this.notificationType = notificationType;
		this.userIdTo = userIdTo;
		this.isRequest = isRequest;
		this.dataObject = dataObject;
	}



}
