package com.bank.service;

import com.bank.Exception.ManagedException;
import com.bank.model.Customer;

/**
 * @author ram
 *
 */
public interface ICustomerService {
	/**
	 * description : this method will return a customer which is
	 * created
	 */
	Customer createCustomer(final Customer customer) throws ManagedException;

	/**
	 *  description : this method will return a customer of specified id
	 */
	Customer getCustomerDetails(final Long id) throws ManagedException;
	/*
	 *  description : this method will return a customer of specified id with updation
	 * @throws CloneNotSupportedException ,ManagedException
	 */
	Customer updateCustomer(final Long id,final String name, final String userId ) throws ManagedException, CloneNotSupportedException;
}
