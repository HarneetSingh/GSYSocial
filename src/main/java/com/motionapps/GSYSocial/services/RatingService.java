package com.motionapps.GSYSocial.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.motionapps.GSYSocial.dao.RatingDao;
import com.motionapps.GSYSocial.dao.vo.GroupAccountVO;
import com.motionapps.GSYSocial.dao.vo.JointAccountVO;
import com.motionapps.GSYSocial.dao.vo.Notification;
import com.motionapps.GSYSocial.dao.vo.NotificationDataVO;
import com.motionapps.GSYSocial.dao.vo.NotificationRequestVO;
import com.motionapps.GSYSocial.dao.vo.PostVO;
import com.motionapps.GSYSocial.dao.vo.RatingArrayVO;
import com.motionapps.GSYSocial.dao.vo.RatingVO;
import com.motionapps.GSYSocial.dao.vo.UserVO;
import com.motionapps.GSYSocial.util.Constants;

public class RatingService {
	

	private NotificationRequestVO notificationRequestVO;
	
	private PostVO postVO;
	
	private JointAccountVO jointAccountVO;
	
	private GroupAccountVO groupAccountVO;
	
	@Autowired
	private RatingDao ratingDao;
	@Autowired
	private PostService postService;
	@Autowired
	private UserService userService;
	@Autowired
	private GroupAccountService groupAccountService;
	@Autowired
	private JointAccountService jointAccountService;

	@Autowired
	private NotificationService notificationService;
	
	
	public void setGroupAccountService(GroupAccountService groupAccountService) {
		this.groupAccountService = groupAccountService;
	}


	public void setJointAccountService(JointAccountService jointAccountService) {
		this.jointAccountService = jointAccountService;
	}

	
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
		if(ratingVO.getRatingValue()>2)
			return 0L;
		RatingVO ratingVO2=ratingDao.getRating(ratingVO);
		if(ratingVO2==null)
		{
		ratingVO.setRatingId(UUID.randomUUID().toString());		
		ratingDao.addRating(ratingVO);
		postService.addRating(ratingVO);
		postVO=postService.getPostById(ratingVO.getPostId());
		if(postVO.getAccountType()==0)
		{
			jointAccountVO=jointAccountService.getJointAccount(postVO.getAccountId());
			notificationRequestVO=createNotificationObject(ratingVO," has rated your post in joint "+jointAccountVO.getJointAccountName());
		}
		else
		{
			groupAccountVO=groupAccountService.getGroupAccount(postVO.getAccountId());
			notificationRequestVO=createNotificationObject(ratingVO," has rated your post in group "+groupAccountVO.getGroupAccountName());

		}
		if(notificationRequestVO!=null){
			notificationService.sendNotification(notificationRequestVO);}
		}
		else
		{
			ratingVO.setRatingId(ratingVO2.getRatingId());
			updateRating(ratingVO);
		}
		return 1L;
		
	}
	
	
	public RatingArrayVO getAllRatingsByPost(String postId)
	{
		List<RatingVO> ratingVOList=ratingDao.getRatingsByPost(postId);
		for (int i=0;i<ratingVOList.size();i++) 
		{
			ratingVOList.set(i, addUserData(ratingVOList.get(i)));
		}
		RatingArrayVO ratingArrayVO = new RatingArrayVO(ratingVOList);
		return ratingArrayVO;	}
	
	
	public Long updateRating(RatingVO ratingVO)
	{
		RatingVO currentRatingVO=ratingDao.getRatingById(ratingVO.getRatingId());
		ratingDao.updateRating(ratingVO);
		//if(currentRatingVO.getRatingValue()>ratingVO.getRatingValue())
		//	5 1 =1-5 = -4
        //   1 5 =5-1 =4
		ratingVO.setPostId(currentRatingVO.getPostId());
		//int ratingDifference=ratingVO.getRatingValue()-currentRatingVO.getRatingValue();
		//ratingVO.setRatingValue(ratingDifference);
		postService.removeRating(currentRatingVO);
		postService.addRating(ratingVO);
		postVO=postService.getPostById(ratingVO.getPostId());
		if(postVO.getAccountType()==0)
		{
			jointAccountVO=jointAccountService.getJointAccount(postVO.getAccountId());
			notificationRequestVO=createNotificationObject(currentRatingVO," has updated the rating on your post in joint "+jointAccountVO.getJointAccountName());
		}
		else
		{
			groupAccountVO=groupAccountService.getGroupAccount(postVO.getAccountId());
			notificationRequestVO=createNotificationObject(currentRatingVO," has updated the rating on your post in group "+groupAccountVO.getGroupAccountName());

		}
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
	
	public Long deleteRatingsByUserId(String userId)
	{

		List<RatingVO> ratingVOList=ratingDao.getRatingByUserId(userId);
		for (RatingVO ratingVO : ratingVOList) {	
		ratingDao.deleteRating(ratingVO.getRatingId());
		return postService.removeRating(ratingVO);
		}
		return 1L;
	}
	
	
	public Long deleteAllRatingsByPost(String postId)
	{
		return ratingDao.deleteAllRatingsByPost(postId);
	}
	
	private NotificationRequestVO createNotificationObject(RatingVO ratingVO,String notificationText)
	{
		PostVO postVO=postService.getPostById(ratingVO.getPostId());
		UserVO userVO=userService.getUser(postVO.getUserId());
		//NotificationRequestVO notificationRequestVO=new NotificationRequestVO();
		String gcmId=userVO.getGcmDeviceId();

		//notificationRequestVO.setTo(userVO.getGcmDeviceId());
		
		
		
		Notification notification =new Notification();
		notification.setTitle(Constants.notificationTitle);
		UserVO userVO2=userService.getUser(ratingVO.getUserId());
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
	
	public RatingVO addUserData(RatingVO ratingVO)
	{
		UserVO userVO=userService.getUser(ratingVO.getUserId());
		ratingVO.setUserName(userVO.getUserName());
		ratingVO.setProfilePicUrl(userVO.getProfilePicUrl());
		return ratingVO;
	}

}
