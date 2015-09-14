package com.motionapps.GSYSocial.services;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.web.filter.RequestContextFilter;


public class MyMainApplication extends ResourceConfig{
	
	public MyMainApplication() {
		register(RequestContextFilter.class);
		register(UserService.class);
		register(JacksonFeature.class);		
	}

	

}
