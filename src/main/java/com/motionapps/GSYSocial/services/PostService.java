package com.motionapps.GSYSocial.services;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;


import com.motionapps.GSYSocial.dao.PostDao;
import com.motionapps.GSYSocial.dao.vo.PostArrayVO;
import com.motionapps.GSYSocial.dao.vo.PostVO;
import com.motionapps.GSYSocial.dao.vo.RatingVO;
import com.motionapps.GSYSocial.dao.vo.UserVO;

public class PostService {



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



	public void setGroupAccountService(GroupAccountService groupAccountService) {
		this.groupAccountService = groupAccountService;
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


	public PostArrayVO getPostByAccount(String accountId) {

		List<PostVO> postVOList=postDao.getPostByAccount(accountId);


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

	public Long decrementCommentCount(String postId) {

		return postDao.decrementCommentCount(postId);
	}

	public PostVO addUserData(PostVO postVO)
	{
		UserVO userVO=userService.getUser(postVO.getUserId());
		postVO.setUserName(userVO.getUserName());
		postVO.setProfilePicUrl(userVO.getProfilePicUrl());
		return postVO;
	}
}
