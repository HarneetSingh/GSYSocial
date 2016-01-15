package com.motionapps.GSYSocial.dao.vo;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NotificationDataVO {
	
	


	private int notificationType;
	
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

	public NotificationDataVO(int notificationType, Object dataObject) {
		super();
		this.notificationType = notificationType;
		this.dataObject = dataObject;
	}
	

	
}
