/**
 * 
 */
package com.springboot.bank.model;

import java.math.BigDecimal;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Sumit
 *
 */

@Entity
@Table(name = "transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;
	@ManyToOne(targetEntity = Customer.class)
	private Customer customer;
	@ManyToOne(targetEntity = Account.class)
	private Account account;
	private BigDecimal amount;
	private String transactionType;
	
	
	private static final Logger LOGGER = Logger.getLogger( Bank.class.getName() );


	/**
	 * 
	 */
	public Transaction() {
		super();
	}

	/**
	 * @param transactionId
	 * @param customer
	 * @param account
	 * @param amount
	 * @param transactionType
	 */
	public Transaction(Customer customer, Account account, BigDecimal amount, String transactionType) {
		this.customer = customer;
		this.account = account;
		this.amount = amount;
		this.transactionType = transactionType;
		LOGGER.info("Inside the Transaction Entity");

	}

	/**
	 * @return the transactionId
	 */
	public Long getTransactionId() {
		return transactionId;
	}

	/**
	 * @param transactionId
	 *            the transactionId to set
	 */
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
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
	 * @return the account
	 */
	public Account getAccount() {
		return account;
	}

	/**
	 * @param account
	 *            the account to set
	 */
	public void setAccount(Account account) {
		this.account = account;
	}

	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * @return the transactionType
	 */
	public String getTransactionType() {
		return transactionType;
	}

	/**
	 * @param transactionType
	 *            the transactionType to set
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	/*
	 * @return toString() representation of given object 
	 */
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", customer=" + customer + ", account=" + account
				+ ", amount=" + amount + ", transactionType=" + transactionType + "]";
	}
}
