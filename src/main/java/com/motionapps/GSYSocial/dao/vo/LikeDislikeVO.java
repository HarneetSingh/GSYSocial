package com.motionapps.GSYSocial.dao.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LikeDislikeVO {
	
	private String postId;
	
	private String userId;
	



	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	
	public LikeDislikeVO() {
	}
	
	
	public LikeDislikeVO(String postId, String userId) {
		super();
		this.postId = postId;
		this.userId = userId;
	}

}
