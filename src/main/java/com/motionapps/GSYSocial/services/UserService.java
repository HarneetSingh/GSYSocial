package com.motionapps.GSYSocial.services;

import java.util.List;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.motionapps.GSYSocial.dao.UserDao;
import com.motionapps.GSYSocial.entities.User;


@Controller
@DependsOn("userDao")
@Path("/user")
public class UserService {
	
	@Autowired
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	/************************************ CREATE ************************************/


	/**
	 * Adds a new resource (question) from the given json format (at least title and feed elements are required
	 * at the DB level)
	 * 
	 * @param question
	 * @return
	 */
	@POST 
	@Path("/register")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.TEXT_HTML})
	@Transactional
	public Response createUser(User user) {
		
		String sessionId=UUID.randomUUID().toString();
		user.setSessionId(sessionId);
		Long abc=userDao.createUser(user);
		if(abc==1)
		{
			User tempUser=new User();
			tempUser.setSessionId(sessionId);
			return Response.status(201).entity(tempUser).type(MediaType.APPLICATION_JSON).build();
		}
		else
		return Response.status(400).entity("Internal Server Error").build();	
		
	}	
	
	/************************************ READ ************************************/
	/**
	 * Returns all resources (questions) from the database
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers() {
		return userDao.getUsers();
	}
	
	@POST 
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.TEXT_HTML})	
	@Transactional
	@Path("/login")
	public Response loginUser(User user) {
		
		String password =userDao.getPassword(user.getEmailId());
		if(password.equals(user.getPassword()))
		{
			String sessionId=UUID.randomUUID().toString();
			User tempUser=new User();
			tempUser.setSessionId(sessionId);
			userDao.updateSessionId(tempUser);
			return Response.status(201).entity(tempUser).type(MediaType.APPLICATION_JSON).build();
		}
		else
		return Response.status(401).entity("Authentication Failed").build();	
		
	}	
	
	
	
	
	
}
