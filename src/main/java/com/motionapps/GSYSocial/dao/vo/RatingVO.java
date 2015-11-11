package com.motionapps.GSYSocial.dao.vo;

import java.io.Serializable;

public class RatingVO implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -777169146469953445L;
	
	private String ratingId;
	
	private String postId;
	
	private int ratingValue;
	
	private String userName;
	
	private String profilePicUrl;
	
	private String createdTime;
	
	private String lastUpdatedTime;
	
	public RatingVO() {
		// TODO Auto-generated constructor stub
	}
	
	

	public RatingVO(String ratingId, String postId, int ratingValue,
			String userName, String profilePicUrl, String createdTime,
			String lastUpdatedTime) {
		super();
		this.ratingId = ratingId;
		this.postId = postId;
		this.ratingValue = ratingValue;
		this.userName = userName;
		this.profilePicUrl = profilePicUrl;
		this.createdTime = createdTime;
		this.lastUpdatedTime = lastUpdatedTime;
	}



	public String getRatingId() {
		return ratingId;
	}

	public void setRatingId(String ratingId) {
		this.ratingId = ratingId;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public int getRatingValue() {
		return ratingValue;
	}

	public void setRatingValue(int ratingValue) {
		this.ratingValue = ratingValue;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProfilePicUrl() {
		return profilePicUrl;
	}

	public void setProfilePicUrl(String profilePicUrl) {
		this.profilePicUrl = profilePicUrl;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(String lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}
	

}
