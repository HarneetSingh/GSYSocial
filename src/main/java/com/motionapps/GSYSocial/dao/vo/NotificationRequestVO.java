package com.motionapps.GSYSocial.dao.vo;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NotificationRequestVO {

	
	private String to;
	
	private Notification notification;
	
	private NotificationDataVO data;
	
	
	public NotificationDataVO getData() {
		return data;
	}


	public void setData(NotificationDataVO data) {
		this.data = data;
	}


	public String getTo() {
		return to;
	}


	public void setTo(String to) {
		this.to = to;
	}


	public Notification getNotification() {
		return notification;
	}


	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	@Override
	public String toString() {
		
		return "{ to :"+to+notification.toString()+data.toString()+ "}";
	}
	
	
}
