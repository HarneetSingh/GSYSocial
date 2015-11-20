package com.motionapps.GSYSocial.dao.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.junit.Ignore;

@XmlRootElement
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class NotificationResposeVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7288954161125581238L;
	
	long multicast_id;


	int success;
	int failure;
	int canonical_ids;
	

	Object results;
	
	public NotificationResposeVO(long multicast_id, int success, int failure,
			int canonical_ids, Object results) {
		super();
		this.multicast_id = multicast_id;
		this.success = success;
		this.failure = failure;
		this.canonical_ids = canonical_ids;
		this.results = results;
	}
	public int getCanonical_ids() {
		return canonical_ids;
	}
	public void setCanonical_ids(int canonical_ids) {
		this.canonical_ids = canonical_ids;
	}
	public Object getResults() {
		return results;
	}
	public void setResults(Object results) {
		this.results = results;
	}

	
	public long getMulticast_id() {
		return multicast_id;
	}
	public void setMulticast_id(long multicast_id) {
		this.multicast_id = multicast_id;
	}
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public int getFailure() {
		return failure;
	}
	public void setFailure(int failure) {
		this.failure = failure;
	}
	
	public NotificationResposeVO() {
	}
	

	
}


//{
//    "multicast_id": 4914165310030445000,
//    "success": 0,
//    "failure": 1,
//    "canonical_ids": 0,
//    "results": [
//        {
//            "error": "MismatchSenderId"
//        }
//    ]
//}