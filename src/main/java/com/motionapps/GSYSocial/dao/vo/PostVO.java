package com.motionapps.GSYSocial.dao.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
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
	
	private int totalRating;
	
	private int noOfRatings;

	private int newRating;
	

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

	public int getTotalRating() {
		return totalRating;
	}

	public void setTotalRating(int totalRating) {
		this.totalRating = totalRating;
	}

	public int getNoOfRatings() {
		return noOfRatings;
	}

	public void setNoOfRatings(int noOfRatings) {
		this.noOfRatings = noOfRatings;
	}
	
	public int getNewRating() {
		return newRating;
	}

	public void setNewRating(int newRating) {
		this.newRating = newRating;
	}

	public PostVO(String postId, String jointAccountId, String fileUrl,
			String fileType, String postText, int totalRating, int noOfRatings)
	{
		super();
		this.postId = postId;
		this.jointAccountId = jointAccountId;
		this.fileUrl = fileUrl;
		this.fileType = fileType;
		this.postText = postText;
		this.totalRating = totalRating;
		this.noOfRatings = noOfRatings;
	}
	
	public PostVO(){}


	


}
