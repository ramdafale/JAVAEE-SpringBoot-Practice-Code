/**
 * 
 */
package com.example.service;

import java.math.BigDecimal;

import com.example.model.Account;

/**
 * @author trainee
 *
 */
public interface IAccountService {

	
	String createAccount(Account account);
	String depositMoney(BigDecimal amount);
	String withdrawMoney(BigDecimal amount);
	String getAccountDetails(Account account);
	
	
}
