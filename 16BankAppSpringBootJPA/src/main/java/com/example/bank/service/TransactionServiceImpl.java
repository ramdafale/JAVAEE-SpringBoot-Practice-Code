package com.example.bank.service;

import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bank.exception.ManagedException;
import com.example.bank.model.Account;
import com.example.bank.model.Customer;
import com.example.bank.model.TransactionOperation;
import com.example.bank.repository.CustomerRepository;
import com.example.bank.repository.TransactionRepository;

/**
 * @author ram
 *
 */
@Service("transactionService")
public class TransactionServiceImpl implements ITransactionService {

	
	
	 static Logger logger = Logger.getLogger(TransactionServiceImpl.class.getName());
	
	
	@Autowired
	TransactionRepository transactionDao;

	@Autowired
	CustomerRepository customerdao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.springboot.bank.service.TransactionService#createTransaction(com.
	 * springboot.bank.wrapper.TransactionDetails)
	 */
	@Override
	public String createTransaction(TransactionOperation transaction) throws ManagedException {

		Customer customer = transaction.getCustomer();
		Long customerId = customer.getCustomerId();
		Account account = transaction.getAccount();
		Long accountId = account.getAccountId();

		if (accountId == 0) {
			throw new ManagedException("accountId cannot be 0");
		} else if (customerId == 0) {
			throw new ManagedException("customerId cannot be 0");
		} else {
			transactionDao.save(transaction);
			logger.info("transaction created from Service");
			return "Transaction details added successfully";
		}
	}

	@Override
	public Optional<TransactionOperation> getTransactionDetails(Long customerId) throws ManagedException {

		if (customerId == 0) {
			throw new ManagedException("customerId cannot be 0");
		} else {
			Optional<TransactionOperation> transactionList = transactionDao.findById(customerId);
			logger.info("transaction created from Service");
			return transactionList;
		}
	}
}
