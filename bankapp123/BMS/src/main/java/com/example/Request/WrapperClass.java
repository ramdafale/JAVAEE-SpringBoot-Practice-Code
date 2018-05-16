/**
 * 
 */
package com.example.Request;

import java.math.BigDecimal;

import com.example.model.Customer;

/**
 * @author trainee
 *
 */
public class WrapperClass  {

	private BigDecimal id;
	Customer customer;
	
	
	
	
	
	/**
	 * @return the id
	 */
	public BigDecimal getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(BigDecimal id) {
		this.id = id;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WrapperClass [id=" + id + ", customer=" + customer + "]";
	}
	
	
	
}
