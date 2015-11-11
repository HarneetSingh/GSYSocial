package com.motionapps.GSYSocial.dao;

import java.util.List;

import com.motionapps.GSYSocial.dao.vo.RatingVO;

public interface RatingDao {
	
	public Long addRating(RatingVO ratingVO);
	
	public List<RatingVO> getAllRatings();
	
	public RatingVO getRatingById(String ratingId);
	
	public List<RatingVO> getRatingsByPost(String postId);
	
	public Long updateRating(RatingVO ratingVO);
	
	public Long deleteAllRatingsByPost(String postId);
	
	public Long deleteRating(String ratingId);

}
