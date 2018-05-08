package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import model.Customer;
import service.CustomerService;
import service.RetailerService;

@RestController
public class CustomersOp {

	@Autowired
	private RetailerService rs;

	@Autowired
	private CustomerService customerService;

	//
	@RequestMapping(value = "/user/{customerId}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable("customerId") int customerId) {
		System.out.printf("User with id {} is :", customerId);
		List<Customer> user = rs.viewCustomer(customerId);
		if (user == null) {
			System.out.printf("User with id {} not found.", customerId);
			return new ResponseEntity(new CustomErrorType("User with id " + customerId + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(user, HttpStatus.OK);
	}

	// Add customer

	@RequestMapping(value = "/addNewCustomer", method = RequestMethod.GET)
	public Customer NewUser() {

		Customer c = new Customer(24, "hulk", "newyork", "Debit");
		customerService.addCustomer(c);

		return null;

	}

	@RequestMapping(value = "/deleteuser/{id}", method = RequestMethod.GET)
	public Customer deleteUser(@PathVariable("id") int id) {
		System.out.printf("Fetching & Deleting User with id {}", id);

		customerService.removeCusotmer(id);
		return null;
	}

	@RequestMapping(value = "/updateuser/{id}/{name}", method = RequestMethod.GET)
	@ResponseBody
	public int updateUser(@PathVariable("id") int id, @PathVariable("name") String name) {
		System.out.printf("Updating User with id {}", id);

		final int dummy = customerService.updateCustomer(id, name);

		return dummy;
	}

	/*
	 * @RequestMapping("/showcustomer") public @ResponseBody List<Customer>
	 * addCustomer() { System.out.println("hi"); //return "hi to browser";
	 * 
	 * //ApplicationContext context = new
	 * AnnotationConfigApplicationContext(AppJavaConfig.class); //CustomerService
	 * customerService = (CustomerService)
	 * context.getBean("customerService",CustomerServiceImpl.class); RetailerService
	 * retailerService = (RetailerService) context.getBean("retailerService",
	 * RetailerServiceImpl.class);
	 * 
	 * 
	 * 
	 * List<Customer> customerList = rs.viewCustomer(1);
	 * 
	 * return customerList;
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * }
	 */
}
