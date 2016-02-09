package com.motionapps.GSYSocial.dao.vo;

import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JointAccountSearchVO {


	
	private Set<JointAccountVO> jointAccounts;
	
	public  JointAccountSearchVO() {
		
	}
	public JointAccountSearchVO(Set<JointAccountVO> jointAccounts) {
		super();
		this.jointAccounts = jointAccounts;
	}

	public Set<JointAccountVO> getJointAccounts() {
		return jointAccounts;
	}

	public void setJointAccounts(Set<JointAccountVO> jointAccounts) {
		this.jointAccounts = jointAccounts;
	}
	

}
