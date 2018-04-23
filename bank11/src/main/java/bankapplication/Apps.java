/* Program Name : Create Banking application in core java using concepts like collections and oops. 
 * Description:	  In Saving Account class and Current Account class there are methods withdraw and deposit amount.  
 * Author :       Ram Dafale					
 * Date of Completion: 23/4/2018
 */

package bankapplication;

/*  
 * Description:	  required librabry classes.
 */

import java.util.HashMap;
import java.util.Map;

/* 
 * class Name : Apps
 * Description:	 In Saving Account class and Current Account class there are methods withdraw and deposit amount.  
 */
public class Apps extends Bank {

	public static void main(String[] args) {
		System.out.println("Welcome To" + name);
		// Creating New account for customer
		Customer cust1 = new Customer("ram", "dafale", "at wardha", 976651934, "ramdafale@gmail.com");
		// We can use ArrayList too
		savingAccount Arr[] = new savingAccount[5];

		Arr[0] = new savingAccount(1000, cust1);

		Arr[0].Disp();
		double depAmnt = Arr[0].Deposit(1000);
		Arr[0].Disp();
		double withdAmnt = Arr[0].Withdraw(1000);
		//System.out.println(" Balance after adding interest rate is :");
		Arr[0].addInterest();
		Arr[0].Disp();
		// Creating another account for customer
		Customer cust2 = new Customer("shyam", "dafale", "at Delhi", 97665454, "shyamdafale@gmail.com");

		Arr[1] = new savingAccount(5000, cust2);

		Arr[1].Disp();
		Arr[1].Deposit(1000);
		Arr[1].Disp();
		Arr[1].Withdraw(700);
		//System.out.println(" Balance after adding interest rate is :");
		Arr[1].addInterest();
		Arr[1].Disp();

		// Code for printing the statement of each customer

		Map<Integer, Account> hm = new HashMap<Integer, Account>();
		hm.put(1, Arr[0]);
		hm.put(2, Arr[1]);

		for (Map.Entry<Integer, Account> entry : hm.entrySet()) {
			int key = entry.getKey();
			Account b = entry.getValue();
			System.out.println(key + " Mini Statement of :");
			System.out.println("AccountNo:" + b.getAccountNumber() + "Account Balance: " + b.getBalance()
					+ "Deposit Amt " + depAmnt + "Withdraw Amt:" + withdAmnt);

		}
	}
}


//Output 

/*
 Welcome To-------Indian Bank------
 
 Account no:1
 Balance:1000.0 
 Name:ram 
 Surname:dafale
 
 Account no:1
 Balance:2000.0 
 Name:ram 
 Surname:dafale
Mon Apr 23 19:20:22 IST 2018
 Balance after adding interest rate is :
Salary After adding interest rate is :1070.0
 
 Account no:1
 Balance:1070.0 
 Name:ram 
 Surname:dafale
 
 Account no:2
 Balance:5000.0 
 Name:shyam 
 Surname:dafale
 
 Account no:2
 Balance:6000.0 
 Name:shyam 
 Surname:dafale
Mon Apr 23 19:20:22 IST 2018
 Balance after adding interest rate is :
Salary After adding interest rate is :5671.0
 
 Account no:2
 Balance:5671.0 
 Name:shyam 
 Surname:dafale
1 Mini Statement of :
AccountNo:1Account Balance: 1070.0Deposit Amt 2000.0Withdraw Amt:1000.0
2 Mini Statement of :
AccountNo:2Account Balance: 5671.0Deposit Amt 2000.0Withdraw Amt:1000.0

 */
