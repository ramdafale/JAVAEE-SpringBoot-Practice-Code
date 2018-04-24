package HelloSpring2.HelloSpring2;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import HelloSpring2.HelloSpring2.service.CustomerService;
import HelloSpring2.HelloSpring2.service.CustomerServiceImpl;


/**
 * Hello world!
 *
 */


public class App extends Bank
{
    public static void main( String[] args )
    {
        
    	CustomerService service = new CustomerServiceImpl();
        //for Testing Purpose
        System.out.println(service.findAll().get(0).getFname());
        
        
        
        
	//final  Logger LOGGER = Logger.getLogger(App.class.getName());
		
		
		
		System.out.println("Welcome To" + name);
		// Creating New account for customer
		Customer cust1 = new Customer("ram", "dafale", "at wardha", 976651934, "ramdafale@gmail.com");
		// We can use ArrayList too
		Account Arr[] = new savingAccount[5];

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
