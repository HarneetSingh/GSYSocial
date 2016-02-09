package com.motionapps.GSYSocial.services;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.motionapps.GSYSocial.dao.InviteRequestDao;
import com.motionapps.GSYSocial.dao.vo.InviteRequestVO;
import com.motionapps.GSYSocial.dao.vo.JointAccountVO;
import com.motionapps.GSYSocial.dao.vo.Notification;
import com.motionapps.GSYSocial.dao.vo.NotificationDataVO;
import com.motionapps.GSYSocial.dao.vo.NotificationRequestVO;
import com.motionapps.GSYSocial.dao.vo.UserVO;

public class InviteRequestService {

	
	private NotificationRequestVO notificationRequestVO;
	private InviteRequestVO inviteRequestVO;

	@Autowired
	private InviteRequestDao inviteRequestDao;
	
	@Autowired
	private NotificationService notificationService;
	
	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}



	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	private UserService userService;
	
	@Autowired
	private JointAccountService jointAccountService;
	
	
	private JointAccountVO jointAccountVO;


	public void setJointAccountService(JointAccountService jointAccountService) {
		this.jointAccountService = jointAccountService;
	}



	public void setInviteRequestDao(InviteRequestDao inviteRequestDao) {
		this.inviteRequestDao = inviteRequestDao;
	}
	
	public InviteRequestVO inviteUser(InviteRequestVO inviteRequestVO)
	{
		String inviteRequestId=UUID.randomUUID().toString();
		inviteRequestVO.setInviteRequestId(inviteRequestId);
		
		inviteRequestDao.inviteUser(inviteRequestVO);


		notificationRequestVO=createNotificationObject(inviteRequestVO," invited you to join joint account");
		if(notificationRequestVO!=null)
			notificationService.sendNotification(notificationRequestVO);
		return inviteRequestVO;

	}
	
	public JointAccountVO inviteAccepted(String inviteRequestId,String notificationId)
	{

		
		inviteRequestVO=inviteRequestDao.getInviteRequestDetails(inviteRequestId);

		
		Long result=inviteRequestDao.inviteDeleted(inviteRequestVO);	


		if(result==1){
			jointAccountVO=jointAccountService.createJointAccount(inviteRequestVO);
			notificationService.removeNotificationRequest(notificationId);
			notificationRequestVO=createJointAccNotificationObject(jointAccountVO," joint account is created");
			if(notificationRequestVO!=null)
				notificationService.sendNotification(notificationRequestVO);
			return jointAccountVO;
			
		}else
			{
			return jointAccountVO;

			}

	}
	
	public Long inviteRejected(String inviteRequestId,String notificationId)
	{
		
		inviteRequestVO=inviteRequestDao.getInviteRequestDetails(inviteRequestId);
//
		notificationService.removeNotificationRequest(notificationId);

		return inviteRequestDao.inviteDeleted(inviteRequestVO);
	}
	
	public InviteRequestVO pendingRequests(String userId)
	{
		return inviteRequestDao.getInviteRequest(userId);
	}
	
	public Set<InviteRequestVO> getInviteRequests(String userId)
	{
		return inviteRequestDao.getInviteRequests(userId);
	}
	
	
	
	public Long deleteInviteRequest(String userId)
	{
		InviteRequestVO inviteRequestVO=pendingRequests(userId);
		if(inviteRequestVO!=null)
		{
			inviteRejected(inviteRequestVO.getInviteRequestId(),null);
		}
		return 1L;
	}
	
	private NotificationRequestVO createNotificationObject(InviteRequestVO inviteRequestVO,String notificationText)
	{

		UserVO inviteeUserVO=userService.getUser(inviteRequestVO.getInviteeUserId());
		UserVO inviterUserVO=userService.getUser(inviteRequestVO.getInviterUserId());

		Notification notification =new Notification();
		notification.setTitle("Intactyou");
		notification.setText(inviterUserVO.getUserName()+notificationText);
		notification.setIcon(inviterUserVO.getProfilePicUrl());

		NotificationDataVO notificationDataVO=new NotificationDataVO(21,inviteeUserVO.getUserId(),true,inviteRequestVO);

		notificationRequestVO=notificationService.createNotificationObject(inviteeUserVO.getGcmDeviceId(),notification, notificationDataVO);
		if(inviteeUserVO.getGcmDeviceId()==null)
			return null;
		else if (inviteeUserVO.getGcmDeviceId().equals(""))
				return null;
		else 
			return notificationRequestVO;
	}
	private NotificationRequestVO createJointAccNotificationObject(JointAccountVO jointAccountVO,String notificationText)
	{

		UserVO userVO=userService.getUser(jointAccountVO.getFirstUserId());

		Notification notification =new Notification();
		notification.setTitle("Intactyou");
		notification.setText(jointAccountVO.getJointAccountName()+notificationText);

		NotificationDataVO notificationDataVO=new NotificationDataVO(1,userVO.getUserId(),false,jointAccountVO);

		
		notificationRequestVO=notificationService.createNotificationObject(userVO.getGcmDeviceId(), notification, notificationDataVO);
			if(userVO.getGcmDeviceId()==null||userVO.getGcmDeviceId().equals(""))
				return null;
			else 
				return notificationRequestVO;
	}

	
}
