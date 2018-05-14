package com.training.springbootjpa.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.springbootjpa.exception.ManagedException;
import com.training.springbootjpa.model.Customer;
import com.training.springbootjpa.repository.CustomerDAO;

/**
 * @author RamDAfale
 *
 */

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;

	@Override
	public Customer addCustomer(Customer customer) throws ManagedException {
		Customer customerData = customerDAO.save(customer);
		System.out.println(customerData.getCustomerName()+ customerData.getPaymentMode() );
		if ( customerData.getCustomerName()==null || customerData.getPaymentMode() == null )
		{
			throw new ManagedException("provide correct details");
		}
		else
			
			return customerData;
	}

	
	
	
	
	
	
	@Override
	public Customer deleteCustomerById(long deleteById) throws ManagedException {
		
		Optional<Customer> customer=customerDAO.findById(deleteById);
		if(customer.isPresent())
		{
		    customerDAO.deleteById(deleteById);		
		}
		else
		{
			throw new ManagedException("Id not Found");
			
		}
		return null;
			}
		
	

	@Override
	public Long updateCustomer(Customer customerReq, Long id) throws ManagedException {
		// TODO Auto-generated method stub
		Optional<Customer> customer = customerDAO.findById(id);
		if (customer.isPresent()) {
			Customer cust = customerDAO.getOne(id);
			if (customerReq.getPaymentMode() != null) {
				cust.setPaymentMode(customerReq.getPaymentMode());
				//LOGGER.info("hello");
			}
			if (customerReq.getCustomerAddress() != null) {
				cust.setCustomerAddress(customerReq.getCustomerAddress());
			}
			if (customerReq.getCustomerName() != null) {
				cust.setCustomerName(customerReq.getCustomerName());
			}
			

			customerDAO.save(cust);
		} else {
			throw new ManagedException("Sorry !! such record not exist in database");
		}

		return id;
	}

	@Override
	public List<Customer> getCustomer() {

		return (List<Customer>) customerDAO.findAll();
	}

	@Override
	public Customer getCustomerDetail(Long id) throws ManagedException {

		Optional<Customer> customerData = customerDAO.findById(id);
	
		if (customerData.isPresent()) {
			Customer cust1=customerDAO.getOne(id);
			return cust1;
			
		} else
			
		{
			throw new ManagedException("not found!!");
		}
		
	}

}
