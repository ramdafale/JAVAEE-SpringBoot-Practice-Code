package com.training.springbootjpa.service;

import java.util.List;
import java.util.Optional;

import com.training.springbootjpa.exception.ManagedException;
import com.training.springbootjpa.model.Customer;

public interface CustomerService {

	Customer addCustomer(Customer customer);

	List<Customer> deleteCustomerById(long deleteById);

	Customer updateCustomerById(long updateById);

	List<Customer> getCustomer();

	Optional<Customer>  getCustomerDetail(Long id) throws ManagedException;
	
	

}
