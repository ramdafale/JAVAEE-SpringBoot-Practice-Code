package com.training.springbootjpa.service;

import java.util.List;

import com.training.springbootjpa.model.Customer;

public interface CustomerService {

	Customer addCustomer(Customer customer);

	List<Customer> deleteCustomerById(long deleteById);

	Customer updateCustomerById(long updateById);

	List<Customer> getCustomer();
	
	

}
