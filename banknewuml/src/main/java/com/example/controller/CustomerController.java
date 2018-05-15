/**
 * 
 */
package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.model.Customer;
import com.example.service.CustomerServiceImpl;

/**
 * @author trainee
 *
 */
@Controller
public class CustomerController {

	
	
	@Autowired
	CustomerServiceImpl customerServiceImpl;
	
	
	
	
	@PostMapping( path = "/createcustomer" , consumes = "application/json", produces = "application/json")
	public ResponseEntity<Customer> addOneCustomer(@RequestBody WrapperClass wrapperClass )
	{	
		System.out.println(wrapperClass + "response body");
		System.out.println(wrapperClass.bank + "bank in createcustomer ");
		System.out.println(wrapperClass.customer.getCustomerName());
		System.out.println(wrapperClass.customer.getPin());
		Customer cust = new Customer();
		
		cust.setBank(wrapperClass.bank);
		cust.setCustomerName(wrapperClass.customer.getCustomerName());
		cust.setPin(wrapperClass.customer.getPin());
		
	  Customer result 	= customerServiceImpl.createCustomer(cust);
		
		
		return new ResponseEntity<Customer>(result,HttpStatus.OK);
		
		
		
	}
	
	@GetMapping("/viewcustomer")
	public ResponseEntity<List<Customer>> viewCustomer()
	{
		List<Customer> result = customerServiceImpl.getCustomerdetails();

		return new ResponseEntity<List<Customer>>(result,HttpStatus.OK);
		
		
		
	}
	
}
