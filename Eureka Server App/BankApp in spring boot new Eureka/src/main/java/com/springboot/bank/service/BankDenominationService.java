/**
 * 
 */
package com.springboot.bank.service;

import java.math.BigDecimal;

import com.springboot.bank.dto.WrapperDenomination;
import com.springboot.bank.exception.BankException;
import com.springboot.bank.model.Denomination;

/**
 * @author Ram
 *
 */
public interface BankDenominationService {



	void  addDenominationNew(BigDecimal amount,Long Id) throws BankException;
	


}
