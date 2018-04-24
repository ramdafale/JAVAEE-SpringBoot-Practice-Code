/*
 * Program Name: Test cases for abnk application using JUNIT
 * @author Ram Dafale
 * DateOF Completion : 4/42/2018
 */



package BankApplicationWIthMaven.BankApplicationAI;



import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import bankapplication.Customer;




/*
 * class Name: BankAppJunit
 * Description : this class contains various test cases for bank application 
 * 				1. Null values are not allowed in Name
 * 				2. Null values are not allowed in lASTnaME
 * 				3. Null values are not allowed in MOBILE NUMBER
 * 				4. checking if deposit method running or not
 * 				5.	 checking if withdraw method running or not
 * 				6. checking if addInterest method running or not
 */			
public class BankAppJunit  {

	
	//Creating a customer class object
	Customer cust = new Customer("ram", "dafale", "wardha", 97546545, "ramdafale@gmail.com");
	

	// 1. Null values are not allowed in Name
	@Test
	public void checknullInFirstName()
	{
		String  str= null;
		assertNotEquals("null not allowed ",str,cust.getFname());
	}
	
	

	@Test
	public void checknullInLastName()
	{
		String  str1= null;
		assertNotEquals("null not allowed ",str1,cust.getLname());
	}
	
	
	@Test
	public void checknullInMobile()
	{
		String  str2= null;
		assertNotEquals("null not allowed ",str2,cust.getNumber());
	}
	
	
	   @Test
	   public void testWithdraw(){
		   double balance =2000;
		   int   amount = 1000;
	      assertTrue(  (balance =  balance - amount) == 1000);
	   }


	 

	   @Test
	   public void testDeposit(){
		   double balance = 0;
		   int   amount = 1000;
	     
	   }

	
	   
	   @Test
	   public void addInterest() {
		   	int balance=100;
		   double	interestRate=0.087;
			
		   assertFalse(  ( balance = (int) (interestRate * balance + balance)) == 1000); ;

		}
	   
	   
	 
	 

}



 






