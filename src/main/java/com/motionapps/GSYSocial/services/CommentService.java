package com.motionapps.GSYSocial.services;

import java.util.List;
import java.util.UUID;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;

import com.motionapps.GSYSocial.dao.CommentDao;
import com.motionapps.GSYSocial.dao.vo.CommentArrayVO;
import com.motionapps.GSYSocial.dao.vo.CommentVO;
import com.motionapps.GSYSocial.dao.vo.GroupAccountVO;
import com.motionapps.GSYSocial.dao.vo.JointAccountVO;
import com.motionapps.GSYSocial.dao.vo.Notification;
import com.motionapps.GSYSocial.dao.vo.NotificationDataVO;
import com.motionapps.GSYSocial.dao.vo.NotificationRequestVO;
import com.motionapps.GSYSocial.dao.vo.PostVO;
import com.motionapps.GSYSocial.dao.vo.UserVO;
import com.motionapps.GSYSocial.util.Constants;

public class CommentService {
	
	private NotificationRequestVO notificationRequestVO;
	
	private PostVO postVO;
	
	private JointAccountVO jointAccountVO;
	
	private GroupAccountVO groupAccountVO;
	
	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private GroupAccountService groupAccountService;
	@Autowired
	private JointAccountService jointAccountService;
	
	public void setGroupAccountService(GroupAccountService groupAccountService) {
		this.groupAccountService = groupAccountService;
	}


	public void setJointAccountService(JointAccountService jointAccountService) {
		this.jointAccountService = jointAccountService;
	}


	public void setPostService(PostService postService) {
		this.postService = postService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}


	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}


	public Long addComment(CommentVO commentVO)
	{
		commentVO.setCommentId(UUID.randomUUID().toString());
		commentDao.addComment(commentVO);
		postService.incrementCommentCount(commentVO.getPostId());
		postVO=postService.getPostById(commentVO.getPostId());
		if(postVO.getAccountType()==0)
		{
			jointAccountVO=jointAccountService.getJointAccount(postVO.getAccountId());
			notificationRequestVO=createNotificationObject(commentVO," commented on your post in joint "+jointAccountVO.getJointAccountName());
		}
		else
		{
			groupAccountVO=groupAccountService.getGroupAccount(postVO.getAccountId());
			notificationRequestVO=createNotificationObject(commentVO," commented on your post in group "+groupAccountVO.getGroupAccountName());

		}
		if(notificationRequestVO!=null)
			notificationService.sendNotification(notificationRequestVO);
		return 1L;

	}
	
	public Long updateComment(CommentVO commentVO)
	{
		 commentDao.updateComment(commentVO);
		 commentVO=commentDao.getCommentById(commentVO.getCommentId());
			notificationRequestVO=createNotificationObject(commentVO," updated the comment on your post");
			if(notificationRequestVO!=null)
				notificationService.sendNotification(notificationRequestVO);
			return 1L;
			
		
	}
	
	
	public CommentArrayVO getAllComments()
	{
		List<CommentVO> commentVOList=commentDao.getAllComments();
		for (int i=0;i<commentVOList.size();i++) 
		{
			commentVOList.set(i, addUserData(commentVOList.get(i)));
		}
		CommentArrayVO commentArrayVO = new CommentArrayVO(commentVOList);
		return commentArrayVO;
	}
	
	
	public CommentArrayVO getCommentsByPost(@QueryParam("postId")String postId)
	{
		List<CommentVO> commentVOList=commentDao.getCommentsByPost(postId);
		for (int i=0;i<commentVOList.size();i++) 
		{
			commentVOList.set(i, addUserData(commentVOList.get(i)));
		}
		CommentArrayVO commentArrayVO = new CommentArrayVO(commentVOList);
		return commentArrayVO;
	}
	
	
	
	public Long deleteComment(String commentId) {
		
		CommentVO commentVO = commentDao.getCommentById(commentId);
		Long status = commentDao.deleteComment(commentId);
		postService.decrementCommentCount(commentVO.getCommentId());
		return status;
	}
	
	public Long deleteAllCommentsByPost(String postId)
	{
		return commentDao.deleteAllCommentsByPost(postId);
	}
	
	public Long deleteAllCommentsByUserId(String userId)
	{
		List<CommentVO> commentVOList=commentDao.getCommentsByUserId(userId);
		for (CommentVO commentVO : commentVOList) {
			deleteComment(commentVO.getCommentId());
		}
		return 1L;
	}
	
	

	private NotificationRequestVO createNotificationObject(CommentVO commentVO,String notificationText)
	{
		PostVO postVO=postService.getPostById(commentVO.getPostId());
		UserVO userVO=userService.getUser(postVO.getUserId());
//		NotificationRequestVO notificationRequestVO=new NotificationRequestVO();
//		String gcmId=userVO.getGcmDeviceId();
//		if(userVO.getGcmDeviceId()==null)
//			return null;
//		notificationRequestVO.setTo(userVO.getGcmDeviceId());
		Notification notification =new Notification();
		notification.setTitle(Constants.notificationTitle);
		UserVO userVO2=userService.getUser(commentVO.getUserId());
		notification.setText(userVO2.getUserName()+notificationText);
		notification.setIcon(userVO2.getProfilePicUrl());
		
		NotificationDataVO notificationDataVO=new NotificationDataVO(2,userVO.getUserId(),false,postVO);

		//notificationRequestVO.setNotification(notification);
		//notificationRequestVO.setData(notificationDataVO);
		notificationRequestVO=notificationService.createNotificationObject(userVO.getGcmDeviceId(),notification, notificationDataVO);

		if(userVO.getGcmDeviceId()==null||userVO.getGcmDeviceId().equals(""))
			return null;
		else
			return notificationRequestVO;
	}

	public CommentVO addUserData(CommentVO commentVO)
	{
		UserVO userVO=userService.getUser(commentVO.getUserId());
		commentVO.setUserName(userVO.getUserName());
		commentVO.setProfilePicUrl(userVO.getProfilePicUrl());
		return commentVO;
	}
	
}
