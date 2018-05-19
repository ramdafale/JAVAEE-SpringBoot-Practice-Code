package com.example.bank.service;

import com.example.bank.model.Customer;

public interface ICustomerService {
	public Customer createCustomer(Customer customer);
	public Customer getCustomerDetails(Long id);
	Customer findByUsername(String username);

}
