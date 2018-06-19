package com.bank.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/*@JsonIgnoreProperties(ignoreUnknown = true)*/
public class AuditLog implements Serializable {
	
	private String eventName;
	private String eventType;
	private Timestamp eventDate;
	private String userId;
	private Object oldValue;
	private Object newValue;
	/**
	 * @return the eventName
	 */
	public String getEventName() {
		return eventName;
	}
	/**
	 * @param eventName the eventName to set
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	/**
	 * @return the eventType
	 */
	public String getEventType() {
		return eventType;
	}
	/**
	 * @param eventType the eventType to set
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	/**
	 * @return the eventDate
	 */
	public Timestamp getEventDate() {
		return eventDate;
	}
	/**
	 * @param eventDate the eventDate to set
	 */
	public void setEventDate(Timestamp eventDate) {
		this.eventDate = eventDate;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the oldValue
	 */
	public Object getOldValue() {
		return oldValue;
	}
	/**
	 * @param oldValue the oldValue to set
	 */
	public void setOldValue(Object oldValue) {
		this.oldValue = oldValue;
	}
	/**
	 * @return the newValue
	 */
	public Object getNewValue() {
		return newValue;
	}
	/**
	 * @param newValue the newValue to set
	 */
	public void setNewValue(Object newValue) {
		this.newValue = newValue;
	}
	
	public AuditLog() {
		
	}
	@Override
	public String toString() {
		return "AuditLog [eventName=" + eventName + ", eventType=" + eventType + ", eventDate=" + eventDate
				+ ", userId=" + userId + ", oldValue=" + oldValue + ", newValue=" + newValue + "]";
	}
	/**
	 * @param eventName
	 * @param eventType
	 * @param eventDate
	 * @param userId
	 * @param oldValue
	 * @param newValue
	 */
	public AuditLog(String eventName, String eventType,Timestamp eventDate, String userId, Object oldValue,
			Object newValue) {
		super();
		this.eventName = eventName;
		this.eventType = eventType;
		this.eventDate = eventDate;
		this.userId = userId;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}
	
	

}
