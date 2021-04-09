package com.lhh.bo;

import java.io.Serializable;

/**
 * @author lihonghao
 * @date 2021/4/10 0:05
 */
public class UserBO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userId;
	private String faceData;

	public UserBO() {
	}

	public UserBO(String userId, String faceData) {
		this.userId = userId;
		this.faceData = faceData;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFaceData() {
		return faceData;
	}

	public void setFaceData(String faceData) {
		this.faceData = faceData;
	}
}
