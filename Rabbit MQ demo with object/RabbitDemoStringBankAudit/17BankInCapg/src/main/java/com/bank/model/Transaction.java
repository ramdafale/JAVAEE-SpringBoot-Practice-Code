package com.bank.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Table(name = "transaction_details")
@Entity
@Component
public class Transaction extends BaseEntity{
	@Id
	@SequenceGenerator(name = "transaction_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
	@Column(name = "transaction_id")
	private Long transactionId;
	@Column(name = "amount")
	private BigDecimal amount;
	@Column(name = "transaction_type")
	private String transactionType;

	@ManyToOne(targetEntity = Customer.class)
	private Customer customer;

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @param account
	 *            the account to set
	 */
	public void setAccount(Account account) {
		this.account = account;
	}

	@ManyToOne(targetEntity = Account.class)
	private Account account;

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
	public void setTransactionId(final Long transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * @return the customerId
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
	public void setTransactionType(final String transactionType) {
		this.transactionType = transactionType;
	}

	public Transaction() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param customerId
	 * @param accountId
	 * @param amount
	 * @param transactionType
	 */
	public Transaction(BigDecimal amount, String transactionType) {
		super();
		/*
		 * this.customerId = customerId; this.accountId = accountId;
		 */
		this.amount = amount;
		this.transactionType = transactionType;
	}

}
