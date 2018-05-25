/**
 * 
 */
package com.springboot.bank.model;

import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author trainee
 *
 */
public class ATMDenomination  extends BaseEntity{

	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long denominationId;
	@OneToOne(targetEntity = ATM.class)
	private ATM atm;
	private BigDecimal noOfDenomination;
	private BigDecimal denomination;
	
	
	
	
	
	
	
}
