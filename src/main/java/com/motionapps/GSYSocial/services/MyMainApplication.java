package com.motionapps.GSYSocial.services;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.web.filter.RequestContextFilter;

import com.motionapps.GSYSocial.controller.CommentController;
import com.motionapps.GSYSocial.controller.FileController;
import com.motionapps.GSYSocial.controller.FollowerController;
import com.motionapps.GSYSocial.controller.GroupAccountController;
import com.motionapps.GSYSocial.controller.InviteRequestController;
import com.motionapps.GSYSocial.controller.JointAccountController;
import com.motionapps.GSYSocial.controller.NotificationController;
import com.motionapps.GSYSocial.controller.PostController;
import com.motionapps.GSYSocial.controller.RatingController;
import com.motionapps.GSYSocial.controller.UserController;


public class MyMainApplication extends ResourceConfig{
	
	public MyMainApplication() {
		register(RequestContextFilter.class);
		register(UserController.class);
		register(FileController.class);
		register(InviteRequestController.class);
		register(JointAccountController.class);
		register(FollowerController.class);
		register(PostController.class);
		register(CommentController.class);
		register(RatingController.class);
		register(NotificationController.class);
		register(GroupAccountController.class);
		register(JacksonFeature.class);		
	}

	

}
