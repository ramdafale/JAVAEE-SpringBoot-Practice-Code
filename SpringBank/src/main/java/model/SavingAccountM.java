/**
 * 
 */
package model;

/**
 * @author trainee
 *
 */
public class SavingAccountM {

  double balance=5000;
  int accountNumber;

  final double interestRate = 0.07;

  static int id = 0;
  Customer customer;

  /*
   * @param balance
   * @param accountNumber
   * @param customer
   */
  public SavingAccountM(double balance, int accountNumber, Customer customer) {
    super();
    this.balance = balance;
    this.accountNumber = ++id;
    this.customer = customer;
  }
  
  
  public SavingAccountM(double balance, Customer customer) {
    super();
    this.balance = balance;
    this.accountNumber = ++id;
    this.customer = customer;
  }
  

  public SavingAccountM(double balance, int accountNumber) {

    this.balance = balance;
    this.accountNumber = accountNumber;

  }

  public SavingAccountM() {

  }

  /**
   * @return the balance
   */
  public double getBalance() {
    return balance;
  }

  /**
   * @param balance
   *          the balance to set
   */
  public void setBalance(double balance) {
    this.balance = balance;
  }

  /**
   * @return the accountNumber
   */
  public int getAccountNumber() {
    return accountNumber;
  }

  /**
   * @param accountNumber
   *          the accountNumber to set
   */
  public void setAccountNumber(int accountNumber) {
    this.accountNumber = accountNumber;
  }

  /**
   * @return the customer
   */
  public Customer getCustomer() {
    return customer;
  }

  /**
   * @param customer
   *          the customer to set
   */
  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  /**
   * @return the interestRate
   */
  public double getInterestRate() {
    return interestRate;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "SavingAccountM balance=" + balance + ", accountNumber=" + accountNumber
        + ", interestRate=" + interestRate + ", customer=" + customer + "";
  }

}
