package com.bank.service;

import java.math.BigDecimal;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.Exception.ManagedException;
import com.bank.dto.AccountTransaction;
import com.bank.model.Account;
import com.bank.model.Bank;
import com.bank.model.Customer;
import com.bank.model.Transaction;
import com.bank.repository.AccountRepository;
import com.bank.repository.BankRepository;
import com.bank.repository.CustomerRepository;
import com.bank.repository.TransactionRepository;

@Service("accountService")
public class AccountServiceImpl implements IAccountService {
	final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private ITransactionService trans;
	@Autowired
	private ICustomerService customerService;
	@Autowired
	private IBankService bankService;
	@Autowired
	private IBankDenominationService bankDenoService;
	private BigDecimal balance;


	// this will create account instance for customer
	@Override
	public Account createAccount(final Account account) throws ManagedException {
		final Account acc = accountRepository.save(account);
		if (acc != null) {
			LOGGER.info("account created");
			return acc;
		} else {
			LOGGER.error("no account is created");
			throw new ManagedException("No account is created");
		}
	}	
			//this will credit money to account
	@Transactional
	@Override
	public Long depositMoney(final AccountTransaction account) throws ManagedException {
		if (account.getAmount().compareTo(BigDecimal.ZERO) > 0) {
		final Optional<Account> acc1 = accountRepository.findByaccountId(account.getAccountId());
		if (acc1.isPresent()) {
			
			final Account acc = acc1.get();
			balance = acc.getAmount();
			balance = balance.add(account.getAmount());
			acc.setAmount(balance);
			final Optional<Bank> bank = bankService.getBankDetailsByID(account.getBankId());
			if (bank.isPresent()) {

				final Bank bank1 = bank.get();
				bank1.setAmount(balance);
				bankService.createBank(bank1);
				bankDenoService.bankDenominationDeposit(account.getAmount(),account.getBankId());
				
			}else
			{   LOGGER.error("no bank id present");
				throw new ManagedException("no bank is present of given id");
			}
			Transaction transaction = new Transaction();
			transaction.setAccount(acc);
			transaction.setAmount(acc.getAmount());
			final Customer customer = customerService.getCustomerDetails(account.getCustomerId());
			transaction.setCustomer(customer);
			transaction.setTransactionType("credit");
			trans.createTransaction(transaction);
			LOGGER.info("AMOUNT IS ADDED");
			return account.getCustomerId();
		} else {
			LOGGER.error("no amount is deposited");
			throw new ManagedException("zero amount  deposited");
		}
		}
		else
		{
			LOGGER.error("-ve amount is not possible");
			throw new ManagedException("negative amount is not possible");
		}
	}

	//this will debit money from account
	@Transactional
	@Override
	public Long withdrawlMoney(final AccountTransaction account) throws ManagedException {
		// TODO Auto-generated method stub
		if (account.getAmount().compareTo(BigDecimal.ZERO) > 0) {
		Optional<Account> acc1 = accountRepository.findByaccountId(account.getAccountId());
		if (acc1.isPresent()) {
			final Account account1 = acc1.get();                                                                     ;
			balance = account1.getAmount();
			balance = balance.subtract(account.getAmount());
			account1.setAmount(balance);
			final Optional<Bank> bank = bankService.getBankDetailsByID(account.getBankId());
			if (bank.isPresent()) {
				bankDenoService.bankDenominationWithdraw(account.getAmount(), account.getBankId());
				final Bank bank1 = bank.get();
				bank1.setAmount(balance);
				bankService.createBank(bank1);

				Transaction transaction=new Transaction();
				transaction.setAccount(account1);
				transaction.setAmount(account1.getAmount());
				final Customer customer = customerService.getCustomerDetails(account.getCustomerId());
				if(customer!=null)
				{
				transaction.setCustomer(customer);
				transaction.setTransactionType("debit");
				trans.createTransaction(transaction);
				LOGGER.info("amount is deducted");
				return account.getCustomerId();
				}
				else
				{ LOGGER.error("no customer exist");
					throw new ManagedException("No customer of such occunct exsit");
				}
			}else
			{LOGGER.error("no account exist");
				throw new ManagedException("No such account exist");
			}
			
		} else {
			LOGGER.error("not  enough amount to withdraw");
			throw new ManagedException("No amount withdraw");
		}
		}
		else
		{
			LOGGER.error("-ve value is not posssible");
			throw new ManagedException("negative amount is not possible");
		}
	}

	
	//It will give account details of perticular Id
	@Override
	public Account getAccountDetails(final Long id) throws ManagedException {
		// TODO Auto-generated method stub
		Account account =  accountRepository.findByaccountId(id).get();
		if (account != null) {
			LOGGER.info("account retrived");
			return account;
		} else {
			LOGGER.error("no such id exist");
			throw new ManagedException("no such element found");
		}
	}

}
