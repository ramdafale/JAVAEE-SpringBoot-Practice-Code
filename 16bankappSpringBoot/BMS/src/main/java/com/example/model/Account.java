package com.example.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {

	@Id
	private BigDecimal accountId;
	private Customer customerId;
	private BigDecimal amount;
	private Bank bankId;

	public BigDecimal getAccountId() {
		return accountId;
	}

	public void setAccountId(BigDecimal accountId) {
		this.accountId = accountId;
	}

	public Customer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Customer customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Bank getBankId() {
		return bankId;
	}

	public void setBankId(Bank bankId) {
		this.bankId = bankId;
	}

	public Account(BigDecimal accountId, Customer customerId, BigDecimal amount, Bank bankId) {
		super();
		this.accountId = accountId;
		this.customerId = customerId;
		this.amount = amount;
		this.bankId = bankId;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", customerId=" + customerId + ", amount=" + amount + ", bankId="
				+ bankId + "]";
	}

	public Account() {
		super();
	}

}
