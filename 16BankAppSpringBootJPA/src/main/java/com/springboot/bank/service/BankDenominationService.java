/**
 * 
 */
package com.springboot.bank.service;

import com.springboot.bank.dto.WrapperDenomination;
import com.springboot.bank.exception.BankException;
import com.springboot.bank.model.Denomination;

/**
 * @author Ram
 *
 */
public interface BankDenominationService {



	void  addDenominationNew(Integer amount) throws BankException;

}
