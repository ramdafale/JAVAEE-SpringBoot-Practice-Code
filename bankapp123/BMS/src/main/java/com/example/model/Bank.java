/**
 * 
 */
package com.example.model;

import java.math.BigDecimal;

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
@Table(name = "BANK")
public class Bank {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private BigDecimal Id;

	
	private String bankName;

	private BigDecimal amount;

	public Bank() {

	}

	/*public Bank(BigDecimal amount, String bankName) {
		this.amount = amount;
		this.bankName = bankName;
	}*/

	/*@OneToMany(targetEntity=Customer.class,mappedBy="bankId")
	private List<Customer> bankCustomerList = new ArrayList<>();*/
	
	
	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @return the bankId
	 */
	public BigDecimal getBankId() {
		return Id;
	}

	/**
	 * @param bankId
	 *            the bankId to set
	 */
	public void setBankId(BigDecimal bankId) {
		this.Id = bankId;
	}

	
	/**
	 * @param bankName
	 *            the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Bank [bankId=" + Id + ", amount=" + amount + "]";
	}

}
