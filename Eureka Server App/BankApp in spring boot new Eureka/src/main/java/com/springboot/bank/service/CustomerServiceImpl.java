/**
 * 
 */
package com.springboot.bank.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import org.aspectj.apache.bcel.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.bank.dto.WrapperBankCustomer;
import com.springboot.bank.enums.AllEnums.EventName;
import com.springboot.bank.enums.AllEnums.EventType;
import com.springboot.bank.exception.BankException;
import com.springboot.bank.model.Audit;
import com.springboot.bank.model.Bank;
import com.springboot.bank.model.Customer;
import com.springboot.bank.repository.BankDAO;
import com.springboot.bank.repository.CustomerDAO;

/**
 * @author Ram
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

	@Autowired
	IAuditService IauditService;
	
	
	@Autowired
	
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
		Optional<Customer> customerList = customerDao.findByCustomerId(customerId);
		if (customerList.isPresent()) {
			Customer customer = customerList.get();
			return customer;
		} else
			throw new BankException("Bank details not found");
	}
	@Override
	public Customer updateCustomerDetails( Long customerId,String customerName) {
		
		Optional<Customer> customerToUpdate = customerDao.findById(customerId);
		
		if (customerToUpdate.isPresent()) {
			Customer customer = customerToUpdate.get();
			Customer oldobject=null;
			Customer newobject=null;
			try {
				oldobject = customer.clone();
				customer.setCustomerName(customerName);
				 newobject=customerDao.save(customer);
				Date date= new Date();
				
				String date1 = new Timestamp(System.currentTimeMillis()).toString();
				
				Audit audit=new Audit("101",EventName.CUSTOMER.toString(),EventType.UPDATE.toString(),date1,oldobject,newobject);
				IauditService.auditLogService(audit);
				
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			//IauditService.auditLogService(audit);
			
			
			return newobject;
			
		}else {
			return null;
		}
		
		/*return customerToUpdate.map(customer -> {
			return new Customer(customer.getCustomerId());
			
		}).orElseGet(null);
		*/
	}
}
