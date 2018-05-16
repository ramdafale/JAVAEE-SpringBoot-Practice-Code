/**
 * 
 */
package com.example.service;

import java.math.BigDecimal;

import com.example.model.Bank;

/**
 * @author trainee
 *
 */
public interface IATMService {

	
	
	
	String createAtm(Bank bank);
	String addMoneyFromBank(BigDecimal amount);
	String withdrawMoney(BigDecimal amount);
	
	
	
}
