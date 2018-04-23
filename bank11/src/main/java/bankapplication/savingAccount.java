/**
 * 
 */
package bankapplication;

/**
 * @author trainee
 *
 */

class savingAccount extends Account {
Customer customer;
	/**
	 * @param balance
	 * @param accountNumber
	 */
final double interestRate= 0.07; 
	public savingAccount(double balance,Customer customer) {
		super(balance,customer);
		this.customer=customer;
		// TODO Auto-generated constructor stub
	}

	public void Deposit(int amount) {
		balance = balance + amount;
	}

	public void Withdraw(int amount) {
		if (balance - amount > 0) {
			balance = balance - amount;
		} else {
			System.out.println("Not enough balance to withdraw " + amount);
		}
	}
	
	public void addInterest()
	{
		
		balance = interestRate * balance+balance ;
		System.out.println("Salary After adding interest rate is :"+balance);
		
	}

	public void Disp() {
		System.out.println(" \n Account no:" + accountNumber + "\n Balance:" + balance+" \n Name:"+customer.getFname()+" \n Surname:"+customer.getLname());
	}

}
