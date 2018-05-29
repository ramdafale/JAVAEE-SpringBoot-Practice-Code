package com.bank.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.Exception.ManagedException;
import com.bank.model.Bank;
import com.bank.repository.BankRepository;

@Service("bankService")
public class BankServiceImpl implements IBankService {

	final Logger LOGGER = LoggerFactory.getLogger(BankServiceImpl.class);
	@Autowired
	private BankRepository bankRepository;
	@Autowired
	private IBankDenominationService bankDenoService;

	
	// create new bank instance
	@Override
	public Bank createBank(final Bank bank) throws ManagedException {
		final Bank bank1 = bankRepository.save(bank);
		if (bank1 != null) {
		
			return bank1;
		} else {
			LOGGER.error("No bank is added");
			throw new ManagedException("No Bank is Added");
		}
	}

	
	@Override
	public List<Bank> getBankDetails() throws ManagedException {
		final List<Bank> list = bankRepository.findAll();
		if (list.isEmpty()) {
			LOGGER.error("No banks found");
			throw new ManagedException("No bank found");
		} else {
			return list;
		}
	}

	@Override
	public Optional<Bank> getBankDetailsByID(final long ID) throws ManagedException {
		// changes made in repo
		final Optional<Bank> bank = bankRepository.findBybankId(ID);
		if (bank.isPresent()) {
			return bank;
		} else {
			LOGGER.error("no bank of such id exist");
			throw new ManagedException("No such bank found");
		}
	}

}
