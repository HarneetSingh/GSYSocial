package com.motionapps.GSYSocial.services;

import java.util.UUID;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;

import com.motionapps.GSYSocial.dao.CommentDao;
import com.motionapps.GSYSocial.dao.vo.CommentArrayVO;
import com.motionapps.GSYSocial.dao.vo.CommentVO;
import com.motionapps.GSYSocial.dao.vo.Notification;
import com.motionapps.GSYSocial.dao.vo.NotificationDataVO;
import com.motionapps.GSYSocial.dao.vo.NotificationRequestVO;
import com.motionapps.GSYSocial.dao.vo.PostVO;
import com.motionapps.GSYSocial.dao.vo.RatingVO;
import com.motionapps.GSYSocial.dao.vo.UserVO;

public class CommentService {
	
	private NotificationRequestVO notificationRequestVO;
	
	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private NotificationService notificationService;

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
		notificationRequestVO=createNotificationObject(commentVO," commented on your post");
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
		return new CommentArrayVO(commentDao.getAllComments());
	}
	
	
	public CommentArrayVO getCommentsByPost(@QueryParam("postId")String postId)
	{
		return new CommentArrayVO(commentDao.getCommentsByPost(postId));
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
	

	private NotificationRequestVO createNotificationObject(CommentVO commentVO,String notificationText)
	{
		PostVO postVO=postService.getPostById(commentVO.getPostId());
		UserVO userVO=userService.getUser(postVO.getUserId());
		NotificationRequestVO notificationRequestVO=new NotificationRequestVO();
		//String gcmId=userVO.getGcmDeviceId();
		if(userVO.getGcmDeviceId()==null)
			return null;
		notificationRequestVO.setTo(userVO.getGcmDeviceId());
		Notification notification =new Notification();
		notification.setTitle("Intactyou");
		notification.setText(commentVO.getUserName()+notificationText);
		notificationRequestVO.setNotification(notification);
		NotificationDataVO notificationDataVO=new NotificationDataVO(1,postVO);
		notificationRequestVO.setData(notificationDataVO);
		return notificationRequestVO;
	}

	
	
}
