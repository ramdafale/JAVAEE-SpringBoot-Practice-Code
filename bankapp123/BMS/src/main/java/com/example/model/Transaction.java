/*package com.example.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Transaction {

	@Id
	private BigDecimal transacationId;
	private Customer customerID;
	private Account accountId;
	private String transacationType;
	private BigDecimal amount;

	public BigDecimal getTransacationId() {
		return transacationId;
	}

	public void setTransacationId(BigDecimal transacationId) {
		this.transacationId = transacationId;
	}

	public Customer getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Customer customerID) {
		this.customerID = customerID;
	}

	public Account getAccountId() {
		return accountId;
	}

	public void setAccountId(Account accountId) {
		this.accountId = accountId;
	}

	public String getTransacationType() {
		return transacationType;
	}

	public void setTransacationType(String transacationType) {
		this.transacationType = transacationType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Transaction(BigDecimal transacationId, Customer customerID, Account accountId, String transacationType,
			BigDecimal amount) {
		super();
		this.transacationId = transacationId;
		this.customerID = customerID;
		this.accountId = accountId;
		this.transacationType = transacationType;
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Transaction [transacationId=" + transacationId + ", customerID=" + customerID + ", accountId="
				+ accountId + ", transacationType=" + transacationType + ", amount=" + amount + "]";
	}

	public Transaction() {
		super();
	}

}
*/