package com.example.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "CUSTOMER")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigDecimal customerId;

	
	private String customerName;

	
	private String pin;

	
	
	
	public Customer() {
		super();
	}

	/**
	 * @param customerId
	 * @param customerName
	 * @param pin
	 */
	public Customer(BigDecimal customerId, String customerName, String pin) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.pin = pin;
		
	}

	
	
	@ManyToOne(targetEntity = Bank.class)
	private Bank bank;
	
	
	
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
	
	/*
	 * this setter is setting up bank into the customer
	 * so that we can get same bank for particular customer
	 * 
	 */
	
	public void setBank(Bank bank) {
		this.bank = bank;
	}

}
