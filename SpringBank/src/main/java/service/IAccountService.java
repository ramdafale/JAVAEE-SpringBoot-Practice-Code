/**
 * 
 */
package service;

import java.util.List;

/**
 * @author trainee
 *
 */
public interface IAccountService {
  public List Withdraw(double amount);
  public List Deposit(double amount);
}
