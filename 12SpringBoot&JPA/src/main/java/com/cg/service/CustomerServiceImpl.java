package com.cg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.CustomerRepository;
import com.cg.model.Customer;

@Service("customerService")
public class CustomerServiceImpl implements ICustomerService{
	@Autowired 
	public CustomerRepository customerRepository;

	@Override
	public Customer AddCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerRepository.save(customer);
		return customer;
	}

	@Override
	public Customer DeleteCustomer(Customer customer) {
	
		customerRepository.delete(entity);
		return customer;
	}
	
	

}
