package com.motionapps.GSYSocial.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.motionapps.GSYSocial.dao.RatingDao;
import com.motionapps.GSYSocial.dao.vo.RatingArrayVO;
import com.motionapps.GSYSocial.dao.vo.RatingVO;

public class RatingService {
	
	@Autowired
	private RatingDao ratingDao;
	@Autowired
	private PostService postService;
	

	public void setRatingDao(RatingDao ratingDao) {
		this.ratingDao = ratingDao;
	}
	
	
	public void setPostService(PostService postService) {
		this.postService = postService;
	}


	public Long addRating(RatingVO ratingVO)
	{
		if(ratingVO.getRatingValue()>5)
			return 0L;
		ratingVO.setRatingId(UUID.randomUUID().toString());
		ratingDao.addRating(ratingVO);
		return postService.addRating(ratingVO);

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
		return postService.updateRating(ratingVO);
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
	
	

}
