package com.bank.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "Bank_Denm")
@Entity
public class BankDenomination extends BaseEntity {

	@Id
	@Column(name = "denomination")
	private BigDecimal denomination;
	@Column(name = "noOfDenomination")
	private int noOfDenomination;
	@Column(name = "bank_id")
	private Long bankId;



	/**
	 * @return the bankId
	 */
	public BigDecimal getDenomination() {
		return denomination;
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
	 * @param denomination
	 *            the denomination to set
	 */
	public void setDenomination(BigDecimal denomination) {
		this.denomination = denomination;
	}

	/**
	 * @return the noOfDenomination
	 */
	public int getNoOfDenomination() {
		return noOfDenomination;
	}

	/**
	 * @param noOfDenomination
	 *            the noOfDenomination to set
	 */
	public void setNoOfDenomination(int noOfDenomination) {
		this.noOfDenomination = noOfDenomination;
	}

	public BankDenomination() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param bankId
	 * @param denomination
	 * @param noOfDenomination
	 */
	public BankDenomination(BigDecimal denomination, int noOfDenomination) {

		this.denomination = denomination;
		this.noOfDenomination = noOfDenomination;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

	@Override
	public String toString() {
		return "BankDenomination [denomination=" + denomination + ", noOfDenomination=" + noOfDenomination + ", bankId="
				+ bankId + "]";
	}

	/**
	 * @param denomination
	 * @param noOfDenomination
	 * @param bankId
	 */
	public BankDenomination(BigDecimal denomination, int noOfDenomination, Long bankId) {
		super();
		this.denomination = denomination;
		this.noOfDenomination = noOfDenomination;
		this.bankId = bankId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((denomination == null) ? 0 : denomination.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		BankDenomination other = (BankDenomination) obj;
		if (denomination == null) {
			if (other.denomination != null)
				return false;
		} else if (!denomination.equals(other.denomination))
			return false;
		return true;
	}

}