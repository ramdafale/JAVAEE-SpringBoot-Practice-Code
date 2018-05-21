/**
 * 
 */
package com.springboot.bank.model;

import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.lang.NonNull;

/**
 * @author Sumit
 *It will represents the CUstomer Entity.
 */

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;
	@Column(nullable = false, length = 100)
	private String customerName;
	@NonNull
	private Integer customerPin;
	@ManyToOne(targetEntity = Bank.class)
	private Bank bank;
	
	private static final Logger LOGGER = Logger.getLogger( Customer.class.getName() );

	
	// @JoinTable(name = "bank_customer", joinColumns = @JoinColumn(name =
	// "customerId"), inverseJoinColumns = @JoinColumn(name = "bankId"))

	/**
	 * 
	 */
	public Customer() {
		super();
	}

	/**
	 * @param customerId
	 * @param customerName
	 * @param customerPin
	 * @param bank
	 */
	public Customer( String customerName, Integer customerPin, Bank bank) {
		this.customerName = customerName;
		this.customerPin = customerPin;
		this.bank = bank;
		LOGGER.info("Inside the Customer Entity");
	
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
	 * @return the customerPin
	 */
	public Integer getCustomerPin() {
		return customerPin;
	}

	/**
	 * @param customerPin
	 *            the customerPin to set
	 */
	public void setCustomerPin(Integer customerPin) {
		this.customerPin = customerPin;
	}

	/**
	 * @return the bank
	 */
	public Bank getBank() {
		return bank;
	}

	/**
	 * @param bank
	 *            the bank to set
	 */
	public void setBank(Bank bank) {
		this.bank = bank;
	}

	/*
	 * @return toString() representation of given object 
	 */
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerPin=" + customerPin
				+ ", bank=" + bank + "]";
	}
}
