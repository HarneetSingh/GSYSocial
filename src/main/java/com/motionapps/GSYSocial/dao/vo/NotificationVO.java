package com.motionapps.GSYSocial.dao.vo;


public class NotificationVO {


	
	
	private String notificationId;
	
	private Boolean isRequest;
	
	private String userId;
	
	private Notification notification;
	
	private NotificationDataVO data;
	

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

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	public NotificationDataVO getData() {
		return data;
	}

	public void setData(NotificationDataVO data) {
		this.data = data;
	}

	public Boolean getIsRequest() {
		return isRequest;
	}

	public void setIsRequest(Boolean isRequest) {
		this.isRequest = isRequest;
	}

	public NotificationVO() {
	}
	
	public NotificationVO(String notificationId, Boolean isRequest, String userId, Notification notification,
			NotificationDataVO data) {
		super();
		this.notificationId = notificationId;
		this.isRequest = isRequest;
		this.userId = userId;
		this.notification = notification;
		this.data = data;
	}
		
	

}
