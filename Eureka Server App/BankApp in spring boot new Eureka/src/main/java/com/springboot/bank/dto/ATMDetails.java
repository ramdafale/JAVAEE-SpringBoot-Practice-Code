/**
 * 
 */
package com.springboot.bank.dto;

import java.math.BigDecimal;

/**
 * @author Ram
 *
 */
public class ATMDetails {

	private Long bankId;
	private Long atmId;
	private Long accountId;
	private BigDecimal amount;

	/**
	 * 
	 */
	public ATMDetails() {
		super();
	}

	/**
	 * @param bankId
	 * @param atmId
	 * @param accountId
	 * @param amount
	 */
	public ATMDetails(Long bankId, Long atmId, Long accountId, BigDecimal amount) {
		super();
		this.bankId = bankId;
		this.atmId = atmId;
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
	 * @return the atmId
	 */
	public Long getAtmId() {
		return atmId;
	}

	/**
	 * @param atmId
	 *            the atmId to set
	 */
	public void setAtmId(Long atmId) {
		this.atmId = atmId;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ATMDetails [bankId=" + bankId + ", atmId=" + atmId + ", accountId=" + accountId + ", amount=" + amount
				+ "]";
	}
}
