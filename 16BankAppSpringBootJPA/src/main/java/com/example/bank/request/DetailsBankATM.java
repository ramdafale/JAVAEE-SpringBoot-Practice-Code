/**
 * 
 */
package com.example.bank.request;

import com.example.bank.model.ATM;

/**
 * @author trainee
 *
 */
public class DetailsBankATM {

	
	
	ATM atm;
	Long bankId;

	/**
	 * 
	 */
	public DetailsBankATM() {
		super();
	}

	/**
	 * @param atm
	 * @param bankId
	 */
	public DetailsBankATM(ATM atm, Long bankId) {
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
