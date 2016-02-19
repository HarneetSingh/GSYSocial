package com.motionapps.GSYSocial.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;


import com.motionapps.GSYSocial.dao.PostDao;
import com.motionapps.GSYSocial.dao.vo.GroupAccountVO;
import com.motionapps.GSYSocial.dao.vo.JointAccountVO;
import com.motionapps.GSYSocial.dao.vo.LikeDislikeVO;
import com.motionapps.GSYSocial.dao.vo.Notification;
import com.motionapps.GSYSocial.dao.vo.NotificationDataVO;
import com.motionapps.GSYSocial.dao.vo.NotificationRequestVO;
import com.motionapps.GSYSocial.dao.vo.PostArrayVO;
import com.motionapps.GSYSocial.dao.vo.PostVO;
import com.motionapps.GSYSocial.dao.vo.RatingVO;
import com.motionapps.GSYSocial.dao.vo.UserVO;
import com.motionapps.GSYSocial.util.Constants;

public class PostService {



	private PostVO postVO;
	private JointAccountVO jointAccountVO;
	private GroupAccountVO groupAccountVO;
	private NotificationRequestVO notificationRequestVO;
	private LikeDislikeVO likeDislikeVO;


	
	@Autowired
	private PostDao postDao;

	@Autowired
	private JointAccountService jointAccountService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private RatingService ratingService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private GroupAccountService groupAccountService;

	@Autowired
	private NotificationService notificationService;
	



	public void setGroupAccountService(GroupAccountService groupAccountService) {
		this.groupAccountService = groupAccountService;
	}



	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}




	public void setJointAccountService(JointAccountService jointAccountService) {
		this.jointAccountService = jointAccountService;
	}




	public void setRatingService(RatingService ratingService) {
		this.ratingService = ratingService;
	}




	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}




	public void setPostDao(PostDao postDao) {
		this.postDao = postDao;
	}




	public Long createPost(PostVO postVO) {

		postVO.setPostId(UUID.randomUUID().toString());
		Long status=postDao.createPost(postVO);
		if(postVO.getAccountType()==0)
			jointAccountService.incrementPostCount(postVO.getAccountId());
		else if(postVO.getAccountType()==1)
			groupAccountService.incrementPostCount(postVO.getAccountId());
		return status;

	}

	public Long updatePost(PostVO postVO) {
		return postDao.updatePost(postVO);
	}

	public Long incrementCommentCount(String postId)
	{
		return postDao.incrementCommentCount(postId);
	}

	public Long addRating(RatingVO ratingVO)
	{
		String postId=ratingVO.getPostId();
		PostVO postVO=new PostVO();
		postVO.setPostId(postId);
		postVO.setNewRating(ratingVO.getRatingValue());
		return postDao.addRating(postVO); 
	}

	public Long removeRating(RatingVO ratingVO) {

		String postId=ratingVO.getPostId();
		PostVO postVO=new PostVO();
		postVO.setPostId(postId);
		postVO.setNewRating(ratingVO.getRatingValue());
		return postDao.deleteRating(postVO); 
	}

