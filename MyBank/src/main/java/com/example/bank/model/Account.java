package com.example.bank.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



/*
 * @class Account class use  to  create new account and deposit money  withdraw money
 * save state of your obejct to database. 
 */

@Table(name="account_details")
@Entity
public class Account {

	
	
	
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	@Column(name="account_id")
	private Long accountId;
	@Column(name="amount")
	private  BigDecimal amount;
	
	
	
	
	
	public Account() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	
	@ManyToOne(targetEntity=Customer.class)
	private Customer customer;
	
	@ManyToOne(targetEntity=Bank.class)
	private Bank bank;
	
	
	public Long getAccountId() {
		return accountId;
	}
	/**
	 * @param accountId the accountId to set
	 */
	public BigDecimal getAmount() {
		return amount;
	}
		/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	/**
	 * @param bank the bank to set
	 */
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 * @return the bankId
	 */
	
	

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", amount=" + amount + ", customer=" + customer + ", bank=" + bank
				+ "]";
	}
	
	
	
}
