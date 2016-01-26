package com.motionapps.GSYSocial.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.motionapps.GSYSocial.services.NotificationService;

@Controller
@Path("/notification")
public class NotificationController {
	
	@Autowired
	private NotificationService notificationService;

	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}
	
	@GET
	@Path("/getnotificationrequests")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNotificationRequests(@QueryParam("userId") String userId)
	{
		return Response.ok().entity(notificationService.getNotificationRequests(userId)).build();
	}
	


	@GET
	@Path("/getnotifications")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNotifications(@QueryParam("userId") String userId)
	{
		return Response.ok().entity(notificationService.getNotifications(userId)).build();
	}
	
	
}
