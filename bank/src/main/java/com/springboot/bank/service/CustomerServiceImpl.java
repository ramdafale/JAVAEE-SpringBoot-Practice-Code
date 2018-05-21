/**
 * 
 */
package com.springboot.bank.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.bank.dto.WrapperBankCustomer;
import com.springboot.bank.exception.BankException;
import com.springboot.bank.model.Bank;
import com.springboot.bank.model.Customer;
import com.springboot.bank.repository.BankDAO;
import com.springboot.bank.repository.CustomerDAO;

/**
 * @author Sumit
 *@Service("customerService")
 *Desc: This class provide implementation for creating customer and 
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	
	
	//injecting properties of  CustomerDAO
	@Autowired
	CustomerDAO customerDao;
	//injecting properties of  BankDAO
	@Autowired
	BankDAO bankDao;

	/*
	 * CustomerService#createCustomer
	 * DESC: it will create a new customer having bankId associated with it.
	 * It calls save method to save he state of customer object into database.
	 */
	
	
	@Override
	public Customer createCustomer(WrapperBankCustomer wrapperBankCustomer) throws BankException {
		Customer customer = null;
		Customer customerData = null;
		customer = wrapperBankCustomer.getCustomer();
		// System.out.println(customer);
		Long bankId = wrapperBankCustomer.getBankId();
		Optional<Bank> bankList = bankDao.findById(bankId);
		Bank bank = bankList.get();
		customer.setBank(bank);
		customerData = customerDao.save(customer);
		// System.out.println(customerData);
		return customerData;
	}
	/*
	 * CustomerService#createCustomer
	 * DESC: it will show  customer details having customerId associated with it.
	 * It calls findById method to find that entity by id and return the object to responce.
	 */
	@Override
	public Customer getCustomerDetails(Long customerId) throws BankException {
		Optional<Customer> customerList = customerDao.findById(customerId);
		if (customerList.isPresent()) {
			Customer customer = customerList.get();
			return customer;
		} else
			throw new BankException("Bank details not found");
	}
}
