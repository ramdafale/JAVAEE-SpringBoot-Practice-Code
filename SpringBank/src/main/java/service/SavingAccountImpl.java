/**
 * 
 */
package service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.SavingAccountM;


/**
 * @author trainee This class create a saving account and perform basic deposit and withdraw
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
    
    
    final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    final LocalDateTime now = LocalDateTime.now();  
    System.out.println("Deposit made at Time "+dtf.format(now)); 
    
    //System.out.println("Deposit amde at Time : " date.format(now));

    return savingAccount;
  }

  public SavingAccountM Withdraw(double amount) {

    SavingAccountM savingAccount = new SavingAccountM();

    double balance = savingAccount.getBalance();
    System.out.println("Current Balance" + savingAccount.getBalance());

    balance = balance - amount;
 
    System.out.println("Amount withdraw from saving account is" + amount);
    System.out.println("Current Balance after withdrawl is : " + balance);
    final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    final LocalDateTime now = LocalDateTime.now();  
    System.out.println("Withdraw is made at Time "+dtf.format(now)); 
    return savingAccount;
  }

}
