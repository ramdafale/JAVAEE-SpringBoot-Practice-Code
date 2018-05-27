
/**
 * 
 */
package com.springboot.bank.service;

import com.springboot.bank.exception.BankException;
import com.springboot.bank.model.Bank;

/**
 * @author Ram
 * @nterface   BankService 
 * Description : It has 2 methods declaration in it. whichever class will implements,
 * will provide implemenatation for this methods.
 *
 */
public interface BankService {

	Bank createBank(Bank bank) throws BankException;

	Bank getBankDetails(Long bankId) throws BankException;
}