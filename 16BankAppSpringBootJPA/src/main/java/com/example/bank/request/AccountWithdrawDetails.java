/**
 * 
 */
package com.example.bank.request;

import java.math.BigDecimal;

/**
 * @author trainee
 *
 */
public class AccountWithdrawDetails {

	long accountId;
	BigDecimal amount;
	long bankId;
	long customerId;

	/**
	 * 
	 */
	public AccountWithdrawDetails() {
		super();
	}

	/**
	 * @param accountId
	 * @param amount
	 * @param bankId
	 * @param customerId
	 */
	public AccountWithdrawDetails(long accountId, BigDecimal amount, long bankId, long customerId) {
		super();
		this.accountId = accountId;
		this.amount = amount;
		this.bankId = bankId;
		this.customerId = customerId;
	}

	/**
	 * @return the accountId
	 */
	public long getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId
	 *            the accountId to set
	 */
	public void setAccountId(long accountId) {
		this.accountId = accountId;
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
	 * @return the bankId
	 */
	public long getBankId() {
		return bankId;
	}

	/**
	 * @param bankId
	 *            the bankId to set
	 */
	public void setBankId(long bankId) {
		this.bankId = bankId;
	}

	/**
	 * @return the customerId
	 */
	public long getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId
	 *            the customerId to set
	 */
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AccountWithdrawDetails [accountId=" + accountId + ", amount=" + amount + ", bankId=" + bankId
				+ ", customerId=" + customerId + "]";
	}

}
