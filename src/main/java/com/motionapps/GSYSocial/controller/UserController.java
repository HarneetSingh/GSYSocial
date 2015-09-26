package com.motionapps.GSYSocial.controller;

import java.util.List;
import java.util.UUID;

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
import com.motionapps.GSYSocial.dao.vo.JointAccountVO;
import com.motionapps.GSYSocial.dao.vo.UserSearchVO;
import com.motionapps.GSYSocial.dao.vo.UserVO;

@Controller
@DependsOn("userDao")
@Path("/user")
public class UserController {


		
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
		@Transactional
		public Response createUser(UserVO user) {
			
			int temp=userDao.checkIfEmailIdAlreadyExists(user.getEmailId());
			if(temp>=1)
			{
				ErrorVO errorVO=new ErrorVO("EmailId already registered");
				return Response.status(400).entity(errorVO).type(MediaType.APPLICATION_JSON).build();
			}
//			else{
//				temp=userDao.checkIfUserNameAlreadyExists(user.getUserName());
//				if(temp>=1)
//				{
//					ErrorVO errorVO=new ErrorVO("User Name already registered");
//					return Response.status(400).entity(errorVO).type(MediaType.APPLICATION_JSON).build();
//				}
//			}
			
			String sessionId=UUID.randomUUID().toString();
			user.setSessionId(sessionId);
			
			Long abc=userDao.createUser(user);
			if(abc==1)
			{
//				UserVO tempUser=new UserVO();
//				tempUser.setSessionId(sessionId);
				user.setPassword(null);
				return Response.status(200).entity(user).type(MediaType.APPLICATION_JSON).build();
			}
			else
			{
				ErrorVO errorVO=new ErrorVO("Internal Server Error");
				return Response.status(400).entity(errorVO).type(MediaType.APPLICATION_JSON).build();
			}	
			
		}	
		
		public Long updateJointAccountDetails(JointAccountVO jointAccountVO)
		{
			UserVO userVO=new UserVO();
			userVO.setEmailId(jointAccountVO.getFirstEmailId());
			userVO.setJointAccountId(jointAccountVO.getJointAccountId());
			updateUserDetails(userVO);
			userVO.setEmailId(jointAccountVO.getSecondEmailId());
			updateUserDetails(userVO);
			return (long)1;
		}

		
		@POST 
		@Path("/update")
		@Consumes({MediaType.APPLICATION_JSON})
		@Transactional
		public Response updateUserDetails(UserVO user) {
			

//			int	temp=userDao.checkIfUserNameAlreadyExists(user.getUserName());
//			if(temp>=1)
//			{
//				ErrorVO errorVO=new ErrorVO("User Name already registered");
//				return Response.status(400).entity(errorVO).type(MediaType.APPLICATION_JSON).build();
//			}
			userDao.updateUser(user);
//			if(abc==1)
//			{

				return Response.status(200).build();
//			}
//			else
//			return Response.status(400).entity("Internal Server Error").build();	
			
		}	
		
		/************************************ READ ************************************/
		/**
		 * Returns all resources (questions) from the database
		 * @return
		 */
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<UserVO> getUsers() {
			return userDao.getUsers();
		}
		
		@GET
		@Path("/search")
		@Produces(MediaType.APPLICATION_JSON)
		public UserSearchVO searchUser(@QueryParam("keyword")String keyword) {
			return new UserSearchVO(userDao.searchUser("%"+keyword+"%"));
		}
		
		@GET
		@Path("/details")
		@Produces(MediaType.APPLICATION_JSON)
		public UserVO getUser(@QueryParam("emailId")String emailId) {
			return userDao.getUser(emailId);
		}
		
//		public UserVO getUserDetails(String emailId) {
//			return userDao.getUser(emailId);
//		}
//		
		@POST 
		@Consumes({MediaType.APPLICATION_JSON})
		@Transactional
		@Path("/login")
		public Response loginUser(UserVO user) {
			
			String emailId=user.getEmailId();
			String password =userDao.getPassword(emailId);
			if(password!=null)
			{	
				if(password.equals(user.getPassword()))
				{
				String sessionId=UUID.randomUUID().toString();
				user.setSessionId(sessionId);
				userDao.updateSessionId(user);
				user.setPassword(null);
				user=userDao.getUser(emailId);
				return Response.status(200).entity(user).type(MediaType.APPLICATION_JSON).build();
				}
				else
				{
					ErrorVO errorVO=new ErrorVO("Authentication Failed");
					return Response.status(401).entity(errorVO).type(MediaType.APPLICATION_JSON).build();
				}	
			}
			else
			{
				ErrorVO errorVO=new ErrorVO("Authentication Failed");
				return Response.status(401).entity(errorVO).type(MediaType.APPLICATION_JSON).build();
			}	
			
		}	
		
		@POST 
		@Consumes({MediaType.APPLICATION_JSON})
		@Transactional
		@Path("/changePassword")
		public Response changePassword(ChangePasswordVO changePasswordVO)
		{
			if(changePasswordVO.getOldPassword().equals(userDao.getPassword(changePasswordVO.getEmailId())))
			{
				userDao.updatePassword(changePasswordVO);
				return Response.ok().build();
			}
			else{
				ErrorVO errorVO=new ErrorVO("Authentication Failed");
				return Response.status(401).entity(errorVO).type(MediaType.APPLICATION_JSON).build();
			}

		}
		
		
}	