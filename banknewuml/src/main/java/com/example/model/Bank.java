/**
 * 
 */
package com.example.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Ram Dafale
 *
 */


@Entity
@Table(name="BANK")
public class Bank {

	
	
	public Bank( BigDecimal amount) {
		this.amount=amount;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bank_Id")
	private long bankId;
	@Column(name="amount")
	private BigDecimal amount;
	/**
	 * @return the bankId
	 */
/*	public long getBankId() {
		return bankId;
	}
	*//**
	 * @param bankId the bankId to set
	 *//*
	public void setBankId(long bankId) {
		this.bankId = bankId;
	}
	*//**
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
	
	
	public Bank()
	{}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Bank [bankId=" + bankId + ", amount=" + amount + "]";
	}
	
	
	
}
