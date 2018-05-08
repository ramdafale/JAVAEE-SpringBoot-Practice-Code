package com.cg.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dao.CustomerRepository;
import com.cg.model.Customer;
import com.cg.service.ICustomerService;

@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	ICustomerService customerService;

	// -------------------Create a User-------------------------------------------

	@RequestMapping(value = "/addNewCustomer", method = RequestMethod.GET)
	public Customer NewUser() {

		Customer c = new Customer("hulk", "newyork", "Debit");
		customerService.saveUser(c);

		return null;

	}

	/*@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
		// System.out.printf("Fetching & Deleting User with id {}", id);

		Customer user = customerService.findById(id);
		// if (user == null) {
		// System.out.printf("Unable to delete. User with id {} not found.", id);
		// return new ResponseEntity(new CustomErrorType("Unable to delete. User with id
		// " + id + " not found."),
		// HttpStatus.NOT_FOUND);

		customerService.deleteUserById(id);
		// return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
		return null;
	}*/

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	 public void findAll(@PathVariable("id") long id)
	 {
		
		customerService.findAll(id);
		
		
	}

	/*
	 * @PostMapping("/customerCreate") public ResponseEntity<Customer>
	 * createCustomer(@RequestBody final Customer customer) {
	 * 
	 * Customer cust=customerService.addCustomer(customer); return new
	 * ResponseEntity<Customer>(customer,HttpStatus.CREATED);
	 * 
	 * }
	 * 
	 */

	// -------------------Retrieve All
	// Users---------------------------------------------

	/*
	 * @RequestMapping(value = "/user/", method = RequestMethod.GET) public
	 * ResponseEntity<List<Customer>> listAllUsers() { List<Customer> users =
	 * customerService.findAllUsers(); if (users.isEmpty()) { return new
	 * ResponseEntity(HttpStatus.NO_CONTENT); // You many decide to return
	 * HttpStatus.NOT_FOUND } return new ResponseEntity<List<Customer>>(users,
	 * HttpStatus.OK); }
	 * 
	 * // -------------------Retrieve Single //
	 * User------------------------------------------
	 * 
	 * @RequestMapping(value = "/user/{id}", method = RequestMethod.GET) public
	 * ResponseEntity<?> getUser(@PathVariable("id") long id) {
	 * System.out.printf("Fetching User with id {}", id); Customer user =
	 * customerService.findById(id); if (user == null) {
	 * System.out.printf("User with id {} not found.", id); return new
	 * ResponseEntity(new CustomErrorType("User with id " + id + " not found"),
	 * HttpStatus.NOT_FOUND); } return new ResponseEntity<Customer>(user,
	 * HttpStatus.OK); }
	 */

	/*
	 * // ------------------- Update a User //
	 * ------------------------------------------------
	 * 
	 * @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT) public
	 * ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody
	 * Customer user) { System.out.printf("Updating User with id {}", id);
	 * 
	 * Customer currentUser = customerService.findById(id);
	 * 
	 * if (currentUser == null) {
	 * System.out.printf("Unable to update. User with id {} not found.", id); return
	 * new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id
	 * + " not found."), HttpStatus.NOT_FOUND); }
	 * 
	 * currentUser.setCustomerName(user.getCustomerName());
	 * currentUser.setCustomerAddress(user.getCustomerAddress());
	 * currentUser.setPaymentMode(user.getPaymentMode());
	 * 
	 * customerService.updateUser(currentUser); return new
	 * ResponseEntity<Customer>(currentUser, HttpStatus.OK); }
	 * 
	 * // ------------------- Delete a User-----------------------------------------
	 * 
	 * @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE) public
	 * ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
	 * System.out.printf("Fetching & Deleting User with id {}", id);
	 * 
	 * Customer user = customerService.findById(id); if (user == null) {
	 * System.out.printf("Unable to delete. User with id {} not found.", id); return
	 * new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id
	 * + " not found."), HttpStatus.NOT_FOUND); }
	 * customerService.deleteUserById(id); return new
	 * ResponseEntity<Customer>(HttpStatus.NO_CONTENT); }
	 * 
	 * // ------------------- Delete All Users-----------------------------
	 * 
	 * @RequestMapping(value = "/user/", method = RequestMethod.DELETE) public
	 * ResponseEntity<Customer> deleteAllUsers() {
	 * System.out.printf("Deleting All Users");
	 * 
	 * customerService.deleteAllUsers(); return new
	 * ResponseEntity<Customer>(HttpStatus.NO_CONTENT); }
	 */

}
