package BankApplicationWIthMaven.BankApplicationAI;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import bankapplication.Customer;





public class BankAppJunit  {

	
	
	Customer cust = new Customer("ram", "dafale", "wardha", 97546545, "ramdafale@gmail.com");
	

	
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



 






