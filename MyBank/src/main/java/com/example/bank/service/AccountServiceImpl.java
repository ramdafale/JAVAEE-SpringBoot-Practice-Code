package com.example.bank.service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bank.exception.ManagedException;
import com.example.bank.model.Account;
import com.example.bank.model.Bank;
import com.example.bank.model.Customer;
import com.example.bank.model.TransactionOperation;
import com.example.bank.repository.AccountRepository;
import com.example.bank.repository.BankRepository;
import com.example.bank.repository.CustomerRepository;
import com.example.bank.request.AccountDetails;
import com.example.bank.request.AccountWithdrawDetails;

/*
 * this class provide implemenataion of methods such as create account, deposit money, 
 * withdraw money. 
 */

@Service("accountService")
public class AccountServiceImpl implements IAccountService {

	static Logger logger = Logger.getLogger(AccountServiceImpl.class.getName());

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private BankRepository bankrepo;

	/*
	 * @Autowired private CustomerRepository customerrepository;
	 */
	@Autowired
	private CustomerRepository custRepo;

	@Autowired
	private TransactionServiceImpl transactionservice;

	/*
	 * @see
	 * com.example.bank.service.IAccountService#createAccount(com.example.bank.model
	 * .Account) this will create a account in your bank
	 */

	@Override
	public Account createAccount(Account account) {

		return accountRepository.save(account);
	}

	@Override
	public String depositMoney(AccountDetails a) {

		Long bankId = a.getBankId();
		Optional<Bank> ob = bankrepo.findById(bankId);
		Bank bankTrueobject = ob.get();

		Long customerId = a.getCustomerId();
		Optional<Customer> customerob = custRepo.findById(customerId);
		Customer customertrueobject = customerob.get();

		Long accountId = a.getAccountId();
		BigDecimal creditAmount = a.getAmount();
		BigDecimal oldvalue = bankTrueobject.getAmount();

		BigDecimal newvalue = oldvalue.add(creditAmount);

		Optional<Account> accountObject = accountRepository.findById(accountId);
		Account accountobject = accountObject.get();
		accountobject.setAmount(newvalue);
		bankTrueobject.setAmount(newvalue);

		bankrepo.save(bankTrueobject);
		custRepo.save(customertrueobject);
		accountRepository.save(accountobject);

		TransactionOperation transaction = new TransactionOperation(customertrueobject, accountobject, newvalue,
				"Deposite");
		try {
			transactionservice.createTransaction(transaction);
		} catch (ManagedException e) {
			// TODO Auto-generated catch block
			logger.info("Transaction not created for deposit .. plz check ");
			e.printStackTrace();

		}
		bankTrueobject.setAmount(newvalue);
		bankrepo.save(bankTrueobject);
		logger.info("Transaction created ");
		return "Amount added Succesfully";
	}

	@Override
	public String withdrawlMoney(final AccountWithdrawDetails account) {

		Long bankId = account.getBankId();
		Optional<Bank> ob = bankrepo.findById(bankId);
		Bank bankTrueobject = ob.get();

		Long customerId = account.getCustomerId();
		Optional<Customer> customerob = custRepo.findById(customerId);
		Customer customertrueobject = customerob.get();

		Long accountId = account.getAccountId();
		BigDecimal creditAmount = account.getAmount();
		BigDecimal oldvalue = bankTrueobject.getAmount();

		BigDecimal newvalue = oldvalue.subtract(creditAmount);

		Optional<Account> accountObject = accountRepository.findById(accountId);
		Account accountTrueobject = accountObject.get();
		accountTrueobject.setAmount(newvalue);
		bankTrueobject.setAmount(newvalue);

		bankrepo.save(bankTrueobject);
		custRepo.save(customertrueobject);
		accountRepository.save(accountTrueobject);

		TransactionOperation transaction = new TransactionOperation(customertrueobject, accountTrueobject, newvalue,
				"Withdraw");
		try {
			transactionservice.createTransaction(transaction);
		} catch (ManagedException e) {
			logger.info("Transaction not created for withdraw.. plz check ");
			e.printStackTrace();
		}
		bankTrueobject.setAmount(newvalue);
		bankrepo.save(bankTrueobject);
		logger.info("Transaction  created succesfully ");
		return "Amount added Succesfully";

	}

	@Override
	public Account getAccountDetails(Long id) {

		Account account = accountRepository.findById(id).get();
		logger.info("Account Details are>>>>>>>>>>>>>:");
		return account;
	}

}
