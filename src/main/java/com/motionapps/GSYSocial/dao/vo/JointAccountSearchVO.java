package com.motionapps.GSYSocial.dao.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JointAccountSearchVO {


	
	private List<JointAccountVO> jointAccounts;
	
	public  JointAccountSearchVO() {
		
	}
	public JointAccountSearchVO(List<JointAccountVO> jointAccounts) {
		super();
		this.jointAccounts = jointAccounts;
	}

	public List<JointAccountVO> getJointAccounts() {
		return jointAccounts;
	}

	public void setJointAccounts(List<JointAccountVO> jointAccounts) {
		this.jointAccounts = jointAccounts;
	}
}
