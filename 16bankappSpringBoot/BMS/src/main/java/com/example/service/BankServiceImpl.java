/**
 * 
 */
package com.example.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.BankRepository;
import com.example.model.Bank;
import com.example.model.Customer;

/**
 * @author trainee
 *
 */


@Service
public class BankServiceImpl  implements IBankService {

	
	
	@Autowired
	private BankRepository bankrepository;
	
	
	@Override
	public int addBank(Bank bank) {
		
		bankrepository.save(bank);
		
		return 0;
	}

	@Override
	public Optional<Bank> getBankDetails(BigDecimal id) {

		 return this.bankrepository.findById(id);
		
		
	}

}
