/**
 * 
 */
package com.example.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Request.WrapperClass;
import com.example.model.Bank;
import com.example.model.Customer;
import com.example.service.BankServiceImpl;
import com.example.service.ICustomerService;

/**
 * @author trainee
 *
 */
@RestController
public class CustomerController {

	@Autowired
	private ICustomerService iserv;
	
	
	@Autowired 
	BankServiceImpl bankserviceImpl;

	@PostMapping("/customerCreate")
	public ResponseEntity<?> createCustomer(@RequestBody
			final WrapperClass createCustomerRequest) {
		Customer response;
		Optional<Bank> bank = bankserviceImpl.getBankDetailById(createCustomerRequest.getId());
		System.out.println("Optional >>>>" + bank);
		Customer cust = createCustomerRequest.getCustomer();
		cust.setBank(bank.get());

		response = iserv.add(cust);
		return new ResponseEntity<Customer>(response, HttpStatus.CREATED);
		
			
	} 

	@RequestMapping(value = "/getCustomerDetails")
	Object viewDetails(@RequestBody Customer customer) {
		return iserv.viewDetails(customer);
	}

}
