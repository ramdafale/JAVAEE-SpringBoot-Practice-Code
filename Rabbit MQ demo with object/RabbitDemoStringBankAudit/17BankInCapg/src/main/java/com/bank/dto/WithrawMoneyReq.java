package com.bank.dto;

import java.math.BigDecimal;

public class WithrawMoneyReq {
	private Long accountId;
	private BigDecimal amount;
	private Long atmId;
	private Long customerId;
	
	/**
	 * @return the customerId
	 */
	public Long getCustomerId() {
		return customerId;
	}
	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	/**
	 * @return the atmId
	 */
	public Long getAtmId() {
		return atmId;
	}
	/**
	 * @param atmId the atmId to set
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
	 * @param accountId the accountId to set
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
	 * @param amount the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
