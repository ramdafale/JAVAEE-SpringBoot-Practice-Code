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
public interface IAccountService {
  public SavingAccountM Deposit(double amount);
  public SavingAccountM Withdraw(double amount) throws WithdrawException;
 
}
