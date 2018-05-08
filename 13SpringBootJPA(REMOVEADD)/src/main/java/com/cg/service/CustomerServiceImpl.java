package com.cg.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dao.CustomerRepository;
import com.cg.model.Customer;

@Service("customerService")
public class CustomerServiceImpl implements ICustomerService{

	
	@Autowired 
	public CustomerRepository customerRepository;

	public List findAll(long id) {
      //  CustomerRepository customerRepo = null;
        List<Customer> customerList = customerRepository.findAll();
		Iterator iterator = customerList.iterator();
		while(iterator.hasNext())
				{
				Customer customer = (Customer) iterator.next();
				if(customer.getCustomerId() == id)
				{
					customerRepository.deleteById(id);
				}
    }
		return customerList;
	}
	
	/*
	public Customer findById(long id) {
		for(Customer user : users){
			if(user.getCustomerId() == id){
				return user;
			}
		}
		return null;
	}*/

	@Override
	public void saveUser(Customer user) {
		
		
	}

//public void deleteUserById(long id) {
//		
//		for (Iterator<Customer> iterator = users.iterator(); iterator.hasNext(); ) {
//			Customer user = iterator.next();
//		    if (user.getCustomerId() == id) {
//		        iterator.remove();
//		    }
//		}
//	}
//	
//	
	
	
	
	/*

	@Override
	public Customer AddCustomer(Customer customer) {
		// TODO Auto-generated method stub
		customerRepository.save(customer);
		return customer;
	}*/


	
	
}
