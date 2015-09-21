package com.motionapps.GSYSocial.services;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.web.filter.RequestContextFilter;

import com.motionapps.GSYSocial.controller.FileController;
import com.motionapps.GSYSocial.controller.UserController;


public class MyMainApplication extends ResourceConfig{
	
	public MyMainApplication() {
		register(RequestContextFilter.class);
		register(UserController.class);
		register(FileController.class);
		register(JacksonFeature.class);		
	}

	

}
