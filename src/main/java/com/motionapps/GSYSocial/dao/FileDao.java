package com.motionapps.GSYSocial.dao;

import com.motionapps.GSYSocial.dao.vo.FileVO;

public interface FileDao {
	
	public Long uploadFile(FileVO fileVO);
	
	public FileVO downloadFile(String fileId);
	
	public Long deleteFile(String fileUrl);
}
