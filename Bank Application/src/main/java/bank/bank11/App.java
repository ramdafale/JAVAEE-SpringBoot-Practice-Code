package bank.bank11;

class Bank {
	static String name = "My Bank";

	public void bankName() {

		System.out.println(name);

	}

}

class Customer {
	String fname = null;
	String lname = null;
	String address = null;
	long number;
	String email;

	/**
	 * @param fname
	 * @param lname
	 * @param address
	 * @param number
	 * @param email
	 */
	public Customer(String fname, String lname, String address, long number, String email) {
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.number = number;
		this.email = email;
	}

	/**
	 * @return the fname
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * @param fname
	 *            the fname to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}

	/**
	 * @return the lname
	 */
	public String getLname() {
		return lname;
	}

	/**
	 * @param lname
	 *            the lname to set
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the number
	 */
	public long getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(long number) {
		this.number = number;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}

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

class savingAccount extends Account {
Customer customer;
	/**
	 * @param balance
	 * @param accountNumber
	 */
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

	public void Disp() {
		System.out.println(" \n Account no:" + accountNumber + "\n Balance:" + balance+" \n Name:"+customer.getFname()+" \n Surname:"+customer.getLname());
	}

}

public class App extends Bank {

	public static void main(String[] args) {
		System.out.println("Welcome To" + name);

		Customer cust1=new Customer("ram","dafale","at wardha", 976651934, "ramdafale@gmail.com");
		Account Arr[] = new savingAccount[100];
		Arr[0] = new savingAccount(1000,cust1);
	//	Arr[1] = new savingAccount(5000);

		((savingAccount) Arr[0]).Disp();
		((savingAccount) Arr[0]).Deposit(1000);
		((savingAccount) Arr[0]).Disp();
		((savingAccount) Arr[0]).Withdraw(1000);
		((savingAccount) Arr[0]).Disp();
	//	((savingAccount) Arr[1]).Disp();

	}
}
