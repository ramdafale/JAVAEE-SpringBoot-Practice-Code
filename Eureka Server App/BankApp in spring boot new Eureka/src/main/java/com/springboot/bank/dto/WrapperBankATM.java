/**
 * 
 */
package com.springboot.bank.dto;

import com.springboot.bank.model.ATM;

/**
 * @author Ram
 *
 */
public class WrapperBankATM {

	ATM atm;
	Long bankId;

	/**
	 * 
	 */
	public WrapperBankATM() {
		super();
	}

	/**
	 * @param atm
	 * @param bankId
	 */
	public WrapperBankATM(ATM atm, Long bankId) {
		this.atm = atm;
		this.bankId = bankId;
	}

	/**
	 * @return the atm
	 */
	public ATM getAtm() {
		return atm;
	}

	/**
	 * @param atm
	 *            the atm to set
	 */
	public void setAtm(ATM atm) {
		this.atm = atm;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WrapperBankATM [atm=" + atm + ", bankId=" + bankId + "]";
	}

}
