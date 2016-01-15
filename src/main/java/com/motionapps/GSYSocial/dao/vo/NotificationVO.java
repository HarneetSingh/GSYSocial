package com.motionapps.GSYSocial.dao.vo;


public class NotificationVO {


	
	private String notificationId;
	
	private String gcmDeviceId;
	
	private String notificationData;
	
	private Boolean notificationStatus;

	public Boolean getNotificationStatus() {
		return notificationStatus;
	}

	public void setNotificationStatus(Boolean notificationStatus) {
		this.notificationStatus = notificationStatus;
	}

	public String getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}

	public String getGcmDeviceId() {
		return gcmDeviceId;
	}

	public void setGcmDeviceId(String gcmDeviceId) {
		this.gcmDeviceId = gcmDeviceId;
	}

	public String getNotificationData() {
		return notificationData;
	}

	public void setNotificationData(String notificationData) {
		this.notificationData = notificationData;
	}
	

	public NotificationVO(String notificationId, String gcmDeviceId,
			String notificationData, Boolean notificationStatus) {
		super();
		this.notificationId = notificationId;
		this.gcmDeviceId = gcmDeviceId;
		this.notificationData = notificationData;
		this.notificationStatus = notificationStatus;
	}

		
	

}
