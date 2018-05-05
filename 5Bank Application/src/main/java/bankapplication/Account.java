/**
 * 
 */
package bankapplication;

/**
 * @author trainee
 *
 */

abstract class Account {
	Customer customer;
	static int id = 0;
	double balance = 0;
	int accountNumber;

	/**
	 * @param balance
	 */
	public Account(double balance,Customer customer) {
		// super(fname, lname, address, number, email);
		this.balance = balance;
		// this.accountNumber = account;
		accountNumber = ++id;
		this.customer=customer;
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance
	 *            the balance to set
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

	/*
	 * public abstract void calcsal(double a); public void disp() {
	 * 
	 * System.out.println("Id"+ id+"Salary"+balance);
	 * 
	 * }
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Account [balance=" + balance + ", accountNumber=" + accountNumber + "]";
	}

}