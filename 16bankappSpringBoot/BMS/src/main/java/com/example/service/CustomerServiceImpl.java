 package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CustomerRepository;
import com.example.model.Customer;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	CustomerRepository idao;

	@Override
	public int add(Customer customer) {
		// TODO Auto-generated method stub
		idao.save(customer);

		return 0;
	}

	@Override
	public Object viewDetails(Customer customer) {
		
		return null;
	}

}
