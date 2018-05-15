/**
 * 
 */
package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.BankRepository;
import com.example.model.Bank;

/**
 * @author trainee
 *
 */
@Service
public class BankServiceImpl  implements BankService {

	
	
	@Autowired
	BankRepository bankrepository;
	
	@Override
	public Bank createBank(Bank bank) {
		// TODO Auto-generated method stub
		
		
		
		
		Bank returnBankCustomer = bankrepository.save(bank);
		
		return returnBankCustomer;
	}

	
	
}
