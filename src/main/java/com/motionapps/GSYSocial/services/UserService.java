package com.motionapps.GSYSocial.services;



import java.util.List;
import java.util.UUID;



import org.springframework.beans.factory.annotation.Autowired;

import com.motionapps.GSYSocial.dao.UserDao;
import com.motionapps.GSYSocial.dao.vo.ChangePasswordVO;
import com.motionapps.GSYSocial.dao.vo.JointAccountVO;
import com.motionapps.GSYSocial.dao.vo.UserSearchVO;
import com.motionapps.GSYSocial.dao.vo.UserVO;



public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	private UserVO userVO;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	
	public UserVO normalregisteration(UserVO user) {
		
		if((user.getEmailId()==null)||(user.getEmailId().equals(""))||(user.getPassword().equals(""))||(user.getPassword()==null))
			return userVO;
		int temp=userDao.checkIfEmailIdAlreadyExists(user.getEmailId());
		if(temp>=1)
		{

			return userVO;
		}
		
		String userId=UUID.randomUUID().toString();
		user.setSessionId(userId);
		user.setUserId(userId);
		
		Long abc=userDao.createUser(user);
		if(abc==1)
		{
			user.setPassword(null);
			return user;
		}
		else
		{
			return userVO;
		}	
		
	}	
	
	public UserVO oauthregisteration(UserVO user) {
		
	//	System.out.println("Inside oauthregisteration");

		if((user.getOauthProvider()==null)||(user.getOauthProvider().equals(""))||(user.getOauthUid().equals(""))||(user.getOauthUid()==null))
			return userVO;
		int temp=userDao.checkIfOauthUidAlreadyExists(user.getOauthUid());
		if(temp>=1)
		{
//			System.out.println("Inside IF");
			String gcmDeviceId=user.getGcmDeviceId();
			UserVO response = userDao.getUserByOauthUid(user.getOauthUid());
//			System.out.println("Inside "+ response.getUserId());
			//Updating the gcm device id
			if(gcmDeviceId!=null)
			{
//				System.out.println("Inside IF gcmDeviceId");
				UserVO tempUser=new UserVO();
				tempUser.setUserId(response.getUserId());
				tempUser.setGcmDeviceId(gcmDeviceId);
				userDao.updateUser(tempUser);
				response.setGcmDeviceId(gcmDeviceId);
			}
			return response;
		}
		
		String userId=UUID.randomUUID().toString();
		user.setSessionId(userId);
		user.setUserId(userId);
		
		Long abc=userDao.createUser(user);
		if(abc==1)
		{
			user.setPassword(null);
			return user;
		}
		else
		{
			return userVO;
		}	
		
	}	
	
	public Long updateJointAccountDetails(JointAccountVO jointAccountVO)
	{
		UserVO userVO=new UserVO();
		userVO.setUserId(jointAccountVO.getFirstUserId());
		userVO.setJointAccountId(jointAccountVO.getJointAccountId());
		userDao.updateUser(userVO);
		userVO.setUserId(jointAccountVO.getSecondUserId());
		userDao.updateUser(userVO);
		return (long)1;
	}
	
	
	public List<UserVO> getUsers() {
		return userDao.getUsers();
	}
	
	public UserSearchVO  searchUser(String keyword) {
		return new UserSearchVO(userDao.searchUser("%"+keyword+"%"));
	}
	
	public UserVO getUser(String userId) {
		return userDao.getUser(userId);
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
	
	public Long updateUserDetails(UserVO user) {
		
		return userDao.updateUser(user);

		
	}	
	public Long updateInviteRequestStatus(UserVO userVO) {
		
		return userDao.updateInviteRequestStatus(userVO);
		
	}
	
	public UserVO loginUser(UserVO user) {
		String emailId=user.getEmailId();
		String password =userDao.getPassword(emailId);
		String gcmDeviceId=user.getGcmDeviceId();
		if(password!=null)
		{	
			if(password.equals(user.getPassword()))
			{
			//String sessionId=UUID.randomUUID().toString();
			//user.setSessionId(sessionId);
			//userDao.updateSessionId(user);
			//user.setPassword(null);
			user=userDao.getUserByEmailId(emailId);
			//Updating the gcm device id
			if(gcmDeviceId!=null)
			{
			clearGcmDeviceId(gcmDeviceId);	
			UserVO  tempUser =new UserVO();
			tempUser.setUserId(user.getUserId());
			tempUser.setGcmDeviceId(gcmDeviceId);
			userDao.updateUser(tempUser);
			user.setGcmDeviceId(gcmDeviceId);
			}
			
			return user;
			}
			else
			{
				return userVO;
			}	
		}
		else
		{
			return userVO;

		}	
	}
	
	public Long clearGcmDeviceId(String gcmDeviceId)
	{
		return userDao.clearGcmDeviceId(gcmDeviceId);
	}
	
	public Long changePassword(ChangePasswordVO changePasswordVO)
	{
		if(changePasswordVO.getOldPassword().equals(userDao.getPassword(changePasswordVO.getUserId())))
		{
			userDao.updatePassword(changePasswordVO);
			return 1L;
		}
		else{
			//ErrorVO errorVO=new ErrorVO("Authentication Failed");
			return 0L;
		}

	}
	
	
}
