package com.motionapps.GSYSocial.services;

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
		
		//Setting the invite_request_pending flag in user table to true
		UserVO userVO=new UserVO();
		userVO.setUserId(inviteRequestVO.getInviteeUserId());
		userVO.setInviteRequestPending(Boolean.TRUE);
		userService.updateInviteRequestStatus(userVO);
		userVO.setUserId(inviteRequestVO.getInviterUserId());
		userService.updateInviteRequestStatus(userVO);
		notificationRequestVO=createNotificationObject(inviteRequestVO," invited you to join joint account");
		if(notificationRequestVO!=null)
			notificationService.sendNotification(notificationRequestVO);
		return inviteRequestVO;

	}
	
	public JointAccountVO inviteAccepted(InviteRequestVO inviteRequestVO)
	{
//		inviteRequestVO.setInviteAccepted(Boolean.TRUE);
		
		inviteRequestVO=inviteRequestDao.getInviteRequestDetails(inviteRequestVO.getInviteRequestId());
		UserVO userVO=new UserVO();
		userVO.setUserId(inviteRequestVO.getInviteeUserId());
		userVO.setInviteRequestPending(Boolean.FALSE);
		userService.updateInviteRequestStatus(userVO);
		userVO.setUserId(inviteRequestVO.getInviterUserId());
		userService.updateInviteRequestStatus(userVO);
		
		Long result=inviteRequestDao.inviteDeleted(inviteRequestVO);	
		
//		notificationRequestVO=createNotificationObject(join," invited you to join joint account");
//		if(notificationRequestVO!=null)
//			notificationService.sendNotification(notificationRequestVO);

		if(result==1){
			jointAccountVO=jointAccountService.createJointAccount(inviteRequestVO);
			notificationRequestVO=createJointAccNotificationObject(jointAccountVO," joint account is created");
			if(notificationRequestVO!=null)
				notificationService.sendNotification(notificationRequestVO);
			return jointAccountVO;
			
		}else
			{
			return jointAccountVO;
//			ErrorVO errorVO=new ErrorVO("No Pending Request Found");
//			return Response.status(400).entity(errorVO).type(MediaType.APPLICATION_JSON).build();
			}

	}
	
	public Long inviteRejected(InviteRequestVO inviteRequestVO)
	{
		
		inviteRequestVO=inviteRequestDao.getInviteRequestDetails(inviteRequestVO.getInviteRequestId());

		//Setting the invite_request_pending flag in user table to false
		UserVO userVO=new UserVO();
		userVO.setUserId(inviteRequestVO.getInviteeUserId());
		userVO.setInviteRequestPending(Boolean.FALSE);
		userService.updateInviteRequestStatus(userVO);
		userVO.setUserId(inviteRequestVO.getInviterUserId());
		userService.updateInviteRequestStatus(userVO);
		return inviteRequestDao.inviteDeleted(inviteRequestVO);
	}
	
	public InviteRequestVO pendingRequests(String userId)
	{
		return inviteRequestDao.getInviteRequest(userId);
	}
	
	private NotificationRequestVO createNotificationObject(InviteRequestVO inviteRequestVO,String notificationText)
	{
//		PostVO postVO=postService.getPostById(commentVO.getPostId());
		UserVO inviteeUserVO=userService.getUser(inviteRequestVO.getInviteeUserId());
		UserVO inviterUserVO=userService.getUser(inviteRequestVO.getInviterUserId());
		//String gcmId=userVO.getGcmDeviceId();
		if(inviteeUserVO.getGcmDeviceId()==null)
			return null;
		NotificationRequestVO notificationRequestVO=new NotificationRequestVO();
		notificationRequestVO.setTo(inviteeUserVO.getGcmDeviceId());
		Notification notification =new Notification();
		notification.setTitle("Intactyou");
		notification.setText(inviterUserVO.getUserName()+notificationText);
		notificationRequestVO.setNotification(notification);
		NotificationDataVO notificationDataVO=new NotificationDataVO(2,inviteRequestVO);
		notificationRequestVO.setData(notificationDataVO);
		return notificationRequestVO;
	}
	private NotificationRequestVO createJointAccNotificationObject(JointAccountVO jointAccountVO,String notificationText)
	{
//		PostVO postVO=postService.getPostById(commentVO.getPostId());
//		UserVO inviteeUserVO=userService.getUser(inviteRequestVO.getInviteeUserId());
		UserVO userVO=userService.getUser(jointAccountVO.getFirstUserId());
		//String gcmId=userVO.getGcmDeviceId();
		if(userVO.getGcmDeviceId()==null)
			return null;
		NotificationRequestVO notificationRequestVO=new NotificationRequestVO();
		notificationRequestVO.setTo(userVO.getGcmDeviceId());
		Notification notification =new Notification();
		notification.setTitle("Intactyou");
		notification.setText(jointAccountVO.getJointAccountName()+notificationText);
		notificationRequestVO.setNotification(notification);
		NotificationDataVO notificationDataVO=new NotificationDataVO(3,jointAccountVO);
		notificationRequestVO.setData(notificationDataVO);
		return notificationRequestVO;
	}

	
}
