/**
 * 
 */
package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Customer;
import com.example.service.ICustomerService;

/**
 * @author trainee
 *
 */
@RestController
public class CustomerController {

	
	
	@Autowired
	private ICustomerService iserv;
	
	
	
	
	
	
	@RequestMapping(value = "/create/customer")
	int add(@RequestBody Customer customer) {
		return iserv.add(customer);
}
	@RequestMapping(value = "/getCustomerDetails")
	Object viewDetails(@RequestBody Customer customer) {
		return iserv.viewDetails(customer);
}
	
	
}
