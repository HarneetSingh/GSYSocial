package com.motionapps.GSYSocial.services;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.springframework.beans.factory.annotation.Autowired;

import com.motionapps.GSYSocial.dao.NotificationDao;
import com.motionapps.GSYSocial.dao.vo.Notification;
import com.motionapps.GSYSocial.dao.vo.NotificationArrayVO;
import com.motionapps.GSYSocial.dao.vo.NotificationDataVO;
import com.motionapps.GSYSocial.dao.vo.NotificationDatabaseVO;
import com.motionapps.GSYSocial.dao.vo.NotificationRequestVO;
import com.motionapps.GSYSocial.dao.vo.NotificationResponseVO;
import com.motionapps.GSYSocial.dao.vo.NotificationVO;


public class NotificationService {
	
	private WebTarget target;
	private String BASE_URI="https://gcm-http.googleapis.com/gcm";
	private String key="AIzaSyBPypcdN8uju8VBNe6HVvsoLrqzzp9oNFo";//"AIzaSyAdVloxTUW2IlZ0BVjoGTm-AZLkQNLZOMc";
	
	
	@Autowired
	private NotificationDao notificationDao;
	
	

	public void setNotificationDao(NotificationDao notificationDao) {
		this.notificationDao = notificationDao;
	}

	
//	@GET
//	@Path("/test")
//	public Response testNotifiwcation()
//	{
//		
//
//		Client client = ClientBuilder.newBuilder()
//						.register(JacksonFeature.class)
//						.register(new LoggingFilter())
//						.build();
//		target = client.target(BASE_URI);
//		Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
//		invocationBuilder.header("Authorization", "key="+key);
//		NotificationRequestVO notificationRequestVO= new NotificationRequestVO();
//		Notification notification=new Notification();
//		notification.setText("Hello");
//		notification.setTitle("My First Notification");
//		notificationRequestVO.setTo("ePywcaQp5lE:APA91bHBz__mS_ZZzqumEAnr9q-k9ylgT-7CGSsy8uq0g-UsMLWBjMzei2LN424cMaluPaQUQ6A4h2aQUxEE4j1lQx2mtgLUd56bTS95b44W1Ol7cSO97bl3L6xuRKETMRBbu9SII7Lp");
//		notificationRequestVO.setNotification(notification);
//		
////		System.out.println(notificationRequestVO.toString());
////		Response responseMsg = target.path("/send").
////									request().accept(MediaType.APPLICATION_JSON)
////									.post(Entity.entity(notificationRequestVO,MediaType.APPLICATION_JSON),ClientResponse.class);
//		NotificationResponseVO response = target.path("/send").request(MediaType.APPLICATION_JSON).header("Authorization", "key="+key).post(Entity.json(notificationRequestVO),NotificationResponseVO.class);
//		//System.out.println(response);
//		//return response;
//		
//		return Response.ok().entity(response).type(MediaType.APPLICATION_JSON).build();
//	}
//	
	
