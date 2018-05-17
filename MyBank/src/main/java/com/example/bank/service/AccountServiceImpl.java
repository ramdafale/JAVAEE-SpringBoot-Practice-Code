package com.example.bank.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private BankRepository bankrepo;
	@Autowired
	private CustomerRepository custRepo;

	/*
	 * @Autowired private CustomerRepository customerrepository;
	 */

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
		Account accountTrueobject = accountObject.get();
		accountTrueobject.setAmount(newvalue);
		bankTrueobject.setAmount(newvalue);

		bankrepo.save(bankTrueobject);
		custRepo.save(customertrueobject);
		accountRepository.save(accountTrueobject);
		
		TransactionOperation tr = new TransactionOperation(customerId, accountId, creditAmount, "deposit");
		itransact.save(tr);
		

		return "Amount added Succesfully";

	}

	@Override
	public String withdrawlMoney(AccountWithdrawDetails a) {

		Long bankId = a.getBankId();
		Optional<Bank> ob = bankrepo.findById(bankId);
		Bank bankTrueobject = ob.get();

		Long customerId = a.getCustomerId();
		Optional<Customer> customerob = custRepo.findById(customerId);
		Customer customertrueobject = customerob.get();

		Long accountId = a.getAccountId();
		BigDecimal creditAmount = a.getAmount();
		BigDecimal oldvalue = bankTrueobject.getAmount();

		BigDecimal newvalue = oldvalue.subtract(creditAmount);

		Optional<Account> accountObject = accountRepository.findById(accountId);
		Account accountTrueobject = accountObject.get();
		accountTrueobject.setAmount(newvalue);
		bankTrueobject.setAmount(newvalue);

		bankrepo.save(bankTrueobject);
		custRepo.save(customertrueobject);
		accountRepository.save(accountTrueobject);

		return "Amount added Succesfully";

	}

	@Override
	public Account getAccountDetails(Long id) {
		// TODO Auto-generated method stub
		Account account = accountRepository.findById(id).get();
		return account;
	}

}
