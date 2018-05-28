package com.bank.dto;

import com.bank.model.Customer;

public class CreateCustomerRequest {
	
	private Customer customer; 
	private Long  bankID;
	
	/**
	 * @return the bankID
	 */
	public Long getBankID() {
		return bankID;
	}
	/**
	 * @param bankID the bankID to set
	 */
	public void setBankID(Long bankID) {
		this.bankID = bankID;
	}
	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}
	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	/**
	 * @param customer
	 * @param bankID
	 */
	public CreateCustomerRequest(Customer customer, Long bankID) {
		super();
		this.customer = customer;
		this.bankID = bankID;
	}
public CreateCustomerRequest() {
	// TODO Auto-generated constructor stub
}
}
