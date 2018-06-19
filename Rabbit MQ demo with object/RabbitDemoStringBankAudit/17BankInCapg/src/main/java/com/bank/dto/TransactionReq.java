package com.bank.dto;

import com.bank.model.Transaction;

public class TransactionReq {
	
	private Long customerId;
	private Long accountId;
	private Transaction transaction;
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
	 * @return the transaction
	 */
	public Transaction getTransaction() {
		return transaction;
	}
	/**
	 * @param transaction the transaction to set
	 */
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

}
