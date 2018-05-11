package com.training.springbootjpa.service;

import java.util.List;

import com.training.springbootjpa.exception.ManagedException;
import com.training.springbootjpa.model.Customer;

public interface CustomerService {

	Customer addCustomer(Customer customer) throws  ManagedException;

	Customer deleteCustomerById(long deleteById)throws ManagedException ;

	Customer updateCustomerById(long updateById);

	List<Customer> getCustomer();

	String getCustomerDetail(Long id) throws ManagedException;
	
	

}
