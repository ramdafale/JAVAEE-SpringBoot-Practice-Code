package com.training.groceryApp;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import model.Customer;
import repository.CustomerDAOImpl;

/**
 * Unit test for simple Application.
 */
public class AppTest 
{
   
	Customer cust1 = new Customer(1,"shyam","wardha","Cash");
	
	
	
	
	
			@Test
			public void InsertCustomerIntoDatabase()
			{
				int customerId= 3;
			    String name = "Shyam";
			    String address = "wardha";
			    String paymentMode= "Cash";
			    CustomerDAOImpl  testAddCustomer = new CustomerDAOImpl();
			   
				int inserted = testAddCustomer.addCustomer(customerId,name,address, paymentMode);
				if (inserted > 0)
				{ 
					int i = 1;
				}
				
			    Assert.assertEquals(1, inserted);
			}
			
			
			
}