	public NotificationResponseVO sendNotification(NotificationRequestVO notificationRequestVO)
	{
		Client client = ClientBuilder.newBuilder()
				.register(JacksonFeature.class)
				.register(new LoggingFilter())
				.build();
		target = client.target(BASE_URI);
		Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
		invocationBuilder.header("Authorization", "key="+key);
		ObjectMapper objectMapper=new ObjectMapper();
		try {
			System.out.println(objectMapper.writeValueAsString(notificationRequestVO));
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NotificationResponseVO notificationResponseVO = target.path("/send").request(MediaType.APPLICATION_JSON).header("Authorization", "key="+key).post(Entity.json(notificationRequestVO),NotificationResponseVO.class);
		//saveNotificationInDatabase(notificationRequestVO);
		return notificationResponseVO;
	}
	
	public NotificationRequestVO createNotificationObject(String to,Notification notification,NotificationDataVO notificationDataVO)
	{

		NotificationRequestVO notificationRequestVO=new NotificationRequestVO();
		notificationRequestVO.setTo(to);

		notificationRequestVO.setNotification(notification);
		notificationRequestVO.setData(notificationDataVO);
		ObjectMapper objectMapper=new ObjectMapper();
		try {
			System.out.println(objectMapper.writeValueAsString(notificationRequestVO));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		saveNotificationInDatabase(notificationRequestVO);
		return notificationRequestVO;
	}
	
	
	private Long saveNotificationInDatabase(NotificationRequestVO notificationRequestVO)
	{
		ObjectMapper objectMapper=new ObjectMapper();
		NotificationDatabaseVO notificationDatabaseVO=new NotificationDatabaseVO();
		notificationDatabaseVO.setNotificationId(UUID.randomUUID().toString());
		notificationDatabaseVO.setUserId(notificationRequestVO.getData().getUserIdTo());
		notificationDatabaseVO.setIsRequest (notificationRequestVO.getData().getIsRequest());
	
		try {
			notificationDatabaseVO.setData(objectMapper.writeValueAsString(notificationRequestVO.getData()));
			notificationDatabaseVO.setNotification(objectMapper.writeValueAsString(notificationRequestVO.getNotification()));

		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return notificationDao.saveNotificationInDatabase(notificationDatabaseVO);
		}

	public NotificationArrayVO getNotificationRequests(String userId)
	{
		ObjectMapper objectMapper=new ObjectMapper();
		List<NotificationDatabaseVO> notificationDatabaseVOs= notificationDao.getNotificationRequests(userId);
		List<NotificationVO> notificationVOs=new ArrayList<NotificationVO>();
		for(NotificationDatabaseVO notificationDatabaseVO : notificationDatabaseVOs)
		{
			NotificationVO notificationVO=new NotificationVO();
			notificationVO.setNotificationId(notificationDatabaseVO.getNotificationId());
			notificationVO.setIsRequest(notificationDatabaseVO.getIsRequest());
			notificationVO.setUserId(notificationDatabaseVO.getUserId());
			try {
				notificationVO.setData(objectMapper.readValue(notificationDatabaseVO.getData(),NotificationDataVO.class ));
				notificationVO.setNotification(objectMapper.readValue(notificationDatabaseVO.getNotification(),Notification.class ));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			notificationVOs.add(notificationVO);
					
		}
	
		return new NotificationArrayVO(notificationVOs);
	}
	
	public NotificationArrayVO getNotifications(String userId)
	{
		ObjectMapper objectMapper=new ObjectMapper();
		List<NotificationDatabaseVO> notificationDatabaseVOs= notificationDao.getNotifications(userId);
		List<NotificationVO> notificationVOs=new ArrayList<NotificationVO>();
		for(NotificationDatabaseVO notificationDatabaseVO : notificationDatabaseVOs)
		{
			NotificationVO notificationVO=new NotificationVO();
			notificationVO.setNotificationId(notificationDatabaseVO.getNotificationId());
			notificationVO.setIsRequest(notificationDatabaseVO.getIsRequest());
			notificationVO.setUserId(notificationDatabaseVO.getUserId());
			try {
				notificationVO.setData(objectMapper.readValue(notificationDatabaseVO.getData(),NotificationDataVO.class ));
				notificationVO.setNotification(objectMapper.readValue(notificationDatabaseVO.getNotification(),Notification.class ));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			notificationVOs.add(notificationVO);
					
		}
	
		return new NotificationArrayVO(notificationVOs);
	}
	
	public Long removeNotificationRequest(String notificationId)
	{
		return notificationDao.removeNotificationRequest(notificationId);
	}
	
	public Long deleteNotificationsOfUserId(String userId)
	{
		return notificationDao.deleteNotificationsOfUserId(userId);
	}
}


/*
Authorization   key=AIzaSyAdVloxTUW2IlZ0BVjoGTm-AZLkQNLZOMc

{ "notification": {
"title": "Portugal vs. Denmark",
"text": "5 to 1"
},
"to" : "fj4Esj0ELoo:APA91bGB_EwwA09e6qe2udz7yw6yU-qGs3hQYsIc3Igt3_JplrGSz35DvKl_UoBVaVVZIIbmw9uDHeCvTwLQO1uDVboNGQ8AgG59bBBruWIoQJs9dmKKTNap7gXEDxuetk3Rvmtscq1i"
}


{ "notification": {
    "title": "Portugal vs. Denmark",
    "text": "5 to 1"
  },
  "to" : "ePywcaQp5lE:APA91bHBz__mS_ZZzqumEAnr9q-k9ylgT-7CGSsy8uq0g-UsMLWBjMzei2LN424cMaluPaQUQ6A4h2aQUxEE4j1lQx2mtgLUd56bTS95b44W1Ol7cSO97bl3L6xuRKETMRBbu9SII7Lp"
}

*/