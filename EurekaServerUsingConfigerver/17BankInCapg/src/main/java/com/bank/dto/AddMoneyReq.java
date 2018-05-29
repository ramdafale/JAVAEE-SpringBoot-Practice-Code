package com.bank.dto;

import java.math.BigDecimal;

public class AddMoneyReq {
	
	private Long atmId;
	private Long bankId;
	private BigDecimal amount;
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
	 * @return the bankId
	 */
	public Long getBankId() {
		return bankId;
	}
	/**
	 * @param bankId the bankId to set
	 */
	public void setBankId(Long bankId) {
		this.bankId = bankId;
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
