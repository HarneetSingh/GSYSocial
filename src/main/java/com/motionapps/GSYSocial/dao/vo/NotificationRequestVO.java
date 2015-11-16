package com.motionapps.GSYSocial.dao.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NotificationRequestVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9090281447661486633L;
	
	private String to;
	
	private Notification notification;
	
	
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


	
	
}
