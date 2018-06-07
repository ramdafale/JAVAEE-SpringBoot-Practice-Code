/**
 * 
 */
package com.example.sampleRcv.dto;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author trainee
 *
 */
@Document(collection="transactionCollection")
public class Transaction implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	int transactionId;
	int customerId;
	int accountId;
	int amount;
	String transactionType;
	
	public Transaction() {
		// TODO Auto-generated constructor stub
	}
	
	public Transaction(int transactionId, int customerId, int accountId, int amount, String transactionType) {
		super();
		this.transactionId = transactionId;
		this.customerId = customerId;
		this.accountId = accountId;
		this.amount = amount;
		this.transactionType = transactionType;
	}

	
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", customerId=" + customerId + ", accountId=" + accountId
				+ ", amount=" + amount + ", transactionType=" + transactionType + "]";
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
}
