/**
 * 
 */
package repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import HelloSpring2.HelloSpring2.service.Account;
import model.Customer;
import model.SavingAccountM;

/**
 * @author ram dafale This class create a current account and perform basic
 *         deposit and witrdraw operations.
 */
class CurrentAccountImpl implements IAccountDAO {

	



	public List Withdraw(double amount) {

		SavingAccountM savingAccount = new SavingAccountM();

		double balance = savingAccount.getBalance();

		balance = balance - amount;
		Date date = new Date();
		List list = new ArrayList();
		list.add(date);
		list.add("Amount withdraw from current account  is" + amount);
		return list;
	}

	public List Deposit(double amount) {

		SavingAccountM savingAccount = new SavingAccountM();

		double balance = savingAccount.getBalance();

		balance = balance + amount;
		Date date = new Date();
		List list = new ArrayList();
		list.add(date);
		list.add("Amount Deposit from current account is" + amount);
		return list;
	}
	
}