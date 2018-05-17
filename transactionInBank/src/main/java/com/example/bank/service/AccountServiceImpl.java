package com.example.bank.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bank.model.Account;
import com.example.bank.repository.AccountRepository;

/*
 * this class provide implemenataion of methods such as create account, deposit money, 
 * withdraw money. 
 */

@Service("accountService")
public class AccountServiceImpl implements IAccountService {
	@Autowired
	private AccountRepository accountRepository;

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
	public String depositMoney(BigDecimal amount) {

		return "Amount added Succesfully";

	}

	@Override
	public String withdrawlMoney(BigDecimal amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account getAccountDetails(Long id) {
		// TODO Auto-generated method stub
		Account account = accountRepository.findById(id).get();
		return account;
	}

}
