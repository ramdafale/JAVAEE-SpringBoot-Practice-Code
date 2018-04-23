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
	
		// using collection 
		List<Account> allObj = new ArrayList<Account>();
	
	//	Statement statementObj1 = new Statement("1st Statement");
	//	Statement statementObj2 = new Statement("2nd Statement");
		// List statementList = new ArrayList<Statement>();
	//	statementList.add(statementObj1);
	//	statementList.add(statementObj2);
		
		

	}
}
