/**
 * 
 */
package bankapplication;

/**
 * @author ram dafale This class create a current account and perform basic
 *         deposit and witrdraw operations.
 */
class currentAccount extends Account {
	Customer customer;
	double interestRate = 0.08;

	public currentAccount(double balance, Customer customer) {
		super(balance, customer);
		this.customer = customer;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param amount
	 */
	public double Deposit(int amount) {
		return balance = balance + amount;
	}

	/**
	 * @param amount
	 */
	public void Withdraw(int amount) {

		if (amount < 0) {

		}
		balance = balance - amount;

	}

	public void addInterest() {

		balance = interestRate * balance + balance;
		System.out.println("Salary After adding interest rate is :" + balance);

	}

}
