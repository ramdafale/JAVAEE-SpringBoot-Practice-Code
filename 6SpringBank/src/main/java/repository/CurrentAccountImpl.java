/**
 * 
 */
package repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import model.CurrentAccountM;
import model.Customer;
import model.SavingAccountM;

/**
 * @author ram dafale This class create a current account and perform basic
 *         deposit and witrdraw operations.
 */
 class CurrentAccountImpl implements AccountDaoCurrrent {

	



	public CurrentAccountM Withdraw1(double amount) {

	  CurrentAccountM currAccount = new CurrentAccountM();

		double balance = currAccount.getBalance();

		balance = balance - amount;
		Date date = new Date();
		List list = new ArrayList();
		// list.add(date);
		list.add("Amount withdraw from current account  is" + amount);
		return currAccount;
	}

	public CurrentAccountM Deposit1(double amount) {

	  CurrentAccountM currAccount = new CurrentAccountM();
		double balance = currAccount.getBalance();

		balance = balance + amount;
		Date date = new Date();
		List list = new ArrayList();
		list.add(date);
		list.add("Amount Deposit from current account is" + amount);
		return currAccount;
	}


	
}