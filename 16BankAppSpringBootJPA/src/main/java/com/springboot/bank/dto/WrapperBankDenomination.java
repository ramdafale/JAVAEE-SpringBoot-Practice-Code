/**
 * 
 */
package com.springboot.bank.dto;

import com.springboot.bank.model.BankDenomination;

/**
 * @author Sumit
 *
 */
public class WrapperBankDenomination {

	BankDenomination bankDenomination;
	Integer amount;
	Long bankId;

	/**
	 * 
	 */
	public WrapperBankDenomination() {
		super();
	}

	/**
	 * @param bankDenomination
	 * @param amount
	 * @param bankId
	 */
	public WrapperBankDenomination(BankDenomination bankDenomination, Integer amount, Long bankId) {
		super();
		this.bankDenomination = bankDenomination;
		this.amount = amount;
		this.bankId = bankId;
	}

	/**
	 * @return the bankDenomination
	 */
	public BankDenomination getBankDenomination() {
		return bankDenomination;
	}

	/**
	 * @param bankDenomination
	 *            the bankDenomination to set
	 */
	public void setBankDenomination(BankDenomination bankDenomination) {
		this.bankDenomination = bankDenomination;
	}

	/**
	 * @return the amount
	 */
	public Integer getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(Integer amount) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WrapperBankDenomination [bankDenomination=" + bankDenomination + ", amount=" + amount + ", bankId="
				+ bankId + "]";
	}

}
