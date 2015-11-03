package com.motionapps.GSYSocial.dao.vo;

import java.io.Serializable;

public class CommentVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3535459433798309942L;
	
	private String commentId;


	private String postId;
	
	private String commentText;
	
	private String userName;
	
	private String profilePicUrl;
	
	private String createdTime;
	
	private String lastUpdatedTime;



	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
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
	
	
	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	
	public CommentVO() {
	}

	public CommentVO(String commentId, String postId, String commentText,
			String userName, String profilePicUrl, String createdTime,
			String lastUpdatedTime) {
		super();
		this.commentId = commentId;
		this.postId = postId;
		this.commentText = commentText;
		this.userName = userName;
		this.profilePicUrl = profilePicUrl;
		this.createdTime = createdTime;
		this.lastUpdatedTime = lastUpdatedTime;
	}
	

	
}
