/**
 * 
 */
package com.example.bank.service;

import java.math.BigDecimal;

import com.example.bank.exception.ManagedException;
import com.example.bank.model.ATM;
import com.example.bank.request.DetailsBankATM;

/**
 * @author trainee
 *
 */
public interface IATMService {

	ATM createATM(DetailsBankATM wrapperBankATM) throws ManagedException;

	ATM addMoneyFromBank(Long atmId, Long bankId, BigDecimal moneyToBeAddedToATM) throws ManagedException;
	
	
	
}
