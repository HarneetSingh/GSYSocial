package com.motionapps.GSYSocial.services;

import java.util.List;
import java.util.Set;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;

import com.motionapps.GSYSocial.dao.GroupAccountDao;
import com.motionapps.GSYSocial.dao.vo.FollowerVO;
import com.motionapps.GSYSocial.dao.vo.GroupAccountVO;
import com.motionapps.GSYSocial.dao.vo.GroupMemberSearchVO;
import com.motionapps.GSYSocial.dao.vo.GroupMemberVO;
import com.motionapps.GSYSocial.dao.vo.Notification;
import com.motionapps.GSYSocial.dao.vo.NotificationDataVO;
import com.motionapps.GSYSocial.dao.vo.NotificationRequestVO;
import com.motionapps.GSYSocial.dao.vo.UserVO;

public class GroupAccountService {
	
	@Autowired
	private GroupAccountDao groupAccountDao;
	
	@Autowired
	private FollowerService followerService;
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private PostService postService;


	@Autowired
	private UserService userService;
	
	private UserVO userVO;
	
	private GroupAccountVO groupAccountVO;
	
	private GroupMemberVO groupMemberVO;
	
	private FollowerVO followerVO;
	
	private NotificationRequestVO notificationRequestVO;
	
	public void setPostService(PostService postService) {
		this.postService = postService;
	}
	
	public void setGroupAccountDao(GroupAccountDao groupAccountDao) {
		this.groupAccountDao = groupAccountDao;
	}
	
