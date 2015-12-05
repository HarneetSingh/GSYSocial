package com.motionapps.GSYSocial.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.motionapps.GSYSocial.dao.RatingDao;
import com.motionapps.GSYSocial.dao.vo.Notification;
import com.motionapps.GSYSocial.dao.vo.NotificationDataVO;
import com.motionapps.GSYSocial.dao.vo.NotificationRequestVO;
import com.motionapps.GSYSocial.dao.vo.PostVO;
import com.motionapps.GSYSocial.dao.vo.RatingArrayVO;
import com.motionapps.GSYSocial.dao.vo.RatingVO;
import com.motionapps.GSYSocial.dao.vo.UserVO;

public class RatingService {
	

	private NotificationRequestVO notificationRequestVO;
	
	@Autowired
	private RatingDao ratingDao;
	@Autowired
	private PostService postService;
	@Autowired
	private UserService userService;
	
	@Autowired
	private NotificationService notificationService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	

	public void setRatingDao(RatingDao ratingDao) {
		this.ratingDao = ratingDao;
	}
	
	
	public void setPostService(PostService postService) {
		this.postService = postService;
	}


	public Long addRating(RatingVO ratingVO)
	{
//		if(ratingVO.getRatingValue()>5)
//			return 0L;
		ratingVO.setRatingId(UUID.randomUUID().toString());
		ratingDao.addRating(ratingVO);
		postService.addRating(ratingVO);
		notificationRequestVO=createNotificationObject(ratingVO," rated your post");
		if(notificationRequestVO!=null)
			notificationService.sendNotification(notificationRequestVO);
		return 1L;
		
	}
	
	
	public RatingArrayVO getAllRatingsByPost(String postId)
	{
		return new RatingArrayVO(ratingDao.getRatingsByPost(postId));
	}
	
	
	public Long updateRating(RatingVO ratingVO)
	{
		RatingVO currentRatingVO=ratingDao.getRatingById(ratingVO.getRatingId());
		ratingDao.updateRating(ratingVO);
		//if(currentRatingVO.getRatingValue()>ratingVO.getRatingValue())
		//	5 1 =1-5 = -4
        //   1 5 =5-1 =4
		ratingVO.setPostId(currentRatingVO.getPostId());
		int ratingDifference=ratingVO.getRatingValue()-currentRatingVO.getRatingValue();
		ratingVO.setRatingValue(ratingDifference);
		postService.updateRating(ratingVO);
		notificationRequestVO=createNotificationObject(currentRatingVO," changed his rating");
		if(notificationRequestVO!=null)
			notificationService.sendNotification(notificationRequestVO);
		return 1L;
	}
	
	public Long deleteRating(String ratingId)
	{
		
		RatingVO ratingVO=ratingDao.getRatingById(ratingId);
		ratingDao.deleteRating(ratingId);
		return postService.removeRating(ratingVO);
	}
	
	
	public Long deleteAllRatingsByPost(String postId)
	{
		return ratingDao.deleteAllRatingsByPost(postId);
	}
	
	private NotificationRequestVO createNotificationObject(RatingVO ratingVO,String notificationText)
	{
		PostVO postVO=postService.getPostById(ratingVO.getPostId());
		UserVO userVO=userService.getUser(postVO.getUserId());
		NotificationRequestVO notificationRequestVO=new NotificationRequestVO();
		String gcmId=userVO.getGcmDeviceId();
		if(userVO.getGcmDeviceId()==null)
			return null;
		notificationRequestVO.setTo(userVO.getGcmDeviceId());
		Notification notification =new Notification();
		notification.setTitle("Intactyou");
		notification.setText(ratingVO.getUserName()+notificationText);
		notificationRequestVO.setNotification(notification);
		NotificationDataVO notificationDataVO=new NotificationDataVO(1,postVO);
		notificationRequestVO.setData(notificationDataVO);
		return notificationRequestVO;
	}

}
