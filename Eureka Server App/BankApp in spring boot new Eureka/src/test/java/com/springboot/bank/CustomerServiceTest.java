/**
 * 
 *//*
package com.springboot.bank;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.bank.controller.CustomerController;
import com.springboot.bank.exception.BankException;
import com.springboot.bank.model.Bank;
import com.springboot.bank.model.Customer;
import com.springboot.bank.service.CustomerServiceImpl;
import com.springboot.bank.wrapper.WrapperBankCustomer;

*//**
 * @author ram
 *
 *//*
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

	@Mock
	CustomerServiceImpl customerService;

	@InjectMocks
	CustomerController customerController;

	@Test
	public void truecheckCreateCustomer() throws BankException {
		Bank bank = new Bank(1L, new BigDecimal(1000));
		// Optional<Bank> ob = Optional.of(bank);
		final Customer customer = new Customer(1L, "sumit", 12345, bank);
		final WrapperBankCustomer wrapperBankCustomer = new WrapperBankCustomer(customer, 1L);
		when(customerService.createCustomer(wrapperBankCustomer)).thenReturn(customer);
		assertThat(customerController.createCustomer(wrapperBankCustomer), is(notNullValue()));
	}

	@Test
	public void falseCheckCreateCustomer() {
		final Bank bank = new Bank(-1L, new BigDecimal(1000));
		final Customer customer = new Customer(1L, "sumit", 12345, bank);
		final WrapperBankCustomer wrapperBankCustomer = new WrapperBankCustomer(customer, 1L);
		try {
			when(customerService.createCustomer(wrapperBankCustomer)).thenReturn(customer);
		} catch (BankException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResponseEntity<Customer> customerDummy = null;
		try {
			customerDummy = customerController.createCustomer(wrapperBankCustomer);
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("Customer not found", customerDummy.getBody(), customer);
	}

	@Test
	public void checkCustomerDetails() {
		final Bank bank = new Bank(-1L, new BigDecimal(1000));
		final Customer customer = new Customer(1L, "sumit", 12345, bank);
		try {
			when(customerService.getCustomerDetails(1L)).thenReturn(customer);
		} catch (BankException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResponseEntity<Customer> customerDummy = null;
		try {
			customerDummy = customerController.getCustomerDetails(1L);
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("Customer not found", customerDummy.getBody().getCustomerId(), customer.getCustomerId());
	}
}*/