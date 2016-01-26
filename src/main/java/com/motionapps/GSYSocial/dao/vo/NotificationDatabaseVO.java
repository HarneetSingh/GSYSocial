package com.motionapps.GSYSocial.dao.vo;

public class NotificationDatabaseVO {
	
	private String notificationId;
	
	private Boolean isRequest;
	
	public Boolean getIsRequest() {
		return isRequest;
	}

	public void setIsRequest(Boolean isRequest) {
		this.isRequest = isRequest;
	}

	private String userId;
	
	private String notification;
	
	private String data;

	public String getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	public NotificationDatabaseVO() {
	}

	public NotificationDatabaseVO(String notificationId, Boolean isRequest, String userId, String notification,
			String data) {
		super();
		this.notificationId = notificationId;
		this.isRequest = isRequest;
		this.userId = userId;
		this.notification = notification;
		this.data = data;
	}









	
}
