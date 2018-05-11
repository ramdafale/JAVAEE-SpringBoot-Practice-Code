package com.training.springbootjpa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;

import com.training.springbootjpa.exception.ManagedException;
import com.training.springbootjpa.model.Customer;

public interface CustomerDAO extends CrudRepository<Customer, Long> {

	// this method find your name in database and return the details if name found
	// also this method internally fire a select query based on parameter you pass to it.
	Customer findByCustomerName(String customerName) throws ManagedException;
	
    
    // this method find your name & database in database and return the details if name & address is found
		// also this method internally fire a select query based on parameter you pass to it.
	 List<Customer> findByCustomerNameAndCustomerAddress(String customerName, String customerAddress);
}
