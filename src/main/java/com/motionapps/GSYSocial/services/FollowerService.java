package com.motionapps.GSYSocial.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.motionapps.GSYSocial.dao.FollowerDao;
import com.motionapps.GSYSocial.dao.vo.FollowerVO;
import com.motionapps.GSYSocial.dao.vo.JointAccountSearchVO;
import com.motionapps.GSYSocial.dao.vo.JointAccountVO;
import com.motionapps.GSYSocial.dao.vo.Notification;
import com.motionapps.GSYSocial.dao.vo.NotificationDataVO;
import com.motionapps.GSYSocial.dao.vo.NotificationRequestVO;
import com.motionapps.GSYSocial.dao.vo.UserSearchVO;
import com.motionapps.GSYSocial.dao.vo.UserVO;

public class FollowerService {
	
	private NotificationRequestVO notificationRequestVO;

	@Autowired
	private FollowerDao followerDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JointAccountService jointAccountService;
	
	private JointAccountVO jointAccountVO;
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private GroupAccountService groupAccountService;
	
	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}


	



	public void setJointAccountService(JointAccountService jointAccountService) {
		this.jointAccountService = jointAccountService;
	}

	


	public void setUserService(UserService userService) {
		this.userService = userService;
	}




	public void setFollowerDao(FollowerDao followerDao) {
		this.followerDao = followerDao;
	}
	
	
	public Long followAccount(FollowerVO followerVO)
	{
		followerVO.setFollowId(UUID.randomUUID().toString());

		if(followerVO.getAccountType()==0)
		{
			jointAccountVO=jointAccountService.getJointAccount(followerVO.getAccountId());
	 		int privacyMode=jointAccountVO.getPrivacyMode();
			if(privacyMode==0)//||privacyMode==1)
			{
				followerDao.followAccount(followerVO);
				jointAccountService.incrementFollowCount(followerVO.getAccountId());
				return userService.incrementFollowCount(followerVO.getUserId());

			} 
			else if(privacyMode==1) {
				return sendFollowRequest(followerVO);
				}
			else {
				return 0L;
			}

		}
		else if(followerVO.getAccountType()==1)
		{
			groupAccountService.incrementFollowCount(followerVO.getAccountId());
			followerDao.followAccount(followerVO);
			return userService.incrementFollowCount(followerVO.getUserId());
		}
		else 
			return 0L;

		 
	}
	
	public Long unfollowAccount(FollowerVO followerVO)
	{
		followerDao.unfollowAccount(followerVO);
		if(followerVO.getAccountType()==0)
			jointAccountService.decrementFollowCount(followerVO.getAccountId());
		else if(followerVO.getAccountType()==1)
			groupAccountService.decrementFollowCount(followerVO.getAccountId());
		return userService.decrementFollowCount(followerVO.getUserId());
		 
	}
	
	public Long acceptFollowRequest(String followerRequestId, String notificationId)
	{
		FollowerVO followerVO=followerDao.getFollowRequest(followerRequestId);
		followerDao.removeFollowRequest(followerRequestId);
		followerDao.followAccount(followerVO);
		jointAccountService.incrementFollowCount(followerVO.getAccountId ());
		userService.incrementFollowCount(followerVO.getUserId());
		return 	notificationService.removeNotificationRequest(notificationId);

	}
	
	public Long sendFollowRequest(FollowerVO followerVO)
	{
		followerDao.sendFollowRequest(followerVO);
		notificationRequestVO=createNotificationObject(followerVO, " requested you to follow the joint account ");
		if(notificationRequestVO!=null)
			notificationService.sendNotification(notificationRequestVO);
//		NotificationDataVO notificationDataVO=new NotificationDataVO(3, followerVO);
//		notificationRequestVO=notificationService.createNotificationObject(firstUserVO,notificationDataVO,"requested you to follow joint account "+jointAccountVO.getJointAccountName());
//		if(notificationRequestVO!=null)
//			notificationService.sendNotification(notificationRequestVO);
//		notificationRequestVO=notificationService.createNotificationObject(secondUserVO,notificationDataVO,"requested you to follow joint account "+jointAccountVO.getJointAccountName());
//		if(notificationRequestVO!=null)
//			notificationService.sendNotification(notificationRequestVO);
		return 1L;
	}
//	
//	public getNotification
	
	public Long rejectFollowRequest(String followerRequestId,String notificationId)
	{
		 followerDao.removeFollowRequest(followerRequestId);
		return 	notificationService.removeNotificationRequest(notificationId);


	}

	public UserSearchVO getAccountFollowers(String accountId)
	{
		return new UserSearchVO(followerDao.getAccountFollowers(accountId));
	}
	
	public JointAccountSearchVO getJointAccountsFollowedByUser(String userId)
	{
		return new JointAccountSearchVO(followerDao.getJointAccountsFollowedByUser(userId));
	}
	
	public Long deleteAllFollowersByJointAccount(String jointAccountId)
	{
		List<FollowerVO> followersVO=followerDao.getFollowersVOByJointAccount(jointAccountId);
		for(FollowerVO tempFollowerVO:followersVO)
		{
			followerDao.unfollowAccount(tempFollowerVO);
			//userService.decrementFollowCount(tempFollowerVO.getUserId());
		}
		
		return 1L;
	}
	public Long deleteAllFollowersByUserId(String userId)
	{
		List<FollowerVO> followersVO=followerDao.getFollowerVOByUserId(userId);
		for(FollowerVO tempFollowerVO:followersVO)
		{
			followerDao.unfollowAccount(tempFollowerVO);
		}
		
		return 1L;
	}

	private NotificationRequestVO createNotificationObject(FollowerVO followerVO,String notificationText)
	{

		jointAccountVO=jointAccountService.getJointAccount(followerVO.getAccountId());
		UserVO user=userService.getUser(followerVO.getUserId());
		UserVO firstUserVO=userService.getUser(jointAccountVO.getFirstUserId());
		//UserVO secondUserVO=userService.getUser(jointAccountVO.getSecondUserId());
		Notification notification =new Notification();
		notification.setTitle("Intactyou");
		notification.setText(user.getUserName()+notificationText+jointAccountVO.getJointAccountName());
		notification.setIcon(user.getProfilePicUrl());
		
		//first user
		NotificationDataVO notificationDataVO=new NotificationDataVO(23,firstUserVO.getUserId(),true,followerVO);
		notificationRequestVO=notificationService.createNotificationObject(firstUserVO.getGcmDeviceId(),notification, notificationDataVO);
		if(firstUserVO.getGcmDeviceId()==null)
			return null;
		else if (firstUserVO.getGcmDeviceId().equals(""))
				return null;
		else 
			return notificationRequestVO;
	}
}
