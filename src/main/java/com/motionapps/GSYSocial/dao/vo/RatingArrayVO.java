package com.motionapps.GSYSocial.dao.vo;

import java.util.List;

public class RatingArrayVO {

	private List<RatingVO> ratings;

	public List<RatingVO> getRatings() {
		return ratings;
	}

	public void setRatings(List<RatingVO> ratings) {
		this.ratings = ratings;
	}

	public RatingArrayVO(List<RatingVO> ratings) {
		super();
		this.ratings = ratings;
	}
	
	
}
