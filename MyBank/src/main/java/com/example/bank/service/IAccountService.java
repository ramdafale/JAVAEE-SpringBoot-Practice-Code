package com.example.bank.service;

import com.example.bank.model.Account;
import com.example.bank.request.AccountDetails;
import com.example.bank.request.AccountWithdrawDetails;

public interface IAccountService {
	public Account createAccount(Account account);
	public String depositMoney(AccountDetails amount);
	public String withdrawlMoney(AccountWithdrawDetails amount);
	public Account getAccountDetails(Long id);

}
