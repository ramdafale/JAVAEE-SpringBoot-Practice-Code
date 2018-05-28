package com.bank.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "account_details")
@Entity
public class Account extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account_id")
	private Long accountId;
	@Column(name = "amount")
	private BigDecimal amount;

	@ManyToOne(targetEntity = Customer.class)
	private Customer customer;

	@ManyToOne(targetEntity = Bank.class)
	private Bank bank;

	public Long getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId
	 *            the accountId to set
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(final Customer customer) {
		this.customer = customer;
	}

	/**
	 * @param bank
	 *            the bank to set
	 */
	public void setBank(final Bank bank) {
		this.bank = bank;
	}

	/**
	 * @param accountId
	 *            the accountId to set
	 */
	public void setAccountId(final Long accountId) {
		this.accountId = accountId;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(final BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * @return the bankId
	 */

	public Account() {

	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", amount=" + amount + ", customer=" + customer + ", bank=" + bank
				+ "]";
	}

	/**
	 * @param accountId
	 * @param amount
	 * @param customer
	 * @param bank
	 */
	public Account(Long accountId, BigDecimal amount, Customer customer, Bank bank) {
		this.accountId = accountId;
		this.amount = amount;
		this.customer = customer;
		this.bank = bank;
	}

}
