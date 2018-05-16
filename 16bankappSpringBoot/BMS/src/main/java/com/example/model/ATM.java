/**
 * 
 *//*
package com.example.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

*//**
 * @author trainee
 *
 *//*
@Entity
@Table(name = "ATM")
public class ATM {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigDecimal amtId;
	
	@Column(name = "amount")
	private BigDecimal amount;
	
	@Column(name = "bank_Id")
	private BigDecimal bankId;

	*//**
	 * @param amtId
	 * @param amount
	 * @param bankId
	 *//*
	public ATM(BigDecimal amtId, BigDecimal amount, BigDecimal bankId) {
		super();
		this.amtId = amtId;
		this.amount = amount;
		this.bankId = bankId;
	}

	*//**
	 * @return the amtId
	 *//*
	public BigDecimal getAmtId() {
		return amtId;
	}

	*//**
	 * @param amtId the amtId to set
	 *//*
	public void setAmtId(BigDecimal amtId) {
		this.amtId = amtId;
	}

	*//**
	 * @return the amount
	 *//*
	public BigDecimal getAmount() {
		return amount;
	}

	*//**
	 * @param amount the amount to set
	 *//*
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	*//**
	 * @return the bankId
	 *//*
	public BigDecimal getBankId() {
		return bankId;
	}

	*//**
	 * @param bankId the bankId to set
	 *//*
	public void setBankId(BigDecimal bankId) {
		this.bankId = bankId;
	}

	 (non-Javadoc)
	 * @see java.lang.Object#toString()
	 
	@Override
	public String toString() {
		return "ATM [amtId=" + amtId + ", amount=" + amount + ", bankId=" + bankId + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
*/