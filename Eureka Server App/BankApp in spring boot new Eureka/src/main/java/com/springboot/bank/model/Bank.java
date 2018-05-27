/**
 * 
 */
package com.springboot.bank.model;

import java.math.BigDecimal;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Ram
 *It will represents the BANK entity
 */
@Table(name = "bank")
@Entity
public class Bank extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bankId")
	private Long bankId;
	private BigDecimal amount;
	
	private static final Logger LOGGER = Logger.getLogger( Bank.class.getName() );
	

	/**
	 * 
	 */
	public Bank() {
		super();
	}

	/**
	 * @param bankId
	 * @param amount
	 */
	public Bank(BigDecimal amount) {
		this.amount = amount;
		LOGGER.info("Inside the BAnk Entity");
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

	/**
	 * @return the amount
	 */
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
	 * @return toString() representation of given object 
	 */
	@Override
	public String toString() {
		return "Bank [bankId=" + bankId + ", amount=" + amount + "]";
	}
}