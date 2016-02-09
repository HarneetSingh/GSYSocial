package com.motionapps.GSYSocial.dao;

import java.util.List;
import java.util.Set;

import com.motionapps.GSYSocial.dao.vo.InviteRequestVO;



public interface InviteRequestDao {
	
	public Long inviteUser(InviteRequestVO inviteRequestVO);
	
	public Long inviteAccepted(InviteRequestVO inviteRequestVO);
	
	public Long inviteDeleted(InviteRequestVO inviteRequestVO);
	
	public InviteRequestVO getInviteRequestDetails(String inviteRequestId);
	
	public InviteRequestVO getInviteRequest(String userId);

	public Set<InviteRequestVO> getInviteRequests(String userId);

}
