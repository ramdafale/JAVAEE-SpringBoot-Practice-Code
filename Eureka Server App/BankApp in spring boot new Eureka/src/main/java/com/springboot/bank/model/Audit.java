package com.springboot.bank.model;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.Id;


/**
 * @author ram
 *
 */

public class Audit {

	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Id
	private String uuid;
	private String eventName;
	private String eventType;
	private String timestamp;
	private BaseEntity oldValue;
	private BaseEntity newValue;
	
	
	
	
	
	/**
	 * 
	 */
	public Audit() {
		super();
	}
	
	
	/**
	 * @param uuid
	 * @param eventName
	 * @param eventType
	 * @param userId
	 * @param oldValue
	 * @param newValue
	 */
	public Audit(String uuid,String eventName, String eventType, String timestamp, BaseEntity oldValue, BaseEntity newValue) {
		super();
		this.uuid = uuid;
		this.eventName = eventName;
		this.eventType = eventType;
		this.timestamp = timestamp;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	


	/**
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param uuid
	 *            the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = UUID.randomUUID().toString();
	}

	/**
	 * @return the eventName
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 * @param eventName
	 *            the eventName to set
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
	 * @param eventType
	 *            the eventType to set
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	/**
	 * @return the oldValue
	 */
	public BaseEntity getOldValue() {
		return oldValue;
	}

	/**
	 * @param oldValue
	 *            the oldValue to set
	 */
	public void setOldValue(BaseEntity oldValue) {
		this.oldValue = oldValue;
	}

	/**
	 * @return the newValue
	 */
	public BaseEntity getNewValue() {
		return newValue;
	}

	/**
	 * @param newValue
	 *            the newValue to set
	 */
	public void setNewValue(BaseEntity newValue) {
		this.newValue = newValue;
	}

	
	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Audit [uuid=" + uuid + ", eventName=" + eventName + ", eventType=" + eventType + ", oldValue="
				+ oldValue + ", newValue=" + newValue + "]";
	}

	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp =  new Timestamp(System.currentTimeMillis()).toString();
	}

}
