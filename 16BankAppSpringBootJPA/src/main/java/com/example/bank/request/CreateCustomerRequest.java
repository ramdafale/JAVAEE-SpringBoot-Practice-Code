package com.example.bank.request;

import com.example.bank.model.Customer;

public class CreateCustomerRequest {
	
	private Customer customer; 
	private long  bankID;
	/**
	 * @return the bankID
	 */
	public long getBankID() {
		return bankID;
	}
	/**
	 * @param bankID the bankID to set
	 */
	public void setBankID(long bankID) {
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

}
