package com.bank.service;



import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.Exception.ManagedException;
import com.bank.model.AuditLog;
import com.bank.model.Customer;
import com.bank.repository.CustomerRepository;

enum EventName {
	CUSTOMER, ACCOUNT, ATM, BANK
}

enum EventType {
	CREATED, UPDATED
}

@Service("customerService")
public class CustomerServiceImpl implements ICustomerService, Cloneable {
	final static Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);
	@Autowired
	private CustomerRepository customerRepository;
	/*@Autowired
	AuditServiceImpl auditService;*/
	@Autowired
	IFeignClient fclient;
	
	@Override
	public Customer createCustomer(final Customer customer) throws ManagedException {
		final Customer custom = customerRepository.save(customer);
		if (custom != null) {
			return custom;
		} else {
			LOGGER.error("No customer is added");
			throw new ManagedException("no customer is created");
		}
	}

	
	@Override
	public Customer getCustomerDetails(final Long id) throws ManagedException {

		final Customer customer = customerRepository.findBycustomerId(id).get();
		if (customer != null) {
			return customer;
		} else {
			LOGGER.info("No customer of given id is exist");
			throw new ManagedException("no customer of such id exist");
		}

	}

	@Override
	public Customer updateCustomer(final Long id, final String name, final String userId)
			throws ManagedException, CloneNotSupportedException {
		
		final Customer customer = customerRepository.findBycustomerId(id).get();

		Customer oldcustmer = customer.clone();

		if (customer != null) {
			customer.setCustomerName(name);
			customer.setUserId(userId);
			Customer cust = customerRepository.save(customer);
			Calendar cal=Calendar.getInstance();
			Date time=cal.getTime();
			AuditLog audit = new AuditLog(EventName.CUSTOMER.toString(), EventType.UPDATED.toString(), time,
					cust.getUserId(), oldcustmer, cust);

			//AuditLog ob = auditService.generateAudit(audit);
			
			AuditLog ob = fclient.create(audit);
			
			
			return cust;
		} else {
			LOGGER.info("Customer not present as Id not present");
			throw new ManagedException("customer data not  updated");
		}

	}

}
