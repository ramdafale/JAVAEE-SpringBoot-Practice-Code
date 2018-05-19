package com.example.bank.model;


/**
 * 
 */


import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author Ram
 *
 */
@Entity
@Table(name = "atm")
public class ATM {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long atmId;
	// @Required
	private BigDecimal money;
	@ManyToOne(targetEntity = Bank.class)
	private Bank bank;

	/**
	 * 
	 */
	public ATM() {
		super();
	}

	/**
	 * @param atmId
	 * @param money
	 * @param bank
	 */
	public ATM(Long atmId, BigDecimal money, Bank bank) {
		super();
		this.atmId = atmId;
		this.money = money;
		this.bank = bank;
	}

	/**
	 * @return the atmId
	 */
	public Long getAtmId() {
		return atmId;
	}

	/**
	 * @param atmId
	 *            the atmId to set
	 */
	public void setAtmId(Long atmId) {
		this.atmId = atmId;
	}

	/**
	 * @return the money
	 */
	public BigDecimal getMoney() {
		return money;
	}

	/**
	 * @param money
	 *            the money to set
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	/**
	 * @param bank
	 *            the bank to set
	 */
	public void setBank(Bank bank) {
		this.bank = bank;
	}

	
	public Bank getBank()
	{
		return bank;
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ATM [atmId=" + atmId + ", money=" + money + ", bank=" + bank + "]";
	}
}
