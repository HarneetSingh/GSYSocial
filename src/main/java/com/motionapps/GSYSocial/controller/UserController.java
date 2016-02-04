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

import com.motionapps.GSYSocial.dao.UserDao;
import com.motionapps.GSYSocial.dao.vo.ChangePasswordVO;
import com.motionapps.GSYSocial.dao.vo.ErrorVO;
import com.motionapps.GSYSocial.dao.vo.UserSearchVO;
import com.motionapps.GSYSocial.dao.vo.UserVO;
import com.motionapps.GSYSocial.services.EmailService;
import com.motionapps.GSYSocial.services.UserService;

@Controller
@DependsOn("userDao")
@Path("/user")
public class UserController {


		
		@Autowired
		private UserService userService;
		
		@Autowired
		private EmailService emailService;
		
		Long status;

		public void setUserService(UserService userService) {
			this.userService = userService;
		}
	
		public void setEmailService(EmailService emailService) {
			this.emailService = emailService;
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
			
			Object object=userService.normalregisteration(user);
			if(object instanceof UserVO)
			{	UserVO userVO=(UserVO)object;
				return Response.status(200).entity(userVO).type(MediaType.APPLICATION_JSON).build();
			}
			else if(object instanceof ErrorVO)
			{
				ErrorVO errorVO=(ErrorVO)object;
				return Response.status(401).entity(errorVO).type(MediaType.APPLICATION_JSON).build();
			}
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
		
		
		@GET
		@Path("/verifyemailaddress")
		public Response verifyEmailAddress(@QueryParam("userId") String userId, @QueryParam("emailId") String emailId )
		{
			//System.out.println("Inside IF verifyEmailAddress");

			status=userService.verifyEmailAddress(userId, emailId);
			if(status==1)
			{
				return Response.status(200).entity("EmailId Successfully Verified").type(MediaType.TEXT_PLAIN).build();
			}
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
			return new UserSearchVO(userService.searchUser(keyword));
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
			
			Object object=userService.loginUser(user);
			if(object instanceof UserVO)
			{
				UserVO userVO=(UserVO) object;
				return Response.ok().entity(userVO).build();
			}
			else if(object instanceof ErrorVO)
			{
				ErrorVO errorVO=(ErrorVO)object;
				return Response.status(errorVO.getStatus()).entity(errorVO).build();
			}
			else 
				return Response.status(400).build();
		}	
		
		@GET
		@Path("/forgetPassword")
		public Response forgetPassword(@QueryParam("emailId")String emailId)
		{
			status=userService.forgetPassword(emailId);
			if(status==1)
				return Response.ok().build();
			else
				return Response.status(400).build();
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
		
		@GET
		@Path("/getAccounts")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getAccountsDetails(@QueryParam("userId")String userId)
		{
			return Response.status(200).entity(userService.getAccountsDetails(userId)).build();
		}
		
		@GET
		@Path("/searchUsersAccount")
		@Produces(MediaType.APPLICATION_JSON)
		public Response searchUsersAccount(@QueryParam("keyword")String keyword)
		{
			return Response.status(200).entity(userService.searchUsersAccount(keyword)).build();
		}
		
		
		@GET
		@Path("/testEmail")
		public Response testEmail()
		{
			status=emailService.sendEmail("harneetsingh17@gmail.com","test","test");
			if(status==1)
				return Response.ok().build();
			else
				return Response.status(400).build();
			
		}
}	