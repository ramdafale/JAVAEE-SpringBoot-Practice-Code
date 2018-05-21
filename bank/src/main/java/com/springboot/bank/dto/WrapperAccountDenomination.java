/**
 * 
 */
package com.springboot.bank.dto;

import com.springboot.bank.model.BankDenomination;

/**
 * @author Ram
 *
 */
public class WrapperAccountDenomination {

	
	private	BankDenomination bankDenomination;
	private	Integer amount;
	private Long bankId;
	private Long accountId;
	/**
	 * @param bankDenomination
	 * @param amount
	 * @param bankId
	 * @param accountId
	 */
	public WrapperAccountDenomination(BankDenomination bankDenomination, Integer amount, Long bankId, Long accountId) {
		this.bankDenomination = bankDenomination;
		this.amount = amount;
		this.bankId = bankId;
		this.accountId = accountId;
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
	 * @return the accountId
	 */
	public Long getAccountId() {
		return accountId;
	}
	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WrapperAccountDenomination [bankDenomination=" + bankDenomination + ", amount=" + amount + ", bankId="
				+ bankId + ", accountId=" + accountId + "]";
	}
	
	
	
}
