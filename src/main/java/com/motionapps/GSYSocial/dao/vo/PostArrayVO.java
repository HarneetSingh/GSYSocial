package com.motionapps.GSYSocial.dao.vo;

import java.util.List;

public class PostArrayVO {
	
	private List<PostVO> posts;

	public List<PostVO> getPosts() {
		return posts;
	}

	public void setPosts(List<PostVO> posts) {
		this.posts = posts;
	}
	
//	public PostArrayVO(){}

	public PostArrayVO(List<PostVO> posts) {
		super();
		this.posts = posts;
	}
	
	
	

}
