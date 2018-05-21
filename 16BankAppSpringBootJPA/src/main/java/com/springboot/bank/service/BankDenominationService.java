/**
 * 
 */
package com.springboot.bank.service;

import com.springboot.bank.dto.WrapperBankDenomination;
import com.springboot.bank.exception.BankException;
import com.springboot.bank.model.BankDenomination;

/**
 * @author Sumit
 *
 */
public interface BankDenominationService {



	void  addDenominationNew(Integer amount) throws BankException;

}
