/**
 * 
 */
package com.springboot.bank.dto;

import com.springboot.bank.model.Denomination;

/**
 * @author Ram
 *
 */
public class WrapperATMDenomination {


private	Denomination denomination;

private	Integer amount;
private Long bankId;
private Long atmId;


	/**
	 * @param denomination
	 * @param amount
	 * @param bankId
	 * @param atmId
	 */
	public WrapperATMDenomination(final Denomination denomination,final Integer amount,final Long bankId,final Long atmId) {
		this.denomination = denomination;
		this.amount = amount;
		this.bankId = bankId;
		this.atmId = atmId;
	}
	/**
	 * @return the bankDenomination
	 */
	public Denomination getBankDenomination() {
		return denomination;
	}
	/**
	 * @param denomination the bankDenomination to set
	 */
	public void setBankDenomination(final Denomination denomination) {
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
	public void setAmount(final Integer amount) {
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
	public void setBankId(final Long bankId) {
		this.bankId = bankId;
	}
	/**
	 * @return the atmId
	 */
	public Long getAtmId() {
		return atmId;
	}
	/**
	 * @param atmId the atmId to set
	 */
	public void setAtmId(final Long atmId) {
		this.atmId = atmId;
	}
	/* 
	 * java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WrapperATMDenomination [bankDenomination=" + denomination + ", amount=" + amount + ", bankId="
				+ bankId + ", atmId=" + atmId + "]";
	}
	
	
	
}
