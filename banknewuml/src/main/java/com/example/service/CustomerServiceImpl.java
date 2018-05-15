package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CustomerRepository;
import com.example.model.Customer;



@Service
public class CustomerServiceImpl implements CustomerService {

	
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	
	
	
	
	@Override
	public Customer createCustomer(Customer customer) {
		
		Customer addNewCustomer =  customerRepository.save(customer);
		
		
		return addNewCustomer;
	}






	@Override
	public List<Customer> getCustomerdetails() {
		
		List<Customer> custlist;
		
		custlist=customerRepository.findAll();
		
		return custlist;
	}

}
