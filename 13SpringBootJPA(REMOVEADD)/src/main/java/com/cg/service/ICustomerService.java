package com.cg.service;

import java.util.List;

import com.cg.model.Customer;

public interface ICustomerService {
	
	//public Customer AddCustomer(Customer customer);
	
//Customer findById(long id);
	public List findAll(long id);
/*Customer findByName(String name);*/
	
	void saveUser(Customer user);
	//void deleteUserById(long id);	
	/*void updateUser(Customer user);
	
	

	List<Customer> findAllUsers();
	
	void deleteAllUsers();
	
	boolean isUserExist(Customer user);*/
	
}
