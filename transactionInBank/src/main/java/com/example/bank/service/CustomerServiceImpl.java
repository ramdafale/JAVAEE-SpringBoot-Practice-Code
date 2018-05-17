package com.example.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bank.model.Customer;
import com.example.bank.repository.CustomerRepository;

@Service("customerService")
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer createCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerRepository.save(customer);
	}

	@Override
	public Customer getCustomerDetails(Long id) {

		Customer customer = customerRepository.findById(id).get();

		return customer;

	}

	@Override
	public Customer findByUsername(String username) {
		return ((CustomerServiceImpl) customerRepository).findByUsername(username);

	}

}
