/**
 * 
 */
package model;

/**
 * @author trainee
 *
 */
public class CurrentAccountM {
	Customer customer;
	double interestRate = 0.08;
	double balance;

	static int id = 100;

	int accountNumber;

	/**
	 * @param customer
	 * @param interestRate
	 * @param balance
	 * @param accountNumber
	 */
	public CurrentAccountM(Customer customer, double interestRate, double balance, int accountNumber) {
		super();
		this.customer = customer;
		this.interestRate = interestRate;
		this.balance = balance;
		this.accountNumber = accountNumber;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
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

	/**
	 * @param interestRate the interestRate to set
	 */
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
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
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CurrentAccountM customer=" + customer + ", interestRate=" + interestRate + ", balance=" + balance
				+ ", accountNumber=" + accountNumber + "";
	}
	
	
	
	

}
