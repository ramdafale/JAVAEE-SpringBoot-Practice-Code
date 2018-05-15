/**
 * 
 */
package com.example.service;

import java.util.List;

import com.example.model.Customer;

/**
 * @author trainee
 *
 */
public interface CustomerService {

	
	
	Customer createCustomer(Customer customer);
	
	List<Customer> getCustomerdetails();
	
}
