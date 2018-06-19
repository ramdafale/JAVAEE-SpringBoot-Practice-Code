package com.bank.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.Exception.ManagedException;
import com.bank.dto.CreateCustomerRequest;
import com.bank.model.Bank;
import com.bank.model.Customer;
import com.bank.service.IBankService;
import com.bank.service.ICustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private ICustomerService customerService;
	@Autowired
	private IBankService bankService;

	
	/**
	 * @return the customerService
	 */
	public ICustomerService getCustomerService() {
		return customerService;
	}

	/**
	 * @param customerService the customerService to set
	 */
	public void setCustomerService(ICustomerService customerService) {
		this.customerService = customerService;
	}

	/**
	 * @return the bankService
	 */
	public IBankService getBankService() {
		return bankService;
	}

	/**
	 * @param bankService the bankService to set
	 */
	public void setBankService(IBankService bankService) {
		this.bankService = bankService;
	}

	/* description : this method will return a
	 * created customer
	 */
	@PostMapping("/customerCreate")
	public ResponseEntity<?> createCustomer(@RequestBody final CreateCustomerRequest createCustomerRequest) {
		Customer response = null;
		Optional<Bank> bank;

		try {
			bank = bankService.getBankDetailsByID(createCustomerRequest.getBankID());

			if (bank.isPresent()) {
				final Customer cust = createCustomerRequest.getCustomer();
				cust.setBank(bank.get());

				try {
					response = customerService.createCustomer(cust);
				} catch (ManagedException e) {
				 e.getMessage();
					return new ResponseEntity<String>("faild to create customer", HttpStatus.OK);
				}
			}
			return new ResponseEntity<Customer>(response, HttpStatus.CREATED);
		} catch (ManagedException e1) {
			String message = e1.getMessage();
			return new ResponseEntity<String>("unable to fetch bank details by id", HttpStatus.OK);
		}

	}

	/* description : this method will return a created customer of
	 * specified id 
	 */
	@GetMapping("/getCustomerDetails/{id}")
	public ResponseEntity<?> getCustomer(@PathVariable final Long id) {
		final Customer cust;
		try {
			cust = customerService.getCustomerDetails(id);
			return new ResponseEntity<Customer>(cust, HttpStatus.OK);
		} catch (ManagedException e) {
			 e.getMessage();
		
		return new ResponseEntity<String>("id not found for customer to get details", HttpStatus.OK);
	}
	}
	
	@PutMapping("/{id}/{name}/{userId}")
	public ResponseEntity<?> updateCustomer(@PathVariable final Long id,@PathVariable final String name,@PathVariable final String userId) throws CloneNotSupportedException {
		final Customer cust;
		try {
			cust = customerService.updateCustomer(id, name, userId);
			return new ResponseEntity<Customer>(cust, HttpStatus.OK);
		} catch (ManagedException e) {
			 e.getMessage();
		
		return new ResponseEntity<String>("unable to update data", HttpStatus.OK);
	}
	}
}
