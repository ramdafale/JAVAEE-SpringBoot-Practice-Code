/**
 * 
 */
package com.springboot.bank.model;

import java.math.BigDecimal;
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
public class Denomination extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long denominationId;
	@OneToOne(targetEntity = Bank.class)
	private Bank bank;
	/*@OneToOne(targetEntity = ATM.class)
	private ATM atm;*/
	private BigDecimal noOfDenomination;
	private BigDecimal denomination;

	
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
	public BigDecimal getNoOfDenomination() {
		return noOfDenomination;
	}

	/**
	 * @param noOfDenomination
	 *            the noOfDenomination to set
	 */
	public void setNoOfDenomination(BigDecimal noOfDenomination) {
		this.noOfDenomination = noOfDenomination;
	}



	/**
	 * @return the denomination
	 */
	public BigDecimal getDenomination() {
		return denomination;
	}



	/**
	 * @param denomination the denomination to set
	 */
	public void setDenomination(BigDecimal denomination) {
		this.denomination = denomination;
	}



	/**
	 * @param bank
	 * @param noOfDenomination
	 * @param denomination
	 */
	public Denomination(Long denominationId, Bank bank, BigDecimal noOfDenomination, BigDecimal denomination) {
		super();
		this.denominationId = denominationId;
		this.bank = bank;
		this.noOfDenomination = noOfDenomination;
		this.denomination = denomination;
	}

	

}