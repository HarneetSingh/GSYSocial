package com.motionapps.GSYSocial.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.motionapps.GSYSocial.dao.vo.ChangePasswordVO;
import com.motionapps.GSYSocial.dao.vo.UserSearchVO;
import com.motionapps.GSYSocial.dao.vo.UserVO;
import com.motionapps.GSYSocial.services.UserService;

@Controller
@DependsOn("userDao")
@Path("/user")
public class UserController {


		
		@Autowired
		private UserService userService;
		
		Long status;

		public void setUserService(UserService userService) {
			this.userService = userService;
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
		@Path("/normalregisteration")
		@Consumes({MediaType.APPLICATION_JSON})
		@Transactional
		public Response normalregisteration(UserVO user) {
			
			UserVO userVO=userService.normalregisteration(user);
			if(userVO!=null)
				return Response.status(200).entity(userVO).type(MediaType.APPLICATION_JSON).build();
			else
				return Response.status(400).build();
			
		}	
		@POST 
		@Path("/oauthregisteration")
		@Consumes({MediaType.APPLICATION_JSON})
		@Transactional
		public Response oauthregisteration(UserVO user) {
			
			//System.out.println("Inside IF oauthregisteration");
			UserVO userVO=userService.oauthregisteration(user);
			System.out.println("Inside oauthregisteration"+ userVO.getUserId());

			if(userVO!=null)
				return Response.status(200).entity(userVO).type(MediaType.APPLICATION_JSON).build();
			else
				return Response.status(400).build();
			
		}	


		
		@POST 
		@Path("/update")
		@Consumes({MediaType.APPLICATION_JSON})
		@Transactional
		public Response updateUserDetails(UserVO user) {
			
			status=userService.updateUserDetails(user);
			if(status==1)
				return Response.status(200).build();
			else 
				return Response.status(400).build();
			
		}	
		
		/************************************ READ ************************************/
		/**
		 * Returns all resources (questions) from the database
		 * @return
		 */
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<UserVO> getUsers() {
			return userService.getUsers();
		}
		
		@GET
		@Path("/search")
		@Produces(MediaType.APPLICATION_JSON)
		public UserSearchVO searchUser(@QueryParam("keyword")String keyword) {
			return userService.searchUser(keyword);
		}
		@GET
		@Path("/delete")
		@Produces(MediaType.APPLICATION_JSON)
		public Response deleteUser(@QueryParam("userId")String userId) {
			status=userService.deleteUser(userId);
			if(status==1)
				return Response.ok().build();
			else
				return Response.status(400).build();

		}
		
		@GET
		@Path("/details")
		@Produces(MediaType.APPLICATION_JSON)
		public UserVO getUser(@QueryParam("userId")String userId) {
			return userService.getUser(userId);
		}
		

		@POST 
		@Consumes({MediaType.APPLICATION_JSON})
		@Produces({MediaType.APPLICATION_JSON})
		@Transactional
		@Path("/login")
		public Response loginUser(UserVO user) {
			
			UserVO userVO=userService.loginUser(user);
			if(userVO!=null)
				return Response.ok().entity(userVO).build();
			else
				return Response.status(401).build();
		}	
		
		@POST 
		@Consumes({MediaType.APPLICATION_JSON})
		@Transactional
		@Path("/changePassword")
		public Response changePassword(ChangePasswordVO changePasswordVO)
		{
			
			status=userService.changePassword(changePasswordVO);
			if(status==1)
				return Response.ok().build();
			else
				return Response.status(401).build();

		}
		
		
		
		
}	