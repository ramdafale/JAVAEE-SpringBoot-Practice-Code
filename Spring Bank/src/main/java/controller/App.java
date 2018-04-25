package controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.Bank;
import model.Customer;


public class App extends Bank {
	public static void main(String[] args) {

		
	
		 ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
	      
	      Customer customer1 = ctx.getBean(Customer.class);
		
		
		//  Resource r=new ClassPathResource("beans.xml");  
	      //  BeanFactory factory=new XmlBeanFactory(r);  
	          
	      //  Customer s=(Customer)factory.getBean("customer");  
	       
	        
	        List<Customer> listOfSavingAccount = new ArrayList<Customer>();

	     

			listOfSavingAccount.add(customer1);
			
			for (Customer item : listOfSavingAccount) {
				System.out.println("retrieved element: " + item);
			}
		
		
		// CustomerService service = new CustomerServiceImpl();
		// for Testing Purpose
		// System.out.println(service.findAll().get(0).getFname());

		// final Logger LOGGER = Logger.getLogger(App.class.getName());

		/*System.out.println("Welcome To" + name);
		// Creating New account for customer

		Customer cust1 = new Customer("ram", "dafale", "at wardha", 976651934, "ramdafale@gmail.com");
		// We can use ArrayList too
		Customer cust2 = new Customer("Shyam", "Dafale", "at wardha MH", 735148469, "shyamdafale@gmail.com");

		List<savingAccount> listOfSavingAccount = new ArrayList<savingAccount>();

		savingAccount savingaccount1 = new savingAccount(100, cust1);
		savingAccount savingaccount2 = new savingAccount(500, cust2);

		listOfSavingAccount.add(savingaccount1);
		listOfSavingAccount.add(savingaccount2);

		for (savingAccount item : listOfSavingAccount) {
			System.out.println("retrieved element: " + item);
		}*/

		/*
		 * Account Arr = new savingAccount[5];
		 * 
		 * Arr[0] = new savingAccount(1000, cust1);
		 * 
		 * Arr[0].Disp(); double depAmnt = Arr[0].Deposit(1000); Arr[0].Disp(); double
		 * withdAmnt = Arr[0].Withdraw(1000);
		 * //System.out.println(" Balance after adding interest rate is :");
		 * Arr[0].addInterest(); Arr[0].Disp(); // Creating another account for customer
		 * Customer cust2 = new Customer("shyam", "dafale", "at Delhi", 97665454,
		 * "shyamdafale@gmail.com");
		 * 
		 * Arr[1] = new savingAccount(5000, cust2);
		 * 
		 * Arr[1].Disp(); Arr[1].Deposit(1000); Arr[1].Disp(); Arr[1].Withdraw(700);
		 * //System.out.println(" Balance after adding interest rate is :");
		 * Arr[1].addInterest(); Arr[1].Disp();
		 * 
		 * // Code for printing the statement of each customer
		 * 
		 * Map<String, Account> hm = new HashMap<String, Account>(); hm.put("1",
		 * Arr[0]); hm.put("2", Arr[1]);
		 * 
		 * for (Map.Entry<String, Account> entry : hm.entrySet()) { String key =
		 * entry.getKey(); Account b = entry.getValue(); System.out.println(key +
		 * " Mini Statement of :"); System.out.println("AccountNo:" +
		 * b.getAccountNumber() + "Account Balance: " + b.getBalance() + "Deposit Amt "
		 * + depAmnt + "Withdraw Amt:" + withdAmnt);
		 * 
		 * 
		 */
	}

}
