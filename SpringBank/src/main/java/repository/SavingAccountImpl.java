/**
 * 
 */
package repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.SavingAccountM;

/**
 * @author trainee This class create a saving account and perform basic deposit and witrdraw
 *         operations.
 */
public class SavingAccountImpl implements IAccountDAO {

  public SavingAccountM Deposit(double amount) {

    SavingAccountM savingAccount = new SavingAccountM();

    double balance = savingAccount.getBalance();
    System.out.println("Current Balance is :" + savingAccount.getBalance());
    balance = balance + amount;
    // Date date = new Date();
    List list = new ArrayList();
    // list.add(date);
    // list.add("Amount Deposit from saving account is" + amount);
    System.out.println("Amount Deposit in saving account is" + amount);
    System.out.println("Current Balance after deposit is : " + balance);

    return savingAccount;
  }

  public SavingAccountM Withdraw(double amount) {

    SavingAccountM savingAccount = new SavingAccountM();

    double balance = savingAccount.getBalance();
    System.out.println("Current Balance" + savingAccount.getBalance());

    balance = balance - amount;
    // Date date = new Date();
    // List list = new ArrayList();
    // list.add(date);
    // list.add(amount);
    System.out.println("Amount withdraw from saving account is" + amount);
    System.out.println("Current Balance after withdrawl is : " + balance);

    return savingAccount;
  }

}
