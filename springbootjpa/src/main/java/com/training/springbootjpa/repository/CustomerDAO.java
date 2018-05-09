package com.training.springbootjpa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.training.springbootjpa.model.Customer;

public interface CustomerDAO extends CrudRepository<Customer, Long> {

	public List<Customer> findByCustomerName(String customerName);
	//public List<Customer> findByCustomerNameAndCustomerAddress(String customerName, String customerAddress);
}
