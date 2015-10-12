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
		@Path("/normalregisteration")
		@Consumes({MediaType.APPLICATION_JSON})
		@Transactional
		public Response normalregisteration(UserVO user) {
			
			if((user.getEmailId()==null)||(user.getEmailId().equals(""))||(user.getPassword().equals(""))||(user.getPassword()==null))
				return Response.status(400).build();
			int temp=userDao.checkIfEmailIdAlreadyExists(user.getEmailId());
			if(temp>=1)
			{
				ErrorVO errorVO=new ErrorVO("EmailId already registered");
				return Response.status(400).entity(errorVO).type(MediaType.APPLICATION_JSON).build();
			}
			
			String userId=UUID.randomUUID().toString();
			user.setSessionId(userId);
			user.setUserId(userId);
			
			Long abc=userDao.createUser(user);
			if(abc==1)
			{
				user.setPassword(null);
				return Response.status(200).entity(user).type(MediaType.APPLICATION_JSON).build();
			}
			else
			{
				ErrorVO errorVO=new ErrorVO("Internal Server Error");
				return Response.status(400).entity(errorVO).type(MediaType.APPLICATION_JSON).build();
			}	
			
		}	
		@POST 
		@Path("/oauthregisteration")
		@Consumes({MediaType.APPLICATION_JSON})
		@Transactional
		public Response oauthregisteration(UserVO user) {
			
			if((user.getOauthProvider()==null)||(user.getOauthProvider().equals(""))||(user.getOauthUid().equals(""))||(user.getOauthUid()==null))
				return Response.status(400).build();
			int temp=userDao.checkIfOauthUidAlreadyExists(user.getOauthUid());
			if(temp>=1)
			{

				UserVO reponse = userDao.getUserByOauthUid(user.getOauthUid());
				return Response.status(200).entity(reponse).type(MediaType.APPLICATION_JSON).build();
			}
			
			String userId=UUID.randomUUID().toString();
			user.setSessionId(userId);
			user.setUserId(userId);
			
			Long abc=userDao.createUser(user);
			if(abc==1)
			{
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
			userVO.setUserId(jointAccountVO.getFirstUserId());
			userVO.setJointAccountId(jointAccountVO.getJointAccountId());
			updateUserDetails(userVO);
			userVO.setUserId(jointAccountVO.getSecondUserId());
			updateUserDetails(userVO);
			return (long)1;
		}

		
		@POST 
		@Path("/update")
		@Consumes({MediaType.APPLICATION_JSON})
		@Transactional
		public Response updateUserDetails(UserVO user) {
			
			userDao.updateUser(user);
			return Response.status(200).build();

			
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
		public UserVO getUser(@QueryParam("userId")String userId) {
			return userDao.getUser(userId);
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
				//String sessionId=UUID.randomUUID().toString();
				//user.setSessionId(sessionId);
				//userDao.updateSessionId(user);
				user.setPassword(null);
				user=userDao.getUserByEmailId(emailId);
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
			if(changePasswordVO.getOldPassword().equals(userDao.getPassword(changePasswordVO.getUserId())))
			{
				userDao.updatePassword(changePasswordVO);
				return Response.ok().build();
			}
			else{
				ErrorVO errorVO=new ErrorVO("Authentication Failed");
				return Response.status(401).entity(errorVO).type(MediaType.APPLICATION_JSON).build();
			}

		}
		
		public Long incrementFollowCount(String emailId)
		{
			userDao.incrementFollowCount(emailId);
			return (long)1;
		}
		
		public Long decrementFollowCount(String emailId)
		{
			userDao.decrementFollowCount(emailId);
			return (long)1;
		}
		
		
}	