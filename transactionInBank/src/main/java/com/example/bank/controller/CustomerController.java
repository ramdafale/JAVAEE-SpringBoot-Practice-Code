package com.example.bank.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.model.Bank;
import com.example.bank.model.Customer;
import com.example.bank.request.CreateCustomerRequest;
import com.example.bank.service.IBankService;
import com.example.bank.service.ICustomerService;

@RestController
@RequestMapping("/apple")
public class CustomerController {
	@Autowired
	private ICustomerService customerService;
	@Autowired
	private IBankService bankService;

	@PostMapping("/customerCreate")
	public ResponseEntity<?> createCustomer(@RequestBody final CreateCustomerRequest createCustomerRequest) {
		Customer response;
		Optional<Bank> bank = bankService.getBankDetailsByID(createCustomerRequest.getBankID());
		if (bank.isPresent()) {
			Customer cust = createCustomerRequest.getCustomer();
			cust.setBank(bank.get());

			response = customerService.createCustomer(cust);
			return new ResponseEntity<Customer>(response, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("no such bank exsit", HttpStatus.OK);
		}

	}

	@GetMapping("/getCustomerDetails/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
		Customer cust= customerService.getCustomerDetails(id);
		System.out.println(">>>>>>>>>>>>>>" + cust);
		
		return new ResponseEntity<Customer>(cust, HttpStatus.OK);
	}
	
	@GetMapping("/getCustomer/{id}")
	public Customer getCustomerSD(@PathVariable Long id) {
		Customer cust= customerService.getCustomerDetails(id);
		System.out.println(">>>>>>>>>>>>>>" + cust);
		
		return cust;
	}
	
	@GetMapping("/customerTest")
	public ResponseEntity<Customer> getCustomerEm() {
		Customer cust = new Customer(1212L, "", 1213L, null);
		
		System.out.println(">>>>>>>>>>>>>>>>>>>"+cust);
		
		return new ResponseEntity<Customer>(cust, HttpStatus.OK);
	}

}
