/**
 * 
 */
package com.springboot.bank.dto;

import com.springboot.bank.model.Account;

/**
 * @author Sumit
 *
 */
public class WrapperBankCustomerAccount {

	private Account account;
	private Long customerId;
	private Long bankId;

	/**
	 * 
	 */
	public WrapperBankCustomerAccount() {
		super();
	}

	/**
	 * @param account
	 * @param customerId
	 * @param bankId
	 */
	public WrapperBankCustomerAccount(Account account, Long customerId, Long bankId) {
		super();
		this.account = account;
		this.customerId = customerId;
		this.bankId = bankId;
	}

	/**
	 * @return the account
	 */
	public Account getAccount() {
		return account;
	}

	/**
	 * @param account
	 *            the account to set
	 */
	public void setAccount(Account account) {
		this.account = account;
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
		return "WrapperBankCustomerAccount [account=" + account + ", customerId=" + customerId + ", bankId=" + bankId
				+ "]";
	}

}
