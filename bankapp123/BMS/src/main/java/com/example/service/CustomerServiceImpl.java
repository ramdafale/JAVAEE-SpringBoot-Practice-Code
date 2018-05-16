 package com.example.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CustomerRepository;
import com.example.model.Bank;
import com.example.model.Customer;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	CustomerRepository idao;

	@Override
	public Customer add(Customer customer) {
		// TODO Auto-generated method stub
		

		return idao.save(customer);
	}

	@Override
	public Object viewDetails(Customer customer) {
		
		return null;
	}

	@Override
	public Optional<Bank> getBankDetailById(BigDecimal id) {
		// TODO Auto-generated method stub
		return null;
	}

	
    
	
	
	
	
	
	
}
