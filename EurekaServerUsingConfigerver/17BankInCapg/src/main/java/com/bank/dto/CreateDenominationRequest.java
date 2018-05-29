package com.bank.dto;

import java.math.BigDecimal;
import java.util.List;

public class CreateDenominationRequest {
	
	private List<BigDecimal> list;
	private Long bankId;
	private Long atmId;
	/**
	 * @return the list
	 */
	public List<BigDecimal> getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List<BigDecimal> list) {
		this.list = list;
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

}
