/**
 * 
 */
package service;

import java.util.List;

import model.SavingAccountM;

/**
 * @author trainee
 *
 */
public interface IAccountDAO {

  public SavingAccountM Deposit(double amount);

  public SavingAccountM Withdraw(double amount);

  // public List<SavingAccount> getStatment(List list);

}
