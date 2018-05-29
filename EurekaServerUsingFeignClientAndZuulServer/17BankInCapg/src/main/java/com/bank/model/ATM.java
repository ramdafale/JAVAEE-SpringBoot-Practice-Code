package com.bank.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "atm_info")
@Entity
public class ATM extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "atm_id")
	private Long aTMId;
	@Column(name = "amount")
	private BigDecimal amount;
	@Column(name = "id")
	private Long bankId;

	/**
	 * @return the aTMId
	 */
	public Long getATMId() {
		return aTMId;
	}

	/**
	 * @param aTMId
	 *            the aTMId to set
	 */
	public void setATMId(final Long aTMId) {
		this.aTMId = aTMId;
	}

	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(final BigDecimal amount) {
		this.amount = amount;
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
	public void setBankId(final Long bankId) {
		this.bankId = bankId;
	}

	public ATM() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "ATM [ATMId=" + aTMId + ", amount=" + amount + ", bankId=" + bankId + "]";
	}

	/**
	 * @param aTMId
	 * @param amount
	 * @param bankId
	 */
	public ATM(Long aTMId, BigDecimal amount, Long bankId) {
		aTMId = aTMId;
		this.amount = amount;
		this.bankId = bankId;
	}

}
