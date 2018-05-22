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
 * @author Ram It will represents the Account Entity.
 */
@Entity
@Table(name = "account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountId;
	@ManyToOne(targetEntity = Bank.class)
	private Bank bank;
	@ManyToOne(targetEntity = Customer.class)
	private Customer customer;
	private BigDecimal amount;

	/*
	 * @Entity This is logger used instead of Sysout.
	 */
	private static final Logger LOGGER = Logger.getLogger(Account.class.getName());

	/**
	 * 
	 */
	public Account() {
		super();
	}

	/**
	 * @param accountId
	 *            acted as primary key of the table account
	 * @param bank
	 * @param customer
	 * @param amount
	 */
	public Account(final Bank bank, final Customer customer, final BigDecimal amount) {
		this.bank = bank;
		this.customer = customer;
		this.amount = amount;
		LOGGER.info("Inside the account Entity");
	}

	/**
	 * @return the accountId
	 */
	public Long getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId
	 *            the accountId to set
	 */
	public void setAccountId(final Long accountId) {
		this.accountId = accountId;
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
	public void setBank(final Bank bank) {
		this.bank = bank;
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
	public void setCustomer(final Customer customer) {
		this.customer = customer;
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
	public void setAmount(final BigDecimal amount) {
		this.amount = amount;
	}

	/*
	 * @return toString() representation of given object
	 */
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", bank=" + bank + ", customer=" + customer + ", amount=" + amount
				+ "]";
	}
}
