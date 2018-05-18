package com.example.bank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "customer_details")
@Entity
public class Customer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", pin=" + pin + ", bank="
				+ bank + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long customerId;
	@Column(name = "customer_name")
	private String customerName;
	@Column(name = "pin")
	private Long pin;

	@ManyToOne(targetEntity = Bank.class)
	private Bank bank;

	/**
	 * @param customerId
	 * @param customerName
	 * @param pin
	 * @param bank
	 */
	public Customer(Long customerId, String customerName, Long pin, Bank bank) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.pin = pin;
		this.bank = bank;
	}
	
	public Customer( String customerName, Long pin) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.pin = pin;
		this.bank = bank;
	}

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(int i, String string, int j) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the customerId
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId
	 *            the customerId to set
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName
	 *            the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the pin
	 */
	public Long getPin() {
		return pin;
	}

	/**
	 * @param pin
	 *            the pin to set
	 */
	public void setPin(Long pin) {
		this.pin = pin;
	}

	/**
	 * @param bank
	 *            the bank to set
	 */
	public void setBank(Bank bank) {
		this.bank = bank;
	}

}
