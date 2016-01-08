package com.motionapps.GSYSocial.dao.vo;

import java.io.Serializable;
import java.util.Date;

public class RatingVO implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -777169146469953445L;

	private String ratingId;

	private String postId;

	private int ratingValue;

	private String userId;

	private String userName;

	private String profilePicUrl;

	private Date createdTime;

	private Date lastUpdatedTime;

	public RatingVO() {
		// TODO Auto-generated constructor stub
	}

	public RatingVO(String ratingId, String postId, int ratingValue, String userId, String userName,
			String profilePicUrl, Date createdTime, Date lastUpdatedTime) {
		super();
		this.ratingId = ratingId;
		this.postId = postId;
		this.ratingValue = ratingValue;
		this.userId = userId;
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

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


}
