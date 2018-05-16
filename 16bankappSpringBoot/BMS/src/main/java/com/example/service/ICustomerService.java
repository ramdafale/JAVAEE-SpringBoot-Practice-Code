package com.example.service;

import com.example.model.Customer;

public interface ICustomerService {

	int add(Customer customer);

	Object viewDetails(Customer customer);

}
