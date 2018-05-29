package com.bank.service;

import com.bank.Exception.ManagedException;
import com.bank.dto.AccountTransaction;
import com.bank.model.Account;

public interface IAccountService {
	/**
	 * method name : createAccount return type : Account object parameter :object of
	 * Account class description : this method will return a account which is
	 * created
	 */
	Account createAccount(final Account account) throws ManagedException;

	/**
	 * method name : depositMoney return type : String parameter :object of
	 * AccountTransaction class description : this method will return a string if
	 * successfully amount is added and add to transaction list
	 */
	Long depositMoney(final AccountTransaction account) throws ManagedException;

	/**
	 * method name : withdrawlMoney return type : String object parameter :object of
	 * AccountTransaction class description : this method will return a string if
	 * successfully withdraw and add to transaction list
	 */
	Long withdrawlMoney(final AccountTransaction account) throws ManagedException;

	/**
	 * method name : getAccountDetails return type : Account object parameter :Long
	 * id description : this method will return a account which is created of given
	 * id
	 */
	Account getAccountDetails(final Long id) throws ManagedException;

}
