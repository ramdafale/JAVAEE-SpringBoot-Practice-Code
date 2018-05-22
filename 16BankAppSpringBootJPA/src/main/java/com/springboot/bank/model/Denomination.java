/**
 * 
 */
package com.springboot.bank.model;

import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author Ram
 *
 */

@Entity
public class Denomination {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long denominationId;
	@OneToOne(targetEntity = Bank.class)
	private Bank bank;
	private Integer noOfDenomination;
	private Integer denomination;

	
	private static final Logger LOGGER = Logger.getLogger( Denomination.class.getName() );
	/**
	 * 
	 */
	public Denomination() {
		
		super();
		LOGGER.info("Inside the BankDenomination Entity");
	}

	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BankDenominationNew [denominationId=" + denominationId + ", bank=" + bank + ", noOfDenomination="
				+ noOfDenomination + ", denomination=" + denomination + "]";
	}



	/**
	 * @return the denominationId
	 */
	public Long getDenominationId() {
		return denominationId;
	}

	/**
	 * @param denominationId
	 *            the denominationId to set
	 */
	public void setDenominationId(Long denominationId) {
		this.denominationId = denominationId;
	}

	/**
	 * @return the bank
	 */
	public Bank getBank() {
		return bank;
	}

	/**
	 * @param bank
	 *            the bank to set
	 */
	public void setBank(Bank bank) {
		this.bank = bank;
	}

	
	/**
	 * @return the noOfDenomination
	 */
	public Integer getNoOfDenomination() {
		return noOfDenomination;
	}

	/**
	 * @param noOfDenomination
	 *            the noOfDenomination to set
	 */
	public void setNoOfDenomination(Integer noOfDenomination) {
		this.noOfDenomination = noOfDenomination;
	}



	/**
	 * @return the denomination
	 */
	public Integer getDenomination() {
		return denomination;
	}



	/**
	 * @param denomination the denomination to set
	 */
	public void setDenomination(Integer denomination) {
		this.denomination = denomination;
	}



	/**
	 * @param denominationId
	 * @param bank
	 * @param noOfDenomination
	 * @param denomination
	 */
	public Denomination(Long denominationId, Bank bank, Integer noOfDenomination, Integer denomination) {
		super();
		this.denominationId = denominationId;
		this.bank = bank;
		this.noOfDenomination = noOfDenomination;
		this.denomination = denomination;
	}

	

}