/**
 * 
 */
package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author trainee
 *
 */
public class Statements {

  
  final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
  final LocalDateTime now = LocalDateTime.now(); 
  
  SavingAccountM listSaving = new SavingAccountM();
  
      public void statement()
  {
  System.out.println("Balance in account number " + listSaving.getAccountNumber() + " is "+listSaving.balance +"" + dtf.format(now) );
  }

  
}
 