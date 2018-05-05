/**
 * 
 */
package service;

import model.Customer;
import model.SavingAccountM;

/**
 * @author trainee
 *
 */
public interface IAccountService {
  SavingAccountM Deposit(double amount);
   SavingAccountM Withdraw(double amount) throws WithdrawException;
  void UpdateDetails(Customer customer, String checkAccountNumber, int newChange) ;
}
