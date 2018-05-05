/**
 * 
 */
package service;

/**
 * @author trainee
 *
 */
public class WithdrawException extends Exception {

  
  
  
  public String message;
  public  String name;
  public  Double balance;
  
  
  public WithdrawException(String message, int i, Double balance)
  {
      this.message = message;
   //   this.name = i;
      this.balance = balance;
  }
  
  
  
  
  
}
