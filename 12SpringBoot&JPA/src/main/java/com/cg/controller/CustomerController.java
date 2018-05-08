package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.model.Customer;
import com.cg.service.ICustomerService;

@RestController
public class CustomerController {
 
	@Autowired
	ICustomerService customerService;
	
	@PostMapping("/customerCreate")
	public ResponseEntity<Customer> createCustomer(@RequestBody final Customer customer)
	{
		
		Customer cust=customerService.addCustomer(customer);
		return new ResponseEntity<Customer>(customer,HttpStatus.CREATED);
		
	}
	
	
	
	
}
