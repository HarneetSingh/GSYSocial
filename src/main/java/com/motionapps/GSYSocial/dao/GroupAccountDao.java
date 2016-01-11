package com.motionapps.GSYSocial.dao;

import java.util.List;

import com.motionapps.GSYSocial.dao.vo.GroupAccountVO;
import com.motionapps.GSYSocial.dao.vo.GroupMemberVO;

public interface GroupAccountDao {
	
	public Long createGroupAccount(GroupAccountVO groupAccountVO);
	
	public Long updateGroupAccount(GroupAccountVO groupAccountVO);
	
	public List<GroupAccountVO> getGroupAccounts();
	
	public GroupAccountVO getGroupAccount(String groupAccountId);
	
	public List<GroupAccountVO> searchGroupAccount(String keyword);
	
	public Long incrementFollowCount(String groupAccountId);
	
	public Long decrementFollowCount(String groupAccountId);
	
	public Long incrementPostCount(String groupAccountId);
	
	public Long decrementPostCount(String groupAccountId);
	
	public Long addGroupMember(GroupMemberVO groupMemberVO);
	
	public List<GroupMemberVO> getGroupMembers(String groupAccountId);

}
