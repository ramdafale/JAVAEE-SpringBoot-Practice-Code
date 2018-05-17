package com.example.bank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table(name="transaction_details")
@Entity
public class Transaction {
	@Id
	@SequenceGenerator(name="transaction_seq", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="transaction_seq")
	@Column(name="transaction_id")
	private Long transactionId;
	@Column(name="customer_id")
	private Long customerId;
	@Column(name="account_id")
	private Long accountId;
	@Column(name="amount")
	private float amount;
	@Column(name="transaction_type")
	private String transactionType;
	/**
	 * @return the transactionId
	 */
	public Long getTransactionId() {
		return transactionId;
	}
	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
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
	 * @return the amount
	 */
	public float getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}
	/**
	 * @return the transactionType
	 */
	public String getTransactionType() {
		return transactionType;
	}
	/**
	 * @param transactionType the transactionType to set
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	
public Transaction() {
	// TODO Auto-generated constructor stub
}
/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "Transaction [transactionId=" + transactionId + ", customerId=" + customerId + ", accountId=" + accountId
			+ ", amount=" + amount + ", transactionType=" + transactionType + "]";
}


}