	public void setFollowerService(FollowerService followerService) {
		this.followerService = followerService;
	}
	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}



	public Long createGroupAccount(GroupAccountVO groupAccountVO)
	{
		
		groupAccountVO.setGroupAccountId(UUID.randomUUID().toString());
		groupAccountDao.createGroupAccount(groupAccountVO);
		//Automatic Addition of Admin As A Member
		groupMemberVO=new GroupMemberVO();
		groupMemberVO.setGroupAccountId(groupAccountVO.getGroupAccountId());
		groupMemberVO.setUserId(groupAccountVO.getGroupAdminId());
		groupMemberVO.setMemberStatus(2);
		groupAccountDao.inviteGroupMember(groupMemberVO);
		//Automatic Following of admin
		followerVO=new FollowerVO();
		followerVO.setAccountId(groupMemberVO.getGroupAccountId());
		followerVO.setUserId(groupMemberVO.getUserId());
		followerVO.setAccountType(1);
		followerService.followAccount(followerVO);
		
		return 1L;
	}
	
	public Long updateGroupAccount(GroupAccountVO groupAccountVO)
	{
		groupAccountDao.updateGroupAccount(groupAccountVO);
		return 1L;
	}
	
	public Set<GroupAccountVO> searchGroupAccount(String keyword)
	{
		Set<GroupAccountVO> groupAccountVO=groupAccountDao.searchGroupAccount("%"+keyword+"%");

//		for (GroupAccountVO groupAccountVO2 : groupAccountVO) {
//			userVO=userService.getUser(groupAccountVO2.getGroupAdminId());
//			groupAccountVO2.setGroupAdminName(userVO.getUserName());
//			groupAccountVO2.setGroupAdminProfilePic(userVO.getProfilePicUrl());
//		}
		return groupAccountVO;
	}

	public GroupAccountVO getGroupAccount(String groupAccountId)
	{
		groupAccountVO=groupAccountDao.getGroupAccount(groupAccountId);
		if(groupAccountVO!=null){
		userVO=userService.getUser(groupAccountVO.getGroupAdminId());
		groupAccountVO.setGroupAdminName(userVO.getUserName());
		groupAccountVO.setGroupAdminProfilePic(userVO.getProfilePicUrl());}
		return groupAccountVO;
	}
	
	public Set<GroupAccountVO> getGroupAccounts()
	{
		Set<GroupAccountVO> groupAccountVO=groupAccountDao.getGroupAccounts();

		for (GroupAccountVO groupAccountVO2 : groupAccountVO) {
			userVO=userService.getUser(groupAccountVO2.getGroupAdminId());
			groupAccountVO2.setGroupAdminName(userVO.getUserName());
			groupAccountVO2.setGroupAdminProfilePic(userVO.getProfilePicUrl());
		}
		return groupAccountVO;
	}
	
	public Set<GroupAccountVO> getGroupAccountsByUserId(String userId)
	{
		Set<GroupAccountVO> groupAccountVO=groupAccountDao.getGroupAccountsByUserId(userId);
		return groupAccountVO;
	}
	
	public Set<GroupAccountVO> getGroupAccountsByAdminId(String userId)
	{
		return groupAccountDao.getGroupAccountsByAdminId(userId);
	}
	
	public Long inviteGroupMember(GroupMemberVO groupMemberVO)
	{
		//1 means invited
		//2 means accepted
		groupMemberVO.setMemberStatus(1);
		groupAccountDao.inviteGroupMember(groupMemberVO);
		
		notificationRequestVO=createNotificationObject(groupMemberVO,"You are invited to join group account ");
		if(notificationRequestVO!=null)
			notificationService.sendNotification(notificationRequestVO);
		return 1L;
	}
	
	private NotificationRequestVO createNotificationObject(GroupMemberVO groupMemberVO,String notificationText)
	{
		UserVO userVO = userService.getUser(groupMemberVO.getUserId());
		groupMemberVO.setUserName(userVO.getUserName());
		groupMemberVO.setProfilePicUrl(userVO.getProfilePicUrl());
		GroupAccountVO groupAccountVO=getGroupAccount(groupMemberVO.getGroupAccountId());
		
		Notification notification=new Notification();
		notification.setIcon(groupAccountVO.getProfilePic());
		notification.setTitle("Intactyou");
		notification.setText(notificationText+groupAccountVO.getGroupAccountName());
		
		NotificationDataVO notificationDataVO=new NotificationDataVO(22,userVO.getUserId(), true, groupMemberVO);
		NotificationRequestVO notificationRequestVO=notificationService.createNotificationObject(userVO.getGcmDeviceId(), notification, notificationDataVO);
		if(userVO.getGcmDeviceId()==null||userVO.getGcmDeviceId().equals(""))
			return null;
		else 
			return notificationRequestVO;
	}
	
	public Long inviteAccepted(String groupAccountId,String userId, String notificationId)
	{
		GroupMemberVO groupMemberVO=new GroupMemberVO();
		groupMemberVO.setGroupAccountId(groupAccountId);
		groupMemberVO.setUserId(userId);
		groupMemberVO.setMemberStatus(2);
		groupAccountDao.inviteAccepted(groupMemberVO);
		
		//automatic following of group member
		followerVO=new FollowerVO();
		followerVO.setAccountId(groupMemberVO.getGroupAccountId());
		followerVO.setUserId(groupMemberVO.getUserId());
		followerVO.setAccountType(1);
		followerService.followAccount(followerVO);
		
		//removal of notification object 
		notificationService.removeNotificationRequest(notificationId);
		
		return 1L;
	}
	public Long inviteRejected(String groupAccountId,String userId, String notificationId)
	{
		//System.out.println("Test");
		GroupMemberVO groupMemberVO=new GroupMemberVO();
		groupMemberVO.setGroupAccountId(groupAccountId);
		groupMemberVO.setUserId(userId);
		groupAccountDao.inviteRejected(groupMemberVO);
		
		//removal of notification object 
		notificationService.removeNotificationRequest(notificationId);
		return 1L;
	}
	
	public GroupMemberSearchVO getGroupMembers(String groupAccountId)
	{
		Set<GroupMemberVO> groupMembers=groupAccountDao.getGroupMembers(groupAccountId);
		for(GroupMemberVO groupMemberVO : groupMembers)
		{
			userVO=userService.getUser(groupMemberVO.getUserId());
			groupMemberVO.setUserName(userVO.getUserName());
			groupMemberVO.setProfilePicUrl(userVO.getProfilePicUrl());
		}
		return new GroupMemberSearchVO(groupMembers);
	}
	
	public List<UserVO> searchUsers(String groupAccountId,String keyword)
	{
		List<UserVO> users=userService.searchUser(keyword);
		Set<GroupMemberVO> groupMembers=groupAccountDao.getAllGroupMembers(groupAccountId);
		for(UserVO userVO : users)
		{
			userVO.setGroupAccountStatus(0);
			//System.out.println("userId="+userVO.getUserId());
			for(GroupMemberVO groupMemberVO : groupMembers)
			{
				//System.out.println("groupMemberId="+groupMemberVO.getUserId());

				if(userVO.getUserId().equals(groupMemberVO.getUserId()))
				{
					//System.out.println("matchId="+groupMemberVO.getUserId());
					userVO.setGroupAccountStatus(groupMemberVO.getMemberStatus());
					break;
				}
			}
			
		}
		return users;
	}
	
	public Set<GroupAccountVO> getGroupAccountsFollowedByUser(String userId)
	{
		return groupAccountDao.getGroupAccountsFollowedByUser(userId);
	}

	
	public Long removeMember(GroupMemberVO groupMemberVO)
	{
		 groupAccountDao.removeMember(groupMemberVO);
		 followerVO=new FollowerVO();
		 followerVO.setAccountId(groupMemberVO.getGroupAccountId());
		 followerVO.setUserId(groupMemberVO.getUserId());
		 followerVO.setAccountType(1);
		 followerService.unfollowAccount(followerVO);
		 return 1L;
		
	}
	
	public GroupAccountVO getGroupAccountWithUserId(String groupAccountId,String userId)
	{
		groupAccountVO=groupAccountDao.getGroupAccountWithUserId(groupAccountId, userId);
		if(groupAccountVO!=null){
		userVO=userService.getUser(groupAccountVO.getGroupAdminId());
		groupAccountVO.setGroupAdminName(userVO.getUserName());
		groupAccountVO.setGroupAdminProfilePic(userVO.getProfilePicUrl());}
		return groupAccountVO;
	}

	public Long incrementPostCount(String groupAccountId)
	{
		
		return groupAccountDao.incrementPostCount(groupAccountId);
	}
	
	public Long decrementPostCount(String groupAccountId)
	{
		return groupAccountDao.decrementPostCount(groupAccountId);
	}
	
	public Long incrementFollowCount(String groupAccountId)
	{
		return groupAccountDao.incrementFollowCount(groupAccountId);
	}
	
	public Long decrementFollowCount(String groupAccountId)
	{
		return groupAccountDao.decrementFollowCount(groupAccountId);
	}

	public Long deleteAllGroupMembers(String groupAccountId)
	{
		Set<GroupMemberVO> groupMembers=groupAccountDao.getAllGroupMembers(groupAccountId);
		for(GroupMemberVO groupMemberVO:groupMembers)
		{
			removeMember(groupMemberVO);
		}
		
		return 1L;
	}
	
	public Long removeGroupMemberByUserId(String userId)
	{
		return groupAccountDao.removeGroupMemberByUserId(userId);
	}
	
	
	public Long deleteGroupAccount(String groupAccountId) {
		
		postService.deleteAllPostsByAccount(groupAccountId);
		deleteAllGroupMembers(groupAccountId);
		//Delete all followers
		followerService.deleteAllFollowersByAccount(groupAccountId);
		
		return groupAccountDao.deleteGroupAccount(groupAccountId);
	}
}