//	public Long updateRating(RatingVO ratingVO) {
//
//		String postId=ratingVO.getPostId();
//		PostVO postVO=new PostVO();
//		postVO.setPostId(postId);
//		postVO.setNewRating(ratingVO.getRatingValue());
//		return postDao.updateRating(postVO); 
//	}


	public PostArrayVO getPostByAccountForUserId(String accountId,String userId) {

		List<PostVO> postVOList=postDao.getPostByAccountForUserId(accountId,userId);


		for (int i=0;i<postVOList.size();i++) 
		{
			postVOList.set(i, addUserData(postVOList.get(i)));
		}
		PostArrayVO postArrayVO = new PostArrayVO(postVOList);
		return postArrayVO;

	}


	public PostArrayVO getPostForUser(String userId) {
		
		
		List<PostVO> postVOList=postDao.getPostForUser(userId);
		for (int i=0;i<postVOList.size();i++) 
		{
			
			postVOList.set(i, addUserData(postVOList.get(i)));
		}
		PostArrayVO postArrayVO = new PostArrayVO(postVOList);
		return postArrayVO;

	}

	public PostArrayVO getAllPosts() {

		List<PostVO> postVOList=postDao.getAllPosts();


		for (int i=0;i<postVOList.size();i++) 
		{
			postVOList.set(i, addUserData(postVOList.get(i)));
		}
		PostArrayVO postArrayVO = new PostArrayVO(postVOList);
		return postArrayVO;
		//		Date test=postArrayVO.getPosts().get(0).getLastUpdatedTime();
		//		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//		String s = formatter.format(test);
		//		//System.out.println(s);
		//
		//
		//		return postArrayVO;

	}

	public PostVO getPostById(String postId) {

		return addUserData(postDao.getPostById(postId));
	}

	public Long deletePost(String postId) {


		PostVO postVO = postDao.getPostById(postId);

		//Delete all comments for a particular post before deleting the post
		commentService.deleteAllCommentsByPost(postId);
		//delete all ratings of a particular post
		ratingService.deleteAllRatingsByPost(postId);
		deleteAllLikesByPost(postId);
		if(postVO.getAccountType()==0)
			jointAccountService.decrementPostCount(postVO.getAccountId());
		else if(postVO.getAccountType()==1)
			groupAccountService.decrementPostCount(postVO.getAccountId());
		Long status = postDao.deletePost(postId);
		return status;
	}


	public Long deleteAllPostsByAccount(String accountId)
	{
		List<PostVO> postList=postDao.getPostByAccount(accountId);
		for(PostVO temp:postList)
		{
			deletePost(temp.getPostId());
		}
		return 1L;
	}
	
	public Long deleteAllPostsByUser(String userId)
	{
		List<PostVO> postList= postDao.getPostOfUser(userId);
		for(PostVO temp:postList)
		{
			deletePost(temp.getPostId());
		}
		return 1L;
		
	}

	public Long decrementCommentCount(String postId) {

		return postDao.decrementCommentCount(postId);
	}

	public PostVO addUserData(PostVO postVO)
	{
		UserVO userVO=userService.getUser(postVO.getUserId());
		if(postVO.getAccountType()==0)
		{
			//System.out.println("jointAccountVO"+postVO.getAccountId());
			JointAccountVO jointAccountVO=jointAccountService.getJointAccount(postVO.getAccountId());
			postVO.setAccountName(jointAccountVO.getJointAccountName());
		}
		else
		{
			//System.out.println("groupAccountVO"+postVO.getAccountId());
			GroupAccountVO groupAccountVO=groupAccountService.getGroupAccount(postVO.getAccountId());
			if(groupAccountVO==null){
				
				JointAccountVO jointAccountVO=jointAccountService.getJointAccount(postVO.getAccountId());
				postVO.setAccountName(jointAccountVO.getJointAccountName());
			}
			else 
				postVO.setAccountName(groupAccountVO.getGroupAccountName());
		}
		postVO.setUserName(userVO.getUserName());
		postVO.setProfilePicUrl(userVO.getProfilePicUrl());
		
		return postVO;
	}
	
	
	public Long likePost( String postId,String userId)
	{
		likeDislikeVO=new LikeDislikeVO(postId,userId);
		postDao.addLikeDislikeEntry(likeDislikeVO);
		postVO=getPostById(postId);
		if(postVO.getAccountType()==0)
		{
			jointAccountVO=jointAccountService.getJointAccount(postVO.getAccountId());
			notificationRequestVO=createNotificationObject(likeDislikeVO," has liked your post in joint "+jointAccountVO.getJointAccountName());
		}
		else
		{
			groupAccountVO=groupAccountService.getGroupAccount(postVO.getAccountId());
			notificationRequestVO=createNotificationObject(likeDislikeVO," has liked your post in group "+groupAccountVO.getGroupAccountName());

		}
		if(notificationRequestVO!=null){
			notificationService.sendNotification(notificationRequestVO);}
		return 1L;

		//System.out.println("updateLikeDislikeEntry");

//			if(postDao.getLikeDislikeEntry(postId, userId)!=null)
//			{
//				//System.out.println("updateLikeDislikeEntry");
//				return postDao.updateLikeDislikeEntry(likeDislikeVO);
//			}
//			else 
//			{
//				System.out.println("addLikeDislikeEntry");

//			}
	}

	public Long undoLikePost( String postId,String userId)
	{
		return postDao.removeLikeDislikeEntry(postId,userId);
	}
	
	public Set<LikeDislikeVO> getAllLikeEntry()
	{
		return postDao.getAllLikeEntry();
	}




	public List<UserVO> getAllLikesByPost(String postId) {
		Set<LikeDislikeVO> likeDislikeVOs=postDao.getAllLikesByPost(postId);
		List<UserVO> userVO=new ArrayList<UserVO>();
		for(LikeDislikeVO likeDislikeVO:likeDislikeVOs)
		{
			userVO.add(userService.getUser(likeDislikeVO.getUserId()));
		}
		return userVO;
		
	}
	public Long deleteAllLikesByPost(String postId) {
		
		return postDao.deleteAllLikesByPost(postId);
	}
	
	
	private NotificationRequestVO createNotificationObject(LikeDislikeVO likeDislikeVO,String notificationText)
	{
		PostVO postVO=getPostById(likeDislikeVO.getPostId());
		UserVO userVO=userService.getUser(postVO.getUserId());
		//NotificationRequestVO notificationRequestVO=new NotificationRequestVO();
		String gcmId=userVO.getGcmDeviceId();

		//notificationRequestVO.setTo(userVO.getGcmDeviceId());
		
		
		
		Notification notification =new Notification();
		notification.setTitle(Constants.notificationTitle);
		UserVO userVO2=userService.getUser(likeDislikeVO.getUserId());
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
	

}
