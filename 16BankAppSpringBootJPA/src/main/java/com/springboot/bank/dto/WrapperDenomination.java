/**
 * 
 */
package com.springboot.bank.dto;

import com.springboot.bank.model.Denomination;

/**
 * @author Ram
 *
 */
public class WrapperDenomination {

	private Denomination denomination;
	private Integer amount;
	private Long bankId;

	
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
	 */
	public WrapperDenomination(Denomination denomination, Integer amount, Long bankId) {
		super();
		this.denomination = denomination;
		this.amount = amount;
		this.bankId = bankId;
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
	public Integer getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Integer amount) {
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WrapperDenomination [denomination=" + denomination + ", amount=" + amount + ", bankId=" + bankId + "]";
	}
	
	
	
	
	

}
