/**
 * 
 */
package com.springboot.bank.dto;

import com.springboot.bank.model.Customer;

/**
 * @author trainee
 *
 */
public class WrapperBankCustomer {

	Customer customer;
	Long bankId;

	/**
	 * 
	 */
	public WrapperBankCustomer() {
		super();
	}

	/**
	 * @param customer
	 * @param bankId
	 */
	public WrapperBankCustomer(Customer customer, Long bankId) {
		super();
		this.customer = customer;
		this.bankId = bankId;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the bankId
	 */
	public Long getBankId() {
		return bankId;
	}

	/**
	 * @param bankId
	 *            the bankId to set
	 */
	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WrapperBankCustomer [customer=" + customer + ", bankId=" + bankId + "]";
	}
}
