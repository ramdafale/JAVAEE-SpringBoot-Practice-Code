/**
 * 
 */
package com.springboot.bank.dto;

import java.math.BigDecimal;

import com.springboot.bank.model.Denomination;

/**
 * @author Ram
 *
 */
public class WrapperDenomination {

	private Denomination denomination;
	private BigDecimal amount;
	private Long bankId;
	private Long atmID;

	
	/**
	 * Default constructor
	 */
	public WrapperDenomination() {
		super();
	}


	/**
	 * @param denomination
	 * @param amount
	 * @param bankId
	 * @param atmID
	 */
	public WrapperDenomination(Denomination denomination, BigDecimal amount, Long bankId, Long atmID) {
		super();
		this.denomination = denomination;
		this.amount = amount;
		this.bankId = bankId;
		this.atmID = atmID;
	}


	/**
	 * @return the denomination
	 */
	public Denomination getDenomination() {
		return denomination;
	}


	/**
	 * @param denomination the denomination to set
	 */
	public void setDenomination(Denomination denomination) {
		this.denomination = denomination;
	}


	/**
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


	/**
	 * @return the bankId
	 */
	public Long getBankId() {
		return bankId;
	}


	/**
	 * @param bankId the bankId to set
	 */
	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}


	/**
	 * @return the atmID
	 */
	public Long getAtmID() {
		return atmID;
	}


	/**
	 * @param atmID the atmID to set
	 */
	public void setAtmID(Long atmID) {
		this.atmID = atmID;
	}


	
	
	
	

}
