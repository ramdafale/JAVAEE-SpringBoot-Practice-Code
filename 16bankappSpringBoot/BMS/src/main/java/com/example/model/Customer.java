package com.example.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigDecimal customerId;

	@Column(name = "customer_Name")
	private String customerName;

	@Column(name = "pin")
	private String pin;

	private BigDecimal bankId;
	
	
	public Customer() {
		super();
	}

	/**
	 * @param customerId
	 * @param customerName
	 * @param pin
	 */
	public Customer(BigDecimal customerId, String customerName, String pin, BigDecimal bankId) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.pin = pin;
		this.bankId=bankId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", pin=" + pin + "]";
	}

	public BigDecimal getCustomerId() {
		return customerId;
	}

	public void setCustomerId(BigDecimal customerId) {
		this.customerId = customerId;
	}

	public String getcustomerName() {
		return customerName;
	}

	public void setcustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

}
