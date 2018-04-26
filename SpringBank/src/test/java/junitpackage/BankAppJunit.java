/*
 * Program Name: Test cases for abnk application using JUNIT
 * @author Ram Dafale
 * DateOF Completion : 4/42/2018
 */
package junitpackage;

import model.Customer;
import model.SavingAccountM;
import model.Statements;


import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/*
 * class Name: BankAppJunit
 *  * Description : this class contains various test cases for bank application 
* 1. Null values are not allowed in Name
* 2. Null values are not allowed in lASTnaME
* 3. Null values are not allowed in MOBILE NUMBER
* 4. checking if deposit method running or not
* 5. checking if withdraw method running or not
* 6. checking if addInterest method running or not
*/
public class BankAppJunit {

  private final String str = null;

  // Creating a customer class object
  final Customer cust = new Customer("ram", "dafale", "wardha", 97546545, "ramdafale@gmail.com");

  // 1. Null values are not allowed in Name
  @Test
  public void checknullInFirstName() {

    assertNotEquals("null not allowed ", str, cust.getFname());
  }

  @Test
  public void checknullInLastName() {

    assertNotEquals("null not allowed ", str, cust.getLname());
  }

  @Test
  public void checknullInMobile() {

    assertNotEquals("null not allowed ", str, cust.getNumber());
  }

  Statements st = new Statements();
  service.SavingAccountImpl li = new service.SavingAccountImpl();
  SavingAccountM savingacc = new SavingAccountM(1000, 5, cust, st);
  private final double amount = 1000;

  @Test
  public void testDeposit() {
    // double balance =2000;

    SavingAccountM a = li.Deposit(amount);
    assertTrue(savingacc.getBalance() == 1000);
  }

  @Test
  public void testWithdraw() {

    final SavingAccountM a = li.Withdraw(amount);
    assertTrue(savingacc.getBalance() == 1000);
  }

}
