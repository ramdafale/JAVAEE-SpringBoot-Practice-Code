package bankapplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;




public class Apps extends Bank {  

	public static void main(String[] args) {
		System.out.println("Welcome To" + name);

		Customer cust1=new Customer("ram","dafale","at wardha", 976651934, "ramdafale@gmail.com");
		Account Arr[] = new savingAccount[100];
		Arr[0] = new savingAccount(1000,cust1);
	

		((savingAccount) Arr[0]).Disp();
		((savingAccount) Arr[0]).Deposit(1000);
		((savingAccount) Arr[0]).Disp();
		((savingAccount) Arr[0]).Withdraw(1000);
		((savingAccount) Arr[0]).Disp();
	
		Customer cust2=new Customer("shyam","dafale","at Delhi", 97665454, "shyamdafale@gmail.com");
		
		Arr[1] = new savingAccount(1000,cust2);
	

		((savingAccount) Arr[1]).Disp();
		((savingAccount) Arr[1]).Deposit(1000);
		((savingAccount) Arr[1]).Disp();
		((savingAccount) Arr[1]).Withdraw(1000);
		((savingAccount) Arr[1]).Disp();
		
		
		
		
		// using collection 
		List<Account> allCustomer = new ArrayList<Account>();
		allCustomer.add(Arr[0]);
		allCustomer.add(Arr[1]);
		
		
		
		
		
		
		
	
	//	Statement statementObj1 = new Statement("1st Statement");
	//	Statement statementObj2 = new Statement("2nd Statement");
		// List statementList = new ArrayList<Statement>();
	//	statementList.add(statementObj1);
	//	statementList.add(statementObj2);
		
		

	}
}
