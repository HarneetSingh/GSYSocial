package com.motionapps.GSYSocial.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Controller;

@Controller
@Path("/")
public class NotificationService {
	
	private WebTarget target;
	private String BASE_URI="http://ec2-54-200-61-8.us-west-2.compute.amazonaws.com/GFYSocial";
	
	@GET
	@Path("/test")
	public Response testNotification()
	{
		Client client = ClientBuilder.newClient();
		target = client.target(BASE_URI);
		String responseMsg = target.path("/user").request().get(String.class);
		return Response.ok().entity(responseMsg).build();
	}

}
