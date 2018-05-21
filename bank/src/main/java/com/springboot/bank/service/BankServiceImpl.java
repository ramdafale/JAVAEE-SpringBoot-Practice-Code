/**
 * 
 */
package com.springboot.bank.service;

import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.bank.exception.BankException;
import com.springboot.bank.model.Bank;
import com.springboot.bank.repository.BankDAO;

/**
 * @author Sumit
 * @Service("bankService")  
 * Description : It provide implementation for BankServices method. 
 *           	1.It Helps to create new bank
 *				2.getBankDetails
 */
@Service("bankService")
public class BankServiceImpl implements BankService {
	
	
	//Injecting properties of BankDAO
	@Autowired
	private BankDAO bankDao;

	/*
	 * BankService#createBank
	 * Desc: this method helps to create a new bank with default amount 0.
	 * 
	 */
	@Override
	public Bank createBank(Bank bank) throws BankException {
		Bank bankData = null;
		final BigDecimal amount = bank.getAmount();
		/*if (amount.compareTo(BigDecimal.ZERO) == -1)
			throw new BankException("amount cannot be zero or less than zero");
		else {*/
			bankData = bankDao.save(bank);
			return bankData;
	}

	/*
	 *BankService#getBankDetails
	 *Desc : It will helps to get information about details of bank passing a Id of it.
	 *Exception : it will throw exception if Id not found.
	 */
	@Override
	public Bank getBankDetails(Long bankId) throws BankException {
		Optional<Bank> bankList = bankDao.findById(bankId);
		if (bankList.isPresent()) {
			Bank bank = bankList.get();
			return bank;
		} else
			throw new BankException("Bank details not found");
	}
}
