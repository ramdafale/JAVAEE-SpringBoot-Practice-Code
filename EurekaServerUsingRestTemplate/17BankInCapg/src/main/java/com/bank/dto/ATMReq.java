package com.bank.dto;

import com.bank.model.ATM;

public class ATMReq {
	private Long bankId;
	private ATM atm;
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
	 * @return the atm
	 */
	public ATM getAtm() {
		return atm;
	}
	/**
	 * @param atm the atm to set
	 */
	public void setAtm(ATM atm) {
		this.atm = atm;
	}
public ATMReq() {
	// TODO Auto-generated constructor stub
}
/**
 * @param bankId
 * @param atm
 */
public ATMReq(Long bankId, ATM atm) {
	this.bankId = bankId;
	this.atm = atm;
}

}
