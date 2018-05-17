package com.example.bank.service;

import java.math.BigDecimal;

import com.example.bank.model.Account;

public interface IAccountService {
	public Account createAccount(Account account);
	public String depositMoney(BigDecimal amount);
	public String withdrawlMoney(BigDecimal amount);
	public Account getAccountDetails(Long id);

}
