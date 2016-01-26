package com.motionapps.GSYSocial.dao;

import java.util.List;

import com.motionapps.GSYSocial.dao.vo.NotificationDatabaseVO;


public interface NotificationDao {
	
	public Long saveNotificationInDatabase(NotificationDatabaseVO notificationDatabaseVO);
	
	public List<NotificationDatabaseVO> getNotificationRequests(String userId);
	
	public List<NotificationDatabaseVO> getNotifications(String userId);
	
	public Long removeNotificationRequest(String notificationId);


}
