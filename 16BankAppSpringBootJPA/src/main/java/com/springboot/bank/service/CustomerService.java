/**
 * 
 */
package com.springboot.bank.service;

import com.springboot.bank.dto.WrapperBankCustomer;
import com.springboot.bank.exception.BankException;
import com.springboot.bank.model.Customer;

/**
 * @author Ram
 *@entity CustomerService 
 *Desc: It has 2 methods declared which helps to create customer and get customer details.
 */
public interface CustomerService {

	Customer createCustomer(WrapperBankCustomer wrapperBankCustomer) throws BankException;

	Customer getCustomerDetails(Long customerId) throws BankException;
}
