/**
 * 
 */
package com.springboot.bank.dto;

import java.math.BigDecimal;

/**
 * @author Sumit
 *
 */
public class AccountDetails {

	private Long bankId;
	private Long customerId;
	private Long accountId;
	private BigDecimal amount;

	/**
	 * 
	 */
	public AccountDetails() {
		super();
	}

	/**
	 * @param bankId
	 * @param customerId
	 * @param accountId
	 * @param amountToBeAdded
	 */
	public AccountDetails(Long bankId, Long customerId, Long accountId, BigDecimal amount) {
		super();
		this.bankId = bankId;
		this.customerId = customerId;
		this.accountId = accountId;
		this.amount = amount;
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
	 * @return the accountId
	 */
	public Long getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId
	 *            the accountId to set
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	/**
	 * @return the amountToBeAdded
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amountToBeAdded
	 *            the amountToBeAdded to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AccountDetails [bankId=" + bankId + ", customerId=" + customerId + ", accountId=" + accountId
				+ ", amount=" + amount + "]";
	}

}
