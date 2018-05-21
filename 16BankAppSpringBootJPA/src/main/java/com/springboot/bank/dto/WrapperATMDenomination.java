/**
 * 
 */
package com.springboot.bank.dto;

import com.springboot.bank.model.BankDenomination;

/**
 * @author Ram
 *
 */
public class WrapperATMDenomination {

	
private	BankDenomination bankDenomination;
private	Integer amount;
private Long bankId;
private Long atmId;
	/**
	 * @param bankDenomination
	 * @param amount
	 * @param bankId
	 * @param atmId
	 */
	public WrapperATMDenomination(BankDenomination bankDenomination, Integer amount, Long bankId, Long atmId) {
		this.bankDenomination = bankDenomination;
		this.amount = amount;
		this.bankId = bankId;
		this.atmId = atmId;
	}
	/**
	 * @return the bankDenomination
	 */
	public BankDenomination getBankDenomination() {
		return bankDenomination;
	}
	/**
	 * @param bankDenomination the bankDenomination to set
	 */
	public void setBankDenomination(BankDenomination bankDenomination) {
		this.bankDenomination = bankDenomination;
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
	/**
	 * @return the atmId
	 */
	public Long getAtmId() {
		return atmId;
	}
	/**
	 * @param atmId the atmId to set
	 */
	public void setAtmId(Long atmId) {
		this.atmId = atmId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WrapperATMDenomination [bankDenomination=" + bankDenomination + ", amount=" + amount + ", bankId="
				+ bankId + ", atmId=" + atmId + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((atmId == null) ? 0 : atmId.hashCode());
		result = prime * result + ((bankDenomination == null) ? 0 : bankDenomination.hashCode());
		result = prime * result + ((bankId == null) ? 0 : bankId.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WrapperATMDenomination other = (WrapperATMDenomination) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (atmId == null) {
			if (other.atmId != null)
				return false;
		} else if (!atmId.equals(other.atmId))
			return false;
		if (bankDenomination == null) {
			if (other.bankDenomination != null)
				return false;
		} else if (!bankDenomination.equals(other.bankDenomination))
			return false;
		if (bankId == null) {
			if (other.bankId != null)
				return false;
		} else if (!bankId.equals(other.bankId))
			return false;
		return true;
	}
	
	
	
	
	
}
