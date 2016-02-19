package com.motionapps.GSYSocial.dao.vo;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement
public class PostVO{
	


	private String postId;
	
	private String accountId;
	
	private int accountType;
	
	private String accountName;
	


	private String fileUrl;
	
	private String fileType;
	
	private String postText;
	
	private int ratingValueSad;//0

	private int ratingValueOk;//1

	private int ratingValueHappy;//2

	private int newRating;
	
	private Boolean isRated;
	
	private String userName;
	
	private String profilePicUrl;
	
	private String userId;
	
	private Date createdTime;
	
	private Date lastUpdatedTime;
	
	private String thumbNameUrl;
	
	private String commentCount;
	
	private int noOfLikes;
	
	private Boolean isLiked;
	




	public Boolean getIsLiked() {
		return isLiked;
	}

	public void setIsLiked(Boolean isLiked) {
		this.isLiked = isLiked;
	}



	public int getNoOfLikes() {
		return noOfLikes;
	}

	public void setNoOfLikes(int noOfLikes) {
		this.noOfLikes = noOfLikes;
	}



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

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}


	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
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
	
	
	public Boolean getIsRated() {
		return isRated;
	}

	public void setIsRated(Boolean isRated) {
		this.isRated = isRated;
	}


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

	public PostVO(){}

	public PostVO(String postId, String accountId, int accountType, String accountName, String fileUrl, String fileType,
			String postText, int ratingValueSad, int ratingValueOk, int ratingValueHappy, int newRating,
			Boolean isRated, String userName, String profilePicUrl, String userId, Date createdTime,
			Date lastUpdatedTime, String thumbNameUrl, String commentCount, int noOfLikes, Boolean isLiked) {
		super();
		this.postId = postId;
		this.accountId = accountId;
		this.accountType = accountType;
		this.accountName = accountName;
		this.fileUrl = fileUrl;
		this.fileType = fileType;
		this.postText = postText;
		this.ratingValueSad = ratingValueSad;
		this.ratingValueOk = ratingValueOk;
		this.ratingValueHappy = ratingValueHappy;
		this.newRating = newRating;
		this.isRated = isRated;
		this.userName = userName;
		this.profilePicUrl = profilePicUrl;
		this.userId = userId;
		this.createdTime = createdTime;
		this.lastUpdatedTime = lastUpdatedTime;
		this.thumbNameUrl = thumbNameUrl;
		this.commentCount = commentCount;
		this.noOfLikes = noOfLikes;
		this.isLiked = isLiked;
	}







}
