/**
 * 
 */
package com.springboot.bank.service;

import java.math.BigDecimal;

import com.springboot.bank.dto.ATMDetails;
import com.springboot.bank.dto.WrapperBankATM;
import com.springboot.bank.exception.BankException;
import com.springboot.bank.model.ATM;

/**
 * @author Ram
 *
 */
public interface ATMService {

	ATM createATM(WrapperBankATM wrapperBankATM) throws BankException;

	ATM addMoneyFromBank(Long atmId, Long bankId, BigDecimal moneyToBeAddedToATM) throws BankException;

	ATM withdrawMoney(ATMDetails atmDetails) throws BankException;

}
