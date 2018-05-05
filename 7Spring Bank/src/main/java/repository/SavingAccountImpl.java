/**
 * 
 */
package repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.SavingAccountM;

/**
 * @author trainee This class create a saving account and perform basic deposit
 *         and witrdraw operations.
 */
public class SavingAccountImpl implements IAccountDAO {

	public List Withdraw(double amount) {

		SavingAccountM savingAccount = new SavingAccountM();

		double balance = savingAccount.getBalance();

		balance = balance - amount;
	//	Date date = new Date();
		List list = new ArrayList();
	//	list.add(date);
		list.add(amount);
		System.out.println("Amount withdraw from saving account is" + amount);
		

		return list;
	}

	public List Deposit(double amount) {

		SavingAccountM savingAccount = new SavingAccountM();

		double balance = savingAccount.getBalance();

		balance = balance + amount;
		//Date date = new Date();
		List list = new ArrayList();
	//	list.add(date);
		list.add("Amount Deposit from saving account is" + amount);
		System.out.println("Amount Deposit from saving account is" + amount);
		return list;
	}

}
