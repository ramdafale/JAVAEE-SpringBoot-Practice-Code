package com.bank.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {
	private String userId;

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BaseEntity() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param userId
	 */
	public BaseEntity(String userId) {
		super();
		this.userId = userId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BaseEntity [userId=" + userId + "]";
	}

}
