/**
 * 
 */
package bankapplication;

/**
 * @author trainee
 *
 */


class currentAccount extends Account
{
	public currentAccount(double balance,Customer customer) {
		super(balance,customer);
		this.customer=customer;
		// TODO Auto-generated constructor stub
	}

    public void Withdraw(int amount)
    {
       
        if (amount < 0)
        {
           
        }
        balance = balance - amount;
        
    }
}