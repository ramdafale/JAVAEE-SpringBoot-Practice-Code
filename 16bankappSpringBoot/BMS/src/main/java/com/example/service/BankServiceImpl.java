/**
 * 
 */
package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.dao.BankRepository;
import com.example.model.Bank;
import com.example.model.Customer;

/**
 * @author trainee
 *
 */



public class BankServiceImpl  implements IBankService {

	
	
	@Autowired
	private BankRepository bankrepository;
	
	
	@Override
	public int addBank(Bank bank) {
		
		bankrepository.save(bank);
		
		return 0;
	}

	@Override
	public Object getBankDetails(Bank bank) {

		Optional<Bank> ob = bankrepository.findById(bank.getBankId());
		Bank obj = ob.get();
		
		return obj;
	}

}
