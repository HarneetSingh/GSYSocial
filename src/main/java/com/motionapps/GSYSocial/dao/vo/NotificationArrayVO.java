package com.motionapps.GSYSocial.dao.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NotificationArrayVO {
	
	
	private List<NotificationVO> notifications;
	
	public NotificationArrayVO(List<NotificationVO> notifications) {
		super();
		this.notifications = notifications;
	}

	public List<NotificationVO> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<NotificationVO> notifications) {
		this.notifications = notifications;
	}


	

}
