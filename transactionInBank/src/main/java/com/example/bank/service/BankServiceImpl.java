package com.example.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bank.model.Bank;
import com.example.bank.repository.BankRepository;

@Service("bankService")
public class BankServiceImpl implements IBankService{

	@Autowired
	private BankRepository bankRepository;
	@Override
	public Bank createBank(Bank bank) {
		// TODO Auto-generated method stub
		return bankRepository.save(bank);
	}
	@Override
	public List<Bank> getBankDetails() {
		// TODO Auto-generated method stub
		return bankRepository.findAll();
	}
	
	@Override
	public Optional<Bank> getBankDetailsByID(long ID) {
		// TODO Auto-generated method stub
		return bankRepository.findById(ID);
	}

}
