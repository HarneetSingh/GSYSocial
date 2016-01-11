package com.motionapps.GSYSocial.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.motionapps.GSYSocial.dao.GroupAccountDao;
import com.motionapps.GSYSocial.dao.vo.GroupAccountSearchVO;
import com.motionapps.GSYSocial.dao.vo.GroupAccountVO;
import com.motionapps.GSYSocial.dao.vo.GroupMemberSearchVO;
import com.motionapps.GSYSocial.dao.vo.GroupMemberVO;
import com.motionapps.GSYSocial.dao.vo.UserVO;

public class GroupAccountService {
	
	@Autowired
	private GroupAccountDao groupAccountDao;
	
	@Autowired
	private UserService userService;
	
	private UserVO userVO;
	
	private GroupAccountVO groupAccountVO;
	
	
	public void setGroupAccountDao(GroupAccountDao groupAccountDao) {
		this.groupAccountDao = groupAccountDao;
	}


	public Long createGroupAccount(GroupAccountVO groupAccountVO)
	{
		groupAccountVO.setGroupAccountId(UUID.randomUUID().toString());
		groupAccountDao.createGroupAccount(groupAccountVO);
		return 1L;
	}
	
	public GroupAccountSearchVO searchGroupAccount(String keyword)
	{
		List<GroupAccountVO> groupAccountVO=groupAccountDao.searchGroupAccount(keyword);

		for (GroupAccountVO groupAccountVO2 : groupAccountVO) {
			userVO=userService.getUser(groupAccountVO2.getGroupAdminId());
			groupAccountVO2.setGroupAdminName(userVO.getUserName());
			groupAccountVO2.setGroupAdminProfilePic(userVO.getProfilePicUrl());
		}
		return new GroupAccountSearchVO(groupAccountVO);
	}

	public GroupAccountVO getGroupAccount(String groupAccountId)
	{
		groupAccountVO=groupAccountDao.getGroupAccount(groupAccountId);
		if(groupAccountVO!=null){
		userVO=userService.getUser(groupAccountVO.getGroupAdminId());
		groupAccountVO.setGroupAdminName(userVO.getUserName());
		groupAccountVO.setGroupAdminProfilePic(userVO.getProfilePicUrl());}
		return groupAccountVO;
	}
	
	public GroupAccountSearchVO getGroupAccounts()
	{
		List<GroupAccountVO> groupAccountVO=groupAccountDao.getGroupAccounts();

		for (GroupAccountVO groupAccountVO2 : groupAccountVO) {
			userVO=userService.getUser(groupAccountVO2.getGroupAdminId());
			groupAccountVO2.setGroupAdminName(userVO.getUserName());
			groupAccountVO2.setGroupAdminProfilePic(userVO.getProfilePicUrl());
		}
		return new GroupAccountSearchVO(groupAccountVO);
	}
	
	public Long addGroupMember(GroupMemberVO groupMemberVO)
	{
		groupAccountDao.addGroupMember(groupMemberVO);
		return 1L;
	}
	
	public GroupMemberSearchVO getGroupMembers(String groupAccountId)
	{
		List<GroupMemberVO> groupMembers=groupAccountDao.getGroupMembers(groupAccountId);
		for(GroupMemberVO groupMemberVO : groupMembers)
		{
			userVO=userService.getUser(groupMemberVO.getUserId());
			groupMemberVO.setUserName(userVO.getUserName());
			groupMemberVO.setProfilePicUrl(userVO.getProfilePicUrl());
		}
		return new GroupMemberSearchVO(groupMembers);
	}
}
