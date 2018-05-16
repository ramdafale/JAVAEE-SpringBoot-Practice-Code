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

/**
 * @author trainee
 *
 */

@Service
public class BankServiceImpl implements IBankService {

	@Autowired
	private BankRepository bankrepository;

	@Override
	public int addBank(Bank bank) {

		bankrepository.save(bank);

		return 0;
	}

	@Override
	public Bank getBankDetails(BigDecimal id) {

		Optional<Bank> bank1 = bankrepository.findById(id);
		System.out.println(">>>>>>>>>" + bank1);
		 Bank ba=bankrepository.getOne(id);
		return ba;

	}
	
	public Optional<Bank> getBankDetailById(BigDecimal id) 
	{
		Optional<Bank> bank = bankrepository.findById(id);
		
		return bank;
		
		
	}
	
	
	
	

}
