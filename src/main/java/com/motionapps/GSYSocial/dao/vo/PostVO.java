package com.motionapps.GSYSocial.dao.vo;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class PostVO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5915203332339230987L;

	private String postId;
	
	private String jointAccountId;
	
	private String fileUrl;
	
	private String fileType;
	
	private String postText;
	
	private int ratingValueSad;//0

	private int ratingValueOk;//1

	private int ratingValueHappy;//2

	private int newRating;
	
	private String userName;
	
	private String profilePicUrl;
	
	private String userId;
	
	private Date createdTime;
	
	private Date lastUpdatedTime;
	
	private String thumbNameUrl;
	
	private String commentCount;
	
	


	public String getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(String commentCount) {
		this.commentCount = commentCount;
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


	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getJointAccountId() {
		return jointAccountId;
	}

	public void setJointAccountId(String jointAccountId) {
		this.jointAccountId = jointAccountId;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getPostText() {
		return postText;
	}

	public void setPostText(String postText) {
		this.postText = postText;
	}
	
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}


	public int getNewRating() {
		return newRating;
	}

	public void setNewRating(int newRating) {
		this.newRating = newRating;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getThumbNameUrl() {
		return thumbNameUrl;
	}

	public void setThumbNameUrl(String thumbNameUrl) {
		this.thumbNameUrl = thumbNameUrl;
	}
	
	public int getRatingValueSad() {
		return ratingValueSad;
	}

	public void setRatingValueSad(int ratingValueSad) {
		this.ratingValueSad = ratingValueSad;
	}

	public int getRatingValueOk() {
		return ratingValueOk;
	}

	public void setRatingValueOk(int ratingValueOk) {
		this.ratingValueOk = ratingValueOk;
	}

	public int getRatingValueHappy() {
		return ratingValueHappy;
	}

	public void setRatingValueHappy(int ratingValueHappy) {
		this.ratingValueHappy = ratingValueHappy;
	}
	
	
	public PostVO(){}

	public java.util.Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public java.util.Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public PostVO(String postId, String jointAccountId, String fileUrl, String fileType, String postText,
			int ratingValueSad, int ratingValueOk, int ratingValueHappy, int newRating, String userName,
			String profilePicUrl, String userId, Date createdTime, Date lastUpdatedTime, String thumbNameUrl,
			String commentCount) {
		super();
		this.postId = postId;
		this.jointAccountId = jointAccountId;
		this.fileUrl = fileUrl;
		this.fileType = fileType;
		this.postText = postText;
		this.ratingValueSad = ratingValueSad;
		this.ratingValueOk = ratingValueOk;
		this.ratingValueHappy = ratingValueHappy;
		this.newRating = newRating;
		this.userName = userName;
		this.profilePicUrl = profilePicUrl;
		this.userId = userId;
		this.createdTime = createdTime;
		this.lastUpdatedTime = lastUpdatedTime;
		this.thumbNameUrl = thumbNameUrl;
		this.commentCount = commentCount;
	}




}
