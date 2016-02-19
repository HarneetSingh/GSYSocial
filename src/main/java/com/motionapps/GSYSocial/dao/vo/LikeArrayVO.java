package com.motionapps.GSYSocial.dao.vo;

import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LikeArrayVO {

	private Set<LikeDislikeVO> Likes;

	public Set<LikeDislikeVO> getLikes() {
		return Likes;
	}

	public void setLikes(Set<LikeDislikeVO> likes) {
		Likes = likes;
	}

	public LikeArrayVO(Set<LikeDislikeVO> likes) {
		super();
		Likes = likes;
	}
	
	
}
