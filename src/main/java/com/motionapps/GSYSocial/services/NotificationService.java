package com.motionapps.GSYSocial.services;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientResponse;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.springframework.stereotype.Controller;

import com.motionapps.GSYSocial.dao.vo.Notification;
import com.motionapps.GSYSocial.dao.vo.NotificationRequestVO;
import com.motionapps.GSYSocial.dao.vo.NotificationResposeVO;

@Controller
@Path("/")
public class NotificationService {
	
	private WebTarget target;
	private String BASE_URI="https://gcm-http.googleapis.com/gcm";
	private String key="AIzaSyAdVloxTUW2IlZ0BVjoGTm-AZLkQNLZOMc";
	
	@GET
	@Path("/test")
	public Response testNotifiwcation()
	{
		

		Client client = ClientBuilder.newBuilder()
						.register(JacksonFeature.class)
						.register(new LoggingFilter())
						.build();
		target = client.target(BASE_URI);
		Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
		invocationBuilder.header("Authorization", "key="+key);
		NotificationRequestVO notificationRequestVO= new NotificationRequestVO();
		Notification notification=new Notification();
		notification.setText("Hello");
		notification.setTitle("My First Notification");
		notificationRequestVO.setTo("ePywcaQp5lE:APA91bHBz__mS_ZZzqumEAnr9q-k9ylgT-7CGSsy8uq0g-UsMLWBjMzei2LN424cMaluPaQUQ6A4h2aQUxEE4j1lQx2mtgLUd56bTS95b44W1Ol7cSO97bl3L6xuRKETMRBbu9SII7Lp");
		notificationRequestVO.setNotification(notification);
		
		System.out.println(notificationRequestVO.toString());
//		Response responseMsg = target.path("/send").
//									request().accept(MediaType.APPLICATION_JSON)
//									.post(Entity.entity(notificationRequestVO,MediaType.APPLICATION_JSON),ClientResponse.class);
		NotificationResposeVO response = target.path("/send").request(MediaType.APPLICATION_JSON).header("Authorization", "key="+key).post(Entity.json(notificationRequestVO),NotificationResposeVO.class);
		//System.out.println(response);
		//return response;
		
		return Response.ok().entity(response).type(MediaType.APPLICATION_JSON).build();
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