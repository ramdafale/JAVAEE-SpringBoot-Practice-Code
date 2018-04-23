/**
 * 
 */
package bankapplication;

/**
 * @author trainee
 * @class Account  ->     this class is abstract class which is then inherited by SavingAccount and currentAccount
 *
 */

abstract class Account {
final private	Customer customer;
static int id = 0;
protected	double balance = 0;  // because this field is associated with Saving and FlexibleSaving
public final	int accountNumber;

	/**
	 * @param balance
	 */
	public Account(final double balance, final Customer customer) {
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
	public void setBalance(final double balance) {
		this.balance = balance;
	}

	/**
	 * @return the accountNumber
	 */
	public int getAccountNumber() {
		return accountNumber;
	}

	

	@Override
	public String toString() {
		return "Account [balance=" + balance + ", accountNumber=" + accountNumber + "]";
	}

}
